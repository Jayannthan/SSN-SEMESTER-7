package com.example.products;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

public class ViewActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private LinearLayout rightSideLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);

        ListView brandListView = findViewById(R.id.brandListView);
        rightSideLayout = findViewById(R.id.rightSideLayout);

        databaseHelper = new DatabaseHelper(this);

        List<String> brandList = databaseHelper.getBrandList();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, brandList);

        brandListView.setAdapter(adapter);

        brandListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                String selectedBrand = brandList.get(position);

                List<Product> productList = databaseHelper.getProductsByBrand(selectedBrand);

                // Clear the existing product information
                rightSideLayout.removeAllViews();

                // ...

                if (!productList.isEmpty()) {
                    for (Product product : productList) {
                        // Create a TextView for each product
                        TextView productTextView = new TextView(ViewActivity.this);
                        productTextView.setLayoutParams(new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT // Set height to wrap_content
                        ));
                        productTextView.setText("Product ID: " + product.getId() + "\n" +
                                "Product Name: " + product.getName() + "\n" +
                                "Product Price: " + product.getPrice());

                        // Add the TextView to the rightSideLayout
                        rightSideLayout.addView(productTextView);

                        // Add space (spacer TextView)
                        TextView spacer = new TextView(ViewActivity.this);
                        spacer.setLayoutParams(new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                50
                        ));
                        rightSideLayout.addView(spacer);
                    }
                } else {
                    // Display a message if no products are available for the selected brand
                    TextView noProductsTextView = new TextView(ViewActivity.this);
                    noProductsTextView.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));
                    noProductsTextView.setText("No products available for this brand.");

                    // Add the message to the rightSideLayout
                    rightSideLayout.addView(noProductsTextView);
                }

            }
        });

    }
}
