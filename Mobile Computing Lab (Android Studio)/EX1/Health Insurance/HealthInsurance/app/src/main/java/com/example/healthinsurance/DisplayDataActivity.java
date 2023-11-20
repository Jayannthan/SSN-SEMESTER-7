package com.example.healthinsurance;

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
		String employer = intent.getStringExtra("employer");
		String employmentStatus = intent.getStringExtra("employmentStatus");
		String emergencyContactName = intent.getStringExtra("emergencyContactName");
		String relationship = intent.getStringExtra("relationship");
		String emergencyContactAddress = intent.getStringExtra("emergencyContactAddress");
		String emergencyContactPhoneNumber = intent.getStringExtra("emergencyContactPhoneNumber");
		String gender = intent.getStringExtra("gender");
		String maritalStatus = intent.getStringExtra("maritalStatus");
		String dateOfBirth = intent.getStringExtra("dateOfBirth");

		// Find TextViews in the layout and set their text
		TextView displayPatientName = findViewById(R.id.displayPatientName);
		TextView displayAddress = findViewById(R.id.displayAddress);
		TextView displayAge = findViewById(R.id.displayAge);
		TextView displayEmployer = findViewById(R.id.displayEmployer);
		TextView displayEmploymentStatus = findViewById(R.id.displayEmploymentStatus);
		TextView displayEmergencyContactName = findViewById(R.id.displayEmergencyContactName);
		TextView displayRelationship = findViewById(R.id.displayRelationship);
		TextView displayEmergencyContactAddress = findViewById(R.id.displayEmergencyContactAddress);
		TextView displayEmergencyContactPhoneNumber = findViewById(R.id.displayEmergencyContactPhoneNumber);
		TextView displayGender = findViewById(R.id.displayGender);
		TextView displayMaritalStatus = findViewById(R.id.displayMaritalStatus);
		TextView displayDateOfBirth = findViewById(R.id.displayDateOfBirth);

		displayPatientName.setText("Patient Name: " + patientName);
		displayAddress.setText("Address: " + address);
		displayAge.setText("Age: " + age);
		displayEmployer.setText("Employer: " + employer);
		displayEmploymentStatus.setText("Employment Status: " + employmentStatus);
		displayEmergencyContactName.setText("Emergency Contact Name: " + emergencyContactName);
		displayRelationship.setText("Relationship: " + relationship);
		displayEmergencyContactAddress.setText("Emergency Contact Address: " + emergencyContactAddress);
		displayEmergencyContactPhoneNumber.setText("Emergency Contact Phone: " + emergencyContactPhoneNumber);
		displayGender.setText("Gender: " + gender);
		displayMaritalStatus.setText("Marital Status: " + maritalStatus);
		displayDateOfBirth.setText("Date of Birth: " + dateOfBirth);
	}

}
