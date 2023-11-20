package com.example.healthinsurance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

	// Declare your view elements
	EditText patientNameEditText, addressEditText, ageEditText, employerEditText,
			emergencyNameEditText, relationshipEditText, emergencyAddressEditText,
			emergencyPhoneNumberEditText;

	RadioGroup genderRadioGroup;
	RadioButton maleRadioButton, femaleRadioButton, otherRadioButton;

	Spinner maritalStatusSpinner;

	CheckBox mobileCheckBox, landlineCheckBox, fullTimeCheckBox, partTimeCheckBox;

	DatePicker dateOfBirthDatePicker;

	Button submitButton, resetButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initialize your view elements
		patientNameEditText = findViewById(R.id.patientNameEditText);
		addressEditText = findViewById(R.id.addressEditText);
		ageEditText = findViewById(R.id.ageEditText);
		employerEditText = findViewById(R.id.employerEditText);
		emergencyNameEditText = findViewById(R.id.emergencyNameEditText);
		relationshipEditText = findViewById(R.id.relationshipEditText);
		emergencyAddressEditText = findViewById(R.id.emergencyAddressEditText);
		emergencyPhoneNumberEditText = findViewById(R.id.emergencyPhoneNumberEditText);

		genderRadioGroup = findViewById(R.id.genderRadioGroup);
		maleRadioButton = findViewById(R.id.maleRadioButton);
		femaleRadioButton = findViewById(R.id.femaleRadioButton);
		otherRadioButton = findViewById(R.id.otherRadioButton);

		mobileCheckBox = findViewById(R.id.mobileCheckBox);
		landlineCheckBox = findViewById(R.id.landlineCheckBox);
		fullTimeCheckBox = findViewById(R.id.fullTimeCheckBox);
		partTimeCheckBox = findViewById(R.id.partTimeCheckBox);

		dateOfBirthDatePicker = findViewById(R.id.dateOfBirthDatePicker);

		submitButton = findViewById(R.id.submitButton);
		resetButton = findViewById(R.id.resetButton);

		// Outside the OnClickListener block
		Spinner maritalStatusSpinner = findViewById(R.id.displayMaritalStatusSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				MainActivity.this, R.array.marital_status_options, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		maritalStatusSpinner.setAdapter(adapter);

		// Inside the OnClickListener block
		submitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String patientName = patientNameEditText.getText().toString();
				String address = addressEditText.getText().toString();
				String ageStr = ageEditText.getText().toString();
				int age = 0;
				if (!ageStr.isEmpty()) {
					age = Integer.parseInt(ageStr);
				}
				String employer = employerEditText.getText().toString();
				String employmentStatus = "";
				if (fullTimeCheckBox.isChecked()) {
					employmentStatus = "Full Time";
				} else if (partTimeCheckBox.isChecked()) {
					employmentStatus = "Part Time";
				}

				String maritalStatus = maritalStatusSpinner.getSelectedItem().toString();

				String emergencyContactName = emergencyNameEditText.getText().toString();
				String relationship = relationshipEditText.getText().toString();
				String emergencyContactAddress = emergencyAddressEditText.getText().toString();
				String emergencyContactPhoneNumber = emergencyPhoneNumberEditText.getText().toString();

				// Get selected radio button from genderRadioGroup
				int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
				RadioButton selectedGenderRadioButton = findViewById(selectedGenderId);
				String gender = selectedGenderRadioButton.getText().toString();

				// Get selected date from dateOfBirthDatePicker
				int year = dateOfBirthDatePicker.getYear();
				int month = dateOfBirthDatePicker.getMonth() + 1; // DatePicker month is 0-based
				int day = dateOfBirthDatePicker.getDayOfMonth();
				String dateOfBirth = year + "-" + month + "-" + day;

				Intent intent = new Intent(MainActivity.this, DisplayDataActivity.class);

				// Pass data to the intent using extras
				intent.putExtra("patientName", patientName);
				intent.putExtra("address", address);
				intent.putExtra("age", age);
				intent.putExtra("employer", employer);
				intent.putExtra("employmentStatus", employmentStatus);
				intent.putExtra("emergencyContactName", emergencyContactName);
				intent.putExtra("relationship", relationship);
				intent.putExtra("emergencyContactAddress", emergencyContactAddress);
				intent.putExtra("emergencyContactPhoneNumber", emergencyContactPhoneNumber);
				intent.putExtra("gender", gender);
				intent.putExtra("maritalStatus", maritalStatus);
				intent.putExtra("dateOfBirth", dateOfBirth);

				// Start the new activity
				startActivity(intent);
			}
		});

		resetButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// Clear all input fields
				patientNameEditText.getText().clear();
				addressEditText.getText().clear();
				ageEditText.getText().clear();
				employerEditText.getText().clear();
				emergencyNameEditText.getText().clear();
				relationshipEditText.getText().clear();
				emergencyAddressEditText.getText().clear();
				emergencyPhoneNumberEditText.getText().clear();
				genderRadioGroup.clearCheck();
				maritalStatusSpinner.setSelection(0); // Reset to the first item in the spinner
				mobileCheckBox.setChecked(false);
				landlineCheckBox.setChecked(false);
				fullTimeCheckBox.setChecked(false);
				partTimeCheckBox.setChecked(false);

				// Reset the date picker to today's date
				Calendar calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				dateOfBirthDatePicker.updateDate(year, month, day);
			}
		});

		// Set the default date of birth in the date picker to today's date
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = dateFormat.format(calendar.getTime());
		String[] dateParts = currentDate.split("-");
		int year = Integer.parseInt(dateParts[0]);
		int month = Integer.parseInt(dateParts[1]) - 1; // Month is 0-based
		int day = Integer.parseInt(dateParts[2]);
		dateOfBirthDatePicker.init(year, month, day, null);
	}
}
