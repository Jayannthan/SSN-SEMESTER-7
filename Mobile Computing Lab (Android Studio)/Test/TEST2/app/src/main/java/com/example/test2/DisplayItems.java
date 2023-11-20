package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayItems extends AppCompatActivity {

    TextView name,address,age,title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_items);

        title=findViewById(R.id.title);
        name=findViewById(R.id.name);
        address=findViewById(R.id.address);
        age=findViewById(R.id.age);

        Intent getIntent=getIntent();
        title.setText(getIntent.getStringExtra("title"));
        name.setText(getIntent.getStringExtra("name"));
        address.setText(getIntent.getStringExtra("address"));
        age.setText(getIntent.getStringExtra("year"));
    }
}