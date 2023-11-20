package com.example.Test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button malebutton,femalebutton,shapebutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        malebutton=findViewById(R.id.male_button);
        femalebutton=findViewById(R.id.female_button);
        shapebutton=findViewById(R.id.shape_button);

        malebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent malepage=new Intent(MainActivity.this, Male.class);
                malepage.putExtra("String","Male");
                startActivity(malepage);
            }
        });

        femalebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent femalepage=new Intent(MainActivity.this, Female.class);
                femalepage.putExtra("String","Female");
                startActivity(femalepage);
            }
        });

        shapebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shapepage=new Intent(MainActivity.this, Shape.class);
                shapepage.putExtra("String","Shape");
                startActivity(shapepage);
            }
        });
    }
}