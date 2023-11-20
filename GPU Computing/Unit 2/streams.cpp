// Function to fill an array with sequential values
void fill_array(u32 *data, const u32 num_elements)
{
	for (u32 i = 0; i < num_elements; i++)
	{
		data[i] = i;
	}
}

// Function to check the array for errors
void check_array(char *device_prefix, u32 *data, const u32 num_elements)
{
	bool error_found = false;

	// Loop through the elements in the array
	for (u32 i = 0; i < num_elements; i++)
	{
		if (data[i] != (i * 2))
		{
			// If an error is found, print an error message
			printf("%s Error: %u %u\n", device_prefix, i, data[i]);
			error_found = true;
		}
	}

	// If no errors were found, print a success message
	if (error_found == false)
	{
		printf("%s Array check passed\n", device_prefix);
	}
}

// Define the maximum number of supported devices
#define MAX_NUM_DEVICES (4)

// Define the number of elements to use in the array
#define NUM_ELEM (1024 * 1024 * 8)

// Define one stream per GPU
cudaStream_t stream[MAX_NUM_DEVICES];

// Define a string to prefix output messages with to identify the GPU
char device_prefix[MAX_NUM_DEVICES][300];

// Define one working array per device on the GPU
u32 *gpu_data[MAX_NUM_DEVICES];

// Define CPU source and destination arrays, one per GPU
u32 *cpu_src_data[MAX_NUM_DEVICES];
u32 *cpu_dest_data[MAX_NUM_DEVICES];

// Loop over the available devices
for (int i = 0; i < MAX_NUM_DEVICES; i++)
{
	// Generate a prefix for all screen messages
	struct cudaDeviceProp device_prop;
	CUDA_CALL(cudaGetDeviceProperties(&device_prop, i));
	sprintf(&device_prefix[i][0], "\nID:%d %s:", i, device_prop.name);

	// Create a new stream on that device
	CUDA_CALL(cudaStreamCreate(&stream[i]));

	// Allocate memory on the GPU
	CUDA_CALL(cudaMalloc((void **)&gpu_data[i], single_gpu_chunk_size));

	// Allocate page-locked memory on the CPU
	CUDA_CALL(cudaMallocHost((void **)&cpu_src_data[i], single_gpu_chunk_size));
	CUDA_CALL(cudaMallocHost((void **)&cpu_dest_data[i], single_gpu_chunk_size));

	// Fill CPU source array with a known pattern
	fill_array(cpu_src_data[i], NUM_ELEM);

	// Copy a chunk of data from the CPU to the GPU asynchronously
	CUDA_CALL(cudaMemcpyAsync(gpu_data[i], cpu_src_data[i], single_gpu_chunk_size,
							  cudaMemcpyHostToDevice, stream[i]));

	// Invoke the GPU kernel using the newly created stream - asynchronous invocation
	gpu_test_kernel<<<num_blocks, num_threads, shared_memory_usage, stream[i]>>>(gpu_data[i]);
	cuda_error_check(device_prefix[i], "Failed to invoke gpu_test_kernel");

	// Now push memory copies to the host into the streams
	// Copy a chunk of data from the GPU to the CPU asynchronously
	CUDA_CALL(cudaMemcpyAsync(cpu_dest_data[i], gpu_data[i], single_gpu_chunk_size,
							  cudaMemcpyDeviceToHost, stream[i]));
}

// Process the data as it comes back from the GPUs Overlaps CPU execution with GPU execution
for (int i = 0; i < num_devices; i++)
{
	// Select the correct device
	CUDA_CALL(cudaSetDevice(i));
	// Wait for all commands in the stream to complete
	CUDA_CALL(cudaStreamSynchronize(stream[i]));

	// GPU data and stream are now used, so clear them up
	CUDA_CALL(cudaStreamDestroy(stream[i]));
	CUDA_CALL(cudaFree(gpu_data[i]));

	// Data has now arrived in cpu_dest_data[i]
	check_array(device_prefix[i], cpu_dest_data[i], NUM_ELEM);

	// Clean up CPU allocations
	CUDA_CALL(cudaFreeHost(cpu_src_data[i]));
	CUDA_CALL(cudaFreeHost(cpu_dest_data[i]));

	// Release the device context
	CUDA_CALL(cudaDeviceReset());
}
