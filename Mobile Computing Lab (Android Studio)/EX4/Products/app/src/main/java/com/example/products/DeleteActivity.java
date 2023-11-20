package com.example.products;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteActivity extends AppCompatActivity {

    private EditText productIdEditText;
    private Button deleteButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);

        productIdEditText = findViewById(R.id.id);
        deleteButton = findViewById(R.id.submit);
        dbHelper = new DatabaseHelper(this);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the product ID from the EditText
                String productIdText = productIdEditText.getText().toString().trim();

                if (productIdText.isEmpty()) {
                    // Product ID field is empty, show an error message
                    Toast.makeText(DeleteActivity.this, "Product ID is required", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        // Parse the product ID as an integer
                        int productId = Integer.parseInt(productIdText);

                        // Attempt to delete the product by ID
                        boolean isDeleted = dbHelper.deleteProductById(productId);

                        if (isDeleted) {
                            // Product deleted successfully
                            Toast.makeText(DeleteActivity.this, "Product deleted successfully", Toast.LENGTH_SHORT).show();

                            // Clear the product ID EditText
                            productIdEditText.setText("");
                        } else {
                            // Product not found, show an error message
                            Toast.makeText(DeleteActivity.this, "Product not found for deletion", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException e) {
                        // Handle invalid input (non-integer)
                        Toast.makeText(DeleteActivity.this, "Invalid Product ID", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
