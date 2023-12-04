// Define a kernel named "convolution"
__kernel void convolution(
	__read_only image2d_t inputImage,	// Input image (read-only)
	__write_only image2d_t outputImage, // Output image (write-only)
	int rows,							// Number of rows in the image
	int cols,							// Number of columns in the image
	int filterWidth,					// Width of the filter (assumed to be square)
	__constant float *filter,			// Filter coefficients (constant memory)
	sampler_t sampler					// Sampler for image read operations
)
{
	// Get the global ID (coordinates) of the current work-item
	int column = get_global_id(0);
	int row = get_global_id(1);

	// Half the width of the filter for indexing memory
	int halfWidth = filterWidth / 2;

	// Initialize the sum for convolution
	float4 sum = (float4)(0.0f, 0.0f, 0.0f, 0.0f);

	// Iterate through the filter
	for (int i = -halfWidth; i <= halfWidth; i++)
	{
		for (int j = -halfWidth; j <= halfWidth; j++)
		{
			// Coordinates for accessing the image
			int2 coords = (int2)(column + j, row + i);

			// Read a pixel from the image using the sampler
			float4 pixel = read_imagef(inputImage, sampler, coords);

			// Update the sum based on the filter and pixel values
			sum.x += pixel.x * filter[(i + halfWidth) * filterWidth + (j + halfWidth)];
			// (Note: Assumes single-channel images, as only 'sum.x' is updated)
		}
	}

	// Write the result to the output image at the current coordinates
	int2 outputCoords = (int2)(column, row);
	write_imagef(outputImage, outputCoords, sum);
}
