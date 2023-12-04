// Include necessary headers
#include <CL/cl.h>

// Define the matrix dimensions (M, K, N) and the corresponding memory sizes
#define M 1024
#define K 1024
#define N 1024

// Define the matrices A, B, and C, and allocate memory for them on the host (CPU)
float A[M * K], B[K * N], C[M * N];

int main()
{
	cl_int err; // Variable to store OpenCL error codes

	// Create an OpenCL context and command queue to interact with the device (e.g., GPU)
	cl_context context = clCreateContext(NULL, 1, &device_id, NULL, NULL, &err);
	cl_command_queue queue = clCreateCommandQueue(context, device_id, 0, &err);

	// Create OpenCL memory objects (buffers) to hold matrices A, B, and C on the device
	cl_mem buffer_A = clCreateBuffer(context, CL_MEM_READ_ONLY, sizeof(float) * M * K, NULL, &err);
	cl_mem buffer_B = clCreateBuffer(context, CL_MEM_READ_ONLY, sizeof(float) * K * N, NULL, &err);
	cl_mem buffer_C = clCreateBuffer(context, CL_MEM_WRITE_ONLY, sizeof(float) * M * N, NULL, &err);

	// Copy matrices A and B from host memory to device memory
	clEnqueueWriteBuffer(queue, buffer_A, CL_TRUE, 0, sizeof(float) * M * K, A, 0, NULL, NULL);
	clEnqueueWriteBuffer(queue, buffer_B, CL_TRUE, 0, sizeof(float) * K * N, B, 0, NULL, NULL);

	// Load and build the OpenCL kernel from a string representation
	const char *kernel_source =
		"__kernel void matrixMultiplication(__global float* A, __global float* B, __global float* C) {"
		" int i = get_global_id(0);"
		" int j = get_global_id(1);"
		" float sum = 0.0;"
		" for (int k = 0; k < K; ++k) {"
		" sum += A[i * K + k] * B[k * N + j];"
		" }"
		" C[i * N + j] = sum;"
		"}";

	cl_program program = clCreateProgramWithSource(context, 1, &kernel_source, NULL, &err);
	clBuildProgram(program, 1, &device_id, NULL, NULL, NULL);
	cl_kernel kernel = clCreateKernel(program, "matrixMultiplication", &err);

	// Set kernel arguments to pass the matrices A, B, and C to the kernel function
	clSetKernelArg(kernel, 0, sizeof(cl_mem), &buffer_A);
	clSetKernelArg(kernel, 1, sizeof(cl_mem), &buffer_B);
	clSetKernelArg(kernel, 2, sizeof(cl_mem), &buffer_C);

	// Define global and local work sizes to specify how the computation is distributed among work items (threads)
	size_t global_size[2] = {M, N};
	size_t local_size[2] = {16, 16}; // For example, use a 16x16 workgroup size

	// Enqueue the kernel for execution on the device
	clEnqueueNDRangeKernel(queue, kernel, 2, NULL, global_size, local_size, 0, NULL, NULL);

	// Read the result matrix C from the device back to the host
	clEnqueueReadBuffer(queue, buffer_C, CL_TRUE, 0, sizeof(float) * M * N, C, 0, NULL, NULL);

	// Clean up OpenCL resources
	clReleaseMemObject(buffer_A);
	clReleaseMemObject(buffer_B);
	clReleaseMemObject(buffer_C);
	clReleaseProgram(program);
	clReleaseKernel(kernel);
	clReleaseCommandQueue(queue);
	clReleaseContext(context);

	return 0;
}
