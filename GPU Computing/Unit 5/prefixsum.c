#include <CL/cl.hpp>
#include <vector>

#define N 256			  // Total number of elements (should be a power of 2)
#define WORKGROUP_SIZE 64 // Workgroup size (usually a power of 2)

__kernel void prefixSum(__global const int *A, __global int *B, const int n)
{
	// Local memory for each workgroup
	__local int temp[N]; // N should be a power of 2, usually set to the workgroup size
	int gid = get_global_id(0);
	int lid = get_local_id(0);
	int lsize = get_local_size(0);

	// Load data from global memory to local memory
	temp[lid] = A[gid];

	// Perform reduction in local memory
	for (int stride = 1; stride < lsize; stride *= 2)
	{
		barrier(CLK_LOCAL_MEM_FENCE);
		if (lid >= stride)
		{
			temp[lid] += temp[lid - stride];
		}
	}

	// Perform post-reduction in local memory
	barrier(CLK_LOCAL_MEM_FENCE);

	// Write the result to global memory
	B[gid] = (gid == 0) ? 0 : temp[lid - 1];
}

int main()
{
	// Initialize input data (example)
	std::vector<int> A(N);
	// Populate A with data...

	// Create OpenCL context, device, and command queue (error checking omitted for simplicity)
	cl::Context context;
	cl::Device device;
	cl::CommandQueue queue;

	// Create buffers for input and output data
	cl::Buffer bufferA(context, CL_MEM_READ_ONLY, N * sizeof(int));
	cl::Buffer bufferB(context, CL_MEM_WRITE_ONLY, N * sizeof(int));

	// Load the OpenCL kernel code from file (PrefixSum.cl)
	// Read the kernel source code from a file
	std::ifstream kernelFile("PrefixSum.cl");
	std::string kernelSource(std::istreambuf_iterator<char>(kernelFile),
							 (std::istreambuf_iterator<char>()));

	cl::Program::Sources sources(1, std::make_pair(kernelSource.c_str(), kernelSource.length()));

	// Create the program from the source code
	cl::Program program(context, sources);

	// Build the program for the selected device
	program.build({device});

	// Create the kernel
	cl::Kernel kernel(program, "prefixSum");

	// Enqueue input data to the device
	queue.enqueueWriteBuffer(bufferA, CL_TRUE, 0, N * sizeof(int), A.data());

	// Set kernel arguments
	kernel.setArg(0, bufferA);
	kernel.setArg(1, bufferB);
	kernel.setArg(2, N);

	// Define the global and local work sizes
	cl::NDRange globalSize(N);
	cl::NDRange localSize(WORKGROUP_SIZE);

	// Launch the kernel
	queue.enqueueNDRangeKernel(kernel, cl::NullRange, globalSize, localSize);

	// Enqueue the result from device to host
	std::vector<int> B(N);
	queue.enqueueReadBuffer(bufferB, CL_TRUE, 0, N * sizeof(int), B.data());

	// Output the result
	for (int i = 0; i < N; i++)
	{
		std::cout << "B[" << i << "] = " << B[i] << std::endl;
	}

	return 0;
}
