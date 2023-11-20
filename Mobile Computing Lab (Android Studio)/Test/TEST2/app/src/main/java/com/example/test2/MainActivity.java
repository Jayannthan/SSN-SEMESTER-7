package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    RadioGroup titlegrp;
    RadioButton mr,mrs;
    EditText name,address;
    DatePicker datePicker;
    Button submitbutton,shapepagebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitbutton = findViewById(R.id.submitbutton);
        titlegrp = findViewById(R.id.titlegrp);
        mr = findViewById(R.id.mrbutton);
        mrs=findViewById(R.id.mrsbutton);
        name=findViewById(R.id.getname);
        address=findViewById(R.id.getaddress);
        datePicker=findViewById(R.id.getdob);
        shapepagebutton = findViewById(R.id.Shape);

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String names=name.getText().toString();
                String addresss=address.getText().toString();
                int year=2023-datePicker.getYear();
                String tit="";

                if (mr.isChecked()){
                    tit="Mr.";
                } else if (mrs.isChecked()) {
                    tit="Mrs.";
                }
                Intent sendIntent=new Intent(MainActivity.this, DisplayItems.class);
                sendIntent.putExtra("title",tit);
                sendIntent.putExtra("name",names);
                sendIntent.putExtra("address",addresss);
                sendIntent.putExtra("year",Integer.toString(year));
                startActivity(sendIntent);
            }
        });

        shapepagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shapepage=new Intent(MainActivity.this, ShapePage.class);
                startActivity(shapepage);
            }
        });
    }
}