package com.example.ex3a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView carImageView;
        Button forwardButton;
        Button backwardButton;
        final float[] carRotation = {90};
        final int[] fd = {1};
        final boolean[] isZoomed = {false};

        carImageView = findViewById(R.id.imageView);
        forwardButton = findViewById(R.id.forward);
        backwardButton = findViewById(R.id.backward);

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fd[0] == 0){
                    carRotation[0] += 180;
                    carImageView.setRotation(carRotation[0]);
                    fd[0] = 1;
                }
                float currentY = carImageView.getTranslationY();
                carImageView.setTranslationY(currentY+20);
            }
        });

        backwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fd[0] == 1) {
                    carRotation[0] += 180;
                    carImageView.setRotation(carRotation[0]);
                    fd[0] = 0;
                }
                float currentY = carImageView.getTranslationY();
                carImageView.setTranslationY(currentY-20);
            }
        });

        carImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isZoomed[0]) {
                    // Zoom out
                    carImageView.setScaleX(1.0f);
                    carImageView.setScaleY(1.0f);
                    isZoomed[0] = false;
                } else {
                    // Zoom in
                    carImageView.setScaleX(2.0f);
                    carImageView.setScaleY(2.0f);
                    isZoomed[0] = true;
                }
            }
        });
    }
}