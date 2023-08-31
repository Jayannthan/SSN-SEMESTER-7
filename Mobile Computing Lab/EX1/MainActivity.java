package com.sampleapp.healthinsurance;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;

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

		maritalStatusSpinner = findViewById(R.id.maritalStatusSpinner);

		mobileCheckBox = findViewById(R.id.mobileCheckBox);
		landlineCheckBox = findViewById(R.id.landlineCheckBox);
		fullTimeCheckBox = findViewById(R.id.fullTimeCheckBox);
		partTimeCheckBox = findViewById(R.id.partTimeCheckBox);

		dateOfBirthDatePicker = findViewById(R.id.dateOfBirthDatePicker);

		submitButton = findViewById(R.id.submitButton);
		resetButton = findViewById(R.id.resetButton);

		// Set click listeners for buttons
		submitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// Retrieve values from input fields
				String patientName = patientNameEditText.getText().toString();
				String address = addressEditText.getText().toString();
				int age = Integer.parseInt(ageEditText.getText().toString());
				String employer = employerEditText.getText().toString();
				String emergencyContactName = emergencyNameEditText.getText().toString();
				String relationship = relationshipEditText.getText().toString();
				String emergencyContactAddress = emergencyAddressEditText.getText().toString();
				String emergencyContactPhoneNumber = emergencyPhoneNumberEditText.getText().toString();

				// Get selected radio button from genderRadioGroup
				int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();
				RadioButton selectedGenderRadioButton = findViewById(selectedGenderId);
				String gender = selectedGenderRadioButton.getText().toString();

				// Get selected item from maritalStatusSpinner
				String maritalStatus = maritalStatusSpinner.getSelectedItem().toString();

				// Build a comma-separated string for selected checkboxes
				StringBuilder checkBoxes = new StringBuilder();
				if (mobileCheckBox.isChecked()) {
					checkBoxes.append("Mobile, ");
				}
				if (landlineCheckBox.isChecked()) {
					checkBoxes.append("Landline, ");
				}
				if (fullTimeCheckBox.isChecked()) {
					checkBoxes.append("Full Time, ");
				}
				if (partTimeCheckBox.isChecked()) {
					checkBoxes.append("Part Time, ");
				}

				// Get selected date from dateOfBirthDatePicker
				int year = dateOfBirthDatePicker.getYear();
				int month = dateOfBirthDatePicker.getMonth() + 1; 
				int day = dateOfBirthDatePicker.getDayOfMonth();
				String dateOfBirth = year + "-" + month + "-" + day;

				Intent intent = new Intent(MainActivity.this, DisplayDataActivity.class);

				intent.putExtra("patientName", patientName);
				intent.putExtra("address", address);
				intent.putExtra("age", age);
				intent.putExtra("employer", employer);
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
				maritalStatusSpinner.setSelection(0);
				mobileCheckBox.setChecked(false);
				landlineCheckBox.setChecked(false);
				fullTimeCheckBox.setChecked(false);
				partTimeCheckBox.setChecked(false);

				Calendar calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				dateOfBirthDatePicker.updateDate(year, month, day);
			}
		});

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = dateFormat.format(calendar.getTime());
		String[] dateParts = currentDate.split("-");
		int year = Integer.parseInt(dateParts[0]);
		int month = Integer.parseInt(dateParts[1]) - 1; 
		int day = Integer.parseInt(dateParts[2]);
		dateOfBirthDatePicker.init(year, month, day, null);
	}
}
