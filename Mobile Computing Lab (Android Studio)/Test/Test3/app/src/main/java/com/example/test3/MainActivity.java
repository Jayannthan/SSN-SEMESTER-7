package com.example.test3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText name, address, phone;
    CheckBox rice, cha, dosa, juice;
    Button submitButton, shapePageButton; // Corrected the variable names
    String[] foodCheck = {"rice", "dosa"}; // Corrected the foodCheck array

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = findViewById(R.id.submitbutton);
        name = findViewById(R.id.getname);
        address = findViewById(R.id.getaddress);
        phone = findViewById(R.id.getphone);
        shapePageButton = findViewById(R.id.Shape);
        rice = findViewById(R.id.rice);
        cha = findViewById(R.id.chappati); // Corrected the variable name
        dosa = findViewById(R.id.dosa);
        juice = findViewById(R.id.juice);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String names = name.getText().toString();
                String addresss = address.getText().toString();
                String phones = phone.getText().toString();
                String food = "";

                if (rice.isChecked()) {
                    if (isFound("rice", foodCheck)) {
                        food += "Rice is Available\n";
                    } else {
                        food += "Sry!!! Rice not available!!!\n";
                    }
                }
                if (cha.isChecked()) {
                    if (isFound("chappati", foodCheck)) {
                        food += "Chappati is Available\n";
                    } else {
                        food += "Sry!!! Chappati not available!!!\n";
                    }
                }
                if (dosa.isChecked()) {
                    if (isFound("dosa", foodCheck)) {
                        food += "Dosa is Available\n";
                    } else {
                        food += "Sry!!! Dosa not available!!!\n";
                    }
                }
                if (juice.isChecked()) {
                    if (isFound("juice", foodCheck)) {
                        food += "Juice is Available\n";
                    } else {
                        food += "Sry!!! Juice not available!!!\n";
                    }
                }

                Intent sendIntent = new Intent(MainActivity.this, DisplayItems.class);
                sendIntent.putExtra("name", names);
                sendIntent.putExtra("address", addresss);
                sendIntent.putExtra("phone", phones);
                sendIntent.putExtra("order", food);
                startActivity(sendIntent);
            }
        });

        shapePageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shapePage = new Intent(MainActivity.this, ShapePage.class);
                startActivity(shapePage);
            }
        });
    }

    private boolean isFound(String item, String[] array) {
        for (String str : array) {
            if (str.equals(item)) {
                return true;
            }
        }
        return false;
    }
}
