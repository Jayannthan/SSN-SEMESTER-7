package com.example.ex3;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	private RelativeLayout carLayout;
	private Button moveButton;
	private Button moveBack;
	private int currentPosition = 0;
	private boolean isFlipped = false;
	private boolean isZoomed = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		carLayout = findViewById(R.id.carLayout);
		moveButton = findViewById(R.id.moveForwardButton);
		moveBack = findViewById(R.id.moveBackButton);

		carLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				toggleZoom();
			}
		});

		moveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				moveCar();
			}
		});

		moveBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				moveCarBackward();
			}
		});
	}

	private void toggleZoom() {
		if (isZoomed) {
			zoomOut();
		} else {
			zoomIn();
		}
	}

	private void zoomIn() {
		carLayout.setScaleX(1.2f);
		carLayout.setScaleY(1.2f);
		isZoomed = true;
	}

	private void zoomOut() {
		carLayout.setScaleX(1f);
		carLayout.setScaleY(1f);
		isZoomed = false;
	}

	private void moveCar() {
		int start = currentPosition;
		int maxTranslation = getMaxTranslation();

		int end;
		if (isFlipped) {
			end = Math.max(start - 100, -maxTranslation);
		} else {
			end = Math.min(start + 100, maxTranslation);
		}

		ObjectAnimator animator;
		animator = ObjectAnimator.ofFloat(carLayout, "translationX", start, end);
		animator.setDuration(100);
		animator.start();

		currentPosition = end;
	}

	private int getMaxTranslation() {
		int screenWidth = getResources().getDisplayMetrics().widthPixels;
		int carWidth = (int) (200 * getResources().getDisplayMetrics().density);

		return (screenWidth - carWidth) / 2;
	}

	private void moveCarBackward() {
		ObjectAnimator rotationAnimator;
		int rotation;
		if (isFlipped) {
			rotation = 180;
			rotationAnimator = ObjectAnimator.ofFloat(carLayout, "rotationY", rotation, 0);
			isFlipped = false;
		} else {
			rotation = 0;
			rotationAnimator = ObjectAnimator.ofFloat(carLayout, "rotationY", rotation, 180);
			isFlipped = true;
		}
		rotationAnimator.setDuration(100);
		rotationAnimator.start();
	}
}
