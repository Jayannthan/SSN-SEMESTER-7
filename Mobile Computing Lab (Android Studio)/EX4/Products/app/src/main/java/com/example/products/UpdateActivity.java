package com.example.products;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

	private EditText productIdEditText;
	private EditText newPriceEditText;
	private Button updateButton;
	private DatabaseHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update);

		productIdEditText = findViewById(R.id.id);
		newPriceEditText = findViewById(R.id.price);
		updateButton = findViewById(R.id.submit);
		dbHelper = new DatabaseHelper(this);

		updateButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// Get the product ID and new price from the EditText fields
				String productIdText = productIdEditText.getText().toString().trim();
				String newPriceText = newPriceEditText.getText().toString().trim();

				if (productIdText.isEmpty() || newPriceText.isEmpty()) {
					// Product ID or new price field is empty, show an error message
					Toast.makeText(UpdateActivity.this, "Product ID and new price are required", Toast.LENGTH_SHORT)
							.show();
				} else {
					try {
						// Parse the product ID and new price as needed (integer and double)
						int productId = Integer.parseInt(productIdText);
						double newPrice = Double.parseDouble(newPriceText);

						// Update the product price in the database
						boolean isUpdated = dbHelper.updateProductPrice(productId, newPrice);

						if (isUpdated) {
							// Show a success message
							Toast.makeText(UpdateActivity.this, "Price updated successfully", Toast.LENGTH_SHORT)
									.show();
						} else {
							// Show an error message if the update failed
							Toast.makeText(UpdateActivity.this, "Failed to update price. Product not found.",
									Toast.LENGTH_SHORT).show();
						}

						// Clear the EditText fields
						productIdEditText.setText("");
						newPriceEditText.setText("");
					} catch (NumberFormatException e) {
						// Handle invalid input (non-integer or non-double)
						Toast.makeText(UpdateActivity.this, "Invalid Product ID or new price", Toast.LENGTH_SHORT)
								.show();
					}
				}
			}
		});
	}
}
