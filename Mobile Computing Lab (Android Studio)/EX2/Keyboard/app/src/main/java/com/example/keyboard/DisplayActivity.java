package com.example.keyboard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		Intent intent = getIntent();
		String editText = intent.getStringExtra("input_value");
		TextView preview = findViewById(R.id.preview_text);
		preview.setText(editText);
	}
}