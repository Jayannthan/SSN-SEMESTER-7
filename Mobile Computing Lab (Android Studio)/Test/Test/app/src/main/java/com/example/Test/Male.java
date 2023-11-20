package com.example.Test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Male extends AppCompatActivity {

    Button malebutton,femlaebutton,shapebutton;
    TextView viewtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male);

        malebutton=findViewById(R.id.male_button);
        femlaebutton=findViewById(R.id.female_button);
        shapebutton=findViewById(R.id.shape_button);
        viewtext=findViewById(R.id.textmale);

        Intent mypage=getIntent();
        String displaystring = mypage.getStringExtra("String");
        viewtext.setText(displaystring);

//        malebutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent malepage=new Intent(MainActivity.this, Male.class);
//                malepage.putExtra("String","Male");
//                startActivity(malepage);
//            }
//        });

        femlaebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent femalepage=new Intent(Male.this, Female.class);
                femalepage.putExtra("String","Female");
                startActivity(femalepage);
            }
        });

        shapebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shapepage=new Intent(Male.this, Shape.class);
                shapepage.putExtra("String","Shape");
                startActivity(shapepage);
            }
        });
    }
}