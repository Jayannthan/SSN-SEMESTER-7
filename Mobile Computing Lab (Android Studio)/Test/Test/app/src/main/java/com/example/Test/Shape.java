package com.example.Test;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Shape extends AppCompatActivity {

    Button malebutton, femalebutton, shapebutton;
    TextView viewtext;

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);

        malebutton = findViewById(R.id.male_button);
        femalebutton = findViewById(R.id.female_button);
        shapebutton = findViewById(R.id.shape_button);
        viewtext = findViewById(R.id.textshape);
        img=findViewById(R.id.img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setScaleX(2);
                img.setScaleY(2);
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator animator;
                animator=ObjectAnimator.ofFloat(img,"translationX",0,10);
                animator.setDuration(100);
                animator.start();
            }
        });

        Intent mypage = getIntent();
        String displaystring = mypage.getStringExtra("String");
        viewtext.setText(displaystring);

        malebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent malepage = new Intent(Shape.this, Male.class);
                malepage.putExtra("String", "Male");
                startActivity(malepage);
            }
        });

        femalebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent femalepage = new Intent(Shape.this, Female.class);
                femalepage.putExtra("String", "Female");
                startActivity(femalepage);
            }
        });

//        shapebutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent shapepage = new Intent(Female.this, Shape.class);
//                shapepage.putExtra("String", "Shape");
//                startActivity(shapepage);
//            }
//        });
    }
}
