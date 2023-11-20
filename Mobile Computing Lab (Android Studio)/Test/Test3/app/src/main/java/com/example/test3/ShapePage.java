package com.example.test3;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class ShapePage extends AppCompatActivity {
    Button bounce;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_page);

        img = findViewById(R.id.ball);
        bounce = findViewById(R.id.bouncebutton);
        bounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator animatorX1 = ObjectAnimator.ofFloat(img, "translationX", 0, 200);
                ObjectAnimator animatorY1 = ObjectAnimator.ofFloat(img, "translationY", 0, -400);

                animatorX1.setDuration(1000);
                animatorY1.setDuration(1000);


                ObjectAnimator animatorX2 = ObjectAnimator.ofFloat(img, "translationX", 200, 400);
                ObjectAnimator animatorY2 = ObjectAnimator.ofFloat(img, "translationY", -400, 0);

                animatorX2.setDuration(1000);
                animatorY2.setDuration(1000);


                ObjectAnimator animatorX3 = ObjectAnimator.ofFloat(img, "translationX", 400, 600);
                ObjectAnimator animatorY3 = ObjectAnimator.ofFloat(img, "translationY", 0, -400);


                animatorX3.setDuration(1000);
                animatorY3.setDuration(1000);

                ObjectAnimator animatorX4 = ObjectAnimator.ofFloat(img, "translationX", 600, 800);
                ObjectAnimator animatorY4 = ObjectAnimator.ofFloat(img, "translationY", -400, 0);

                animatorX4.setDuration(1000);
                animatorY4.setDuration(1000);


                AnimatorSet animatorSet1 = new AnimatorSet();
                animatorSet1.playTogether(animatorX1, animatorY1);

                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.playTogether(animatorX2, animatorY2);

                AnimatorSet animatorSet3 = new AnimatorSet();
                animatorSet3.playTogether(animatorX3, animatorY3);

                AnimatorSet animatorSet4 = new AnimatorSet();
                animatorSet4.playTogether(animatorX4, animatorY4);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(animatorSet1, animatorSet2, animatorSet3, animatorSet4);

                animatorSet.start();
            }
        });

    }
}
