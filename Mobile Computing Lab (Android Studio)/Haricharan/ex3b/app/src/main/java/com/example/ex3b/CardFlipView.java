package com.example.ex3b;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;

import com.example.ex3b.R;

public class CardFlipView extends CardView {

    private CardView cardFront;
    private CardView cardBack;
    private boolean isFrontVisible = true;

    public CardFlipView(@NonNull Context context) {
        this(context, null);
    }

    public CardFlipView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.card_flip_view, this);
        cardFront = findViewById(R.id.cardFront);
        cardBack = findViewById(R.id.cardBack);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCard();
            }
        });
    }

    public void flipCard() {
        if (isFrontVisible) {
            ViewCompat.animate(cardFront).rotationY(180).setDuration(500).start();
            ViewCompat.animate(cardBack).rotationY(0).setDuration(500).start();
        } else {
            ViewCompat.animate(cardFront).rotationY(0).setDuration(500).start();
            ViewCompat.animate(cardBack).rotationY(180).setDuration(500).start();
        }
        isFrontVisible = !isFrontVisible;
    }
}