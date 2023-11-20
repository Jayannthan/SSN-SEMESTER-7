package com.sampleapp.healthinsurance;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayDataActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_data);

		// Retrieve data from the intent
		Intent intent = getIntent();
		String patientName = intent.getStringExtra("patientName");
		String address = intent.getStringExtra("address");
		int age = intent.getIntExtra("age", 0);

		// Find TextViews in the layout and set their text
		TextView displayPatientName = findViewById(R.id.displayPatientName);
		TextView displayAddress = findViewById(R.id.displayAddress);
		TextView displayAge = findViewById(R.id.displayAge);

		displayPatientName.setText("Patient Name: " + patientName);
		displayAddress.setText("Address: " + address);
		displayAge.setText("Age: " + age);
	}
}
