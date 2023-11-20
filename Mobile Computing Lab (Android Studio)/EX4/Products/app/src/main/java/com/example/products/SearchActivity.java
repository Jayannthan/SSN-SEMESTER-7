package com.example.products;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

	private EditText productIdEditText;
	private TableLayout outputTable;
	private TextView nameTextView;
	private TextView brandTextView;
	private TextView descriptionTextView;
	private TextView priceTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);

		// Initialize UI elements
		productIdEditText = findViewById(R.id.id);
		outputTable = findViewById(R.id.outputTable);
		nameTextView = findViewById(R.id.name);
		brandTextView = findViewById(R.id.brand);
		descriptionTextView = findViewById(R.id.desc);
		priceTextView = findViewById(R.id.price);

		Button searchButton = findViewById(R.id.submit);

		searchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				searchProduct();
			}
		});
	}

	private void searchProduct() {
		// Get the product ID entered by the user
		String productIdText = productIdEditText.getText().toString();

		if (productIdText.isEmpty()) {
			// Product ID field is empty, show an error message or handle it as needed
			Toast.makeText(this, "Please enter a Product ID", Toast.LENGTH_SHORT).show();
			return;
		}

		int productId = Integer.parseInt(productIdText);

		// Use the DatabaseHelper to search for the product by ID
		DatabaseHelper databaseHelper = new DatabaseHelper(this);
		Product product = databaseHelper.searchProductById(productId);

		if (product != null) {
			// Display the product information in the outputTable
			nameTextView.setText(product.getName());
			brandTextView.setText(product.getBrand());
			descriptionTextView.setText(product.getDescription());
			priceTextView.setText(String.valueOf(product.getPrice()));

			// Make the outputTable visible
			outputTable.setVisibility(View.VISIBLE);
		} else {
			// Product not found, you can display an error message or handle it as needed
			Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show();

			// For now, let's hide the outputTable
			outputTable.setVisibility(View.GONE);
		}
	}
}
