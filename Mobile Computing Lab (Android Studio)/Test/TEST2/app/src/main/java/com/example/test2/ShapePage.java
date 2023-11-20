package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ShapePage extends AppCompatActivity {
    Button zoom,right,left;
    ImageView img;
    Boolean isZoom=false;

    int pos=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_page);

        zoom=findViewById(R.id.zoom);
        left=findViewById(R.id.left);
        right=findViewById(R.id.right);
        img=findViewById(R.id.rect);
        zoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isZoom){
                    isZoom=false;
                    img.setScaleX(1);
                    img.setScaleY(1);
                }
                else {
                    isZoom=true;
                    img.setScaleX(2);
                    img.setScaleY(2);
                }
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator animator;

                animator=ObjectAnimator.ofFloat(img,"translationX",pos,pos-20);
                pos-=20;
                animator.setDuration(100);
                animator.start();
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator animator;

                animator=ObjectAnimator.ofFloat(img,"rotationX",pos,pos+20);
                pos+=20;
                animator.setDuration(100);
                animator.start();
            }
        });
    }
}