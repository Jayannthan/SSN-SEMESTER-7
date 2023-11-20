package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner maritalSpinner = findViewById(R.id.marital_ip);

        // Get the string array from resources
        String[] maritalStatuses = getResources().getStringArray(R.array.marital_array);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, maritalStatuses);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        maritalSpinner.setAdapter(adapter);

        Button reset = findViewById(R.id.reset_button);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maritalSpinner.setSelection(0);
                EditText name_ip = findViewById(R.id.name_ip);
                name_ip.setText("");
                EditText landline_ip = findViewById(R.id.landline_ip);
                landline_ip.setText("");
                EditText mobile_ip = findViewById(R.id.mobile_ip);
                mobile_ip.setText("");
                EditText address_ip = findViewById(R.id.address_ip);
                address_ip.setText("");
                EditText age_ip = findViewById(R.id.age_ip);
                age_ip.setText("");
                EditText dob_ip = findViewById(R.id.dob_ip);
                dob_ip.setText("");
                EditText name_emp_ip = findViewById(R.id.name_emp_ip);
                name_emp_ip.setText("");
                EditText name_ec_ip = findViewById(R.id.name_ec_ip);
                name_ec_ip.setText("");
                EditText relationship_ip = findViewById(R.id.relationship_ip);
                relationship_ip.setText("");
                EditText address_ip_ec = findViewById(R.id.address_ip_ec);
                address_ip_ec.setText("");
                EditText phno_ec_ip = findViewById(R.id.phno_ec_ip);
                phno_ec_ip.setText("");
                RadioButton male_ip = findViewById(R.id.male_ip);
                male_ip.setChecked(false);
                RadioButton female_ip = findViewById(R.id.female_ip);
                female_ip.setChecked(false);
                RadioButton others_ip = findViewById(R.id.others_ip);
                others_ip.setChecked(false);
                CheckBox checkBox = findViewById(R.id.checkBox);
                checkBox.setChecked(false);
                CheckBox checkBox2 = findViewById(R.id.checkBox2);
                checkBox2.setChecked(false);
                CheckBox full_time = findViewById(R.id.full_time);
                full_time.setChecked(false);
                CheckBox part_time = findViewById(R.id.part_time);
                part_time.setChecked(false);
                CheckBox retired = findViewById(R.id.retired_ip);
                retired.setChecked(false);
                CheckBox emp_others_ip = findViewById(R.id.emp_others_ip);
                emp_others_ip.setChecked(false);
                CheckBox unemployed_ip = findViewById(R.id.unemployed_ip);
                unemployed_ip.setChecked(false);
                CheckBox student = findViewById(R.id.student_ip);
                student.setChecked(false);
                TextView output_view = findViewById(R.id.output_view);
                output_view.setText("");
            }
        });

        Button submit = findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name_ip = findViewById(R.id.name_ip);
                EditText address_ip = findViewById(R.id.address_ip);
                EditText age_ip = findViewById(R.id.age_ip);
                EditText name_emp_ip = findViewById(R.id.name_emp_ip);
                EditText name_ec_ip = findViewById(R.id.name_ec_ip);
                EditText relationship_ip = findViewById(R.id.relationship_ip);
                EditText phno_ec_ip = findViewById(R.id.phno_ec_ip);
                String output = "Output\n\n"+"Patient\nName: " + name_ip.getText().toString() + "\n";
                output += "Address: "+ address_ip.getText().toString() + "\n"
                        + "Age: " + age_ip.getText().toString() + "\n"
                        + "Employer\nName: " + name_emp_ip.getText().toString() + "\n"
                        + "Emergency Contact\nName: " + name_ec_ip.getText().toString() + "\n"
                        + "Relationship: " + relationship_ip.getText().toString() + "\n"
                        + "Phone Number: " + phno_ec_ip.getText().toString() + "\n";

                TextView output_view = findViewById(R.id.output_view);
                output_view.setText(output);
            }
        });
    }
}