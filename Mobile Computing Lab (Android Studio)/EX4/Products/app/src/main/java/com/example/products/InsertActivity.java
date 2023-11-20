package com.example.products;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {

    private EditText productIdEditText, productNameEditText, productBrandEditText, productDescriptionEditText, productPriceEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert);

        // Initialize UI elements
        productIdEditText = findViewById(R.id.id);
        productNameEditText = findViewById(R.id.name);
        productBrandEditText = findViewById(R.id.brand);
        productDescriptionEditText = findViewById(R.id.desc);
        productPriceEditText = findViewById(R.id.price);
        submitButton = findViewById(R.id.submit);

        // Set click listener for the Submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input
                String productId = productIdEditText.getText().toString();
                String productName = productNameEditText.getText().toString();
                String productBrand = productBrandEditText.getText().toString();
                String productDescription = productDescriptionEditText.getText().toString();
                String productPriceStr = productPriceEditText.getText().toString();

                // Check if any of the fields are empty
                if (productId.isEmpty() || productName.isEmpty() || productBrand.isEmpty() || productDescription.isEmpty() || productPriceStr.isEmpty()) {
                    Toast.makeText(InsertActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Convert product price to double
                double productPrice = Double.parseDouble(productPriceStr);

                // Insert data into the database
                insertProduct(productId, productName, productBrand, productDescription, productPrice);

                // Show success message
                Toast.makeText(InsertActivity.this, "Product inserted successfully", Toast.LENGTH_SHORT).show();

                // Clear input fields
                productIdEditText.getText().clear();
                productNameEditText.getText().clear();
                productBrandEditText.getText().clear();
                productDescriptionEditText.getText().clear();
                productPriceEditText.getText().clear();
            }
        });
    }

    private void insertProduct(String productId, String productName, String productBrand, String productDescription, double productPrice) {
        // Create or open the database for writing
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ProductEntry.COLUMN_PRODUCT_ID, productId);
        values.put(DatabaseContract.ProductEntry.COLUMN_PRODUCT_NAME, productName);
        values.put(DatabaseContract.ProductEntry.COLUMN_PRODUCT_BRAND, productBrand);
        values.put(DatabaseContract.ProductEntry.COLUMN_PRODUCT_DESCRIPTION, productDescription);
        values.put(DatabaseContract.ProductEntry.COLUMN_PRODUCT_PRICE, productPrice);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DatabaseContract.ProductEntry.TABLE_NAME, null, values);

        // Close the database
        db.close();
    }
}
