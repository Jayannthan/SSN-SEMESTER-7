package com.example.Test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Female extends AppCompatActivity {

    Button malebutton, femalebutton, shapebutton; // Changed "femlaebutton" to "femalebutton"
    TextView viewtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_female);

        malebutton = findViewById(R.id.male_button);
        femalebutton = findViewById(R.id.female_button); // Corrected the variable name
        shapebutton = findViewById(R.id.shape_button);
        viewtext = findViewById(R.id.textfemale);

        Intent mypage = getIntent();
        String displaystring = mypage.getStringExtra("String");
        viewtext.setText(displaystring);

        malebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent malepage = new Intent(Female.this, Male.class);
                malepage.putExtra("String", "Male");
                startActivity(malepage);
            }
        });

//        femalebutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent femalepage = new Intent(Female.this, Female.class);
//                femalepage.putExtra("String", "Female");
//                startActivity(femalepage);
//            }
//        });

        shapebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shapepage = new Intent(Female.this, Shape.class);
                shapepage.putExtra("String", "Shape");
                startActivity(shapepage);
            }
        });
    }
}
