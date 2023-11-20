package com.example.products;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

	Button insertButton;
	Button deleteButton;
	Button updateButton;
	Button searchButton;
	Button viewButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initialize buttons inside the onCreate method
		insertButton = findViewById(R.id.insert);
		deleteButton = findViewById(R.id.delete);
		updateButton = findViewById(R.id.update);
		searchButton = findViewById(R.id.search);
		viewButton = findViewById(R.id.view);

		insertButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, InsertActivity.class);
				startActivity(intent);
			}
		});

		deleteButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
				startActivity(intent);
			}
		});

		updateButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
				startActivity(intent);
			}
		});

		searchButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, SearchActivity.class);
				startActivity(intent);
			}
		});

		viewButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ViewActivity.class);
				startActivity(intent);
			}
		});

	}
}
