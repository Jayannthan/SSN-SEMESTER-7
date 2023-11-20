package com.example.test3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayItems extends AppCompatActivity {

    TextView name,address,phone,title,status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_items);

        name=findViewById(R.id.name);
        address=findViewById(R.id.address);
        phone=findViewById(R.id.phone);
        status=findViewById(R.id.order);


        Intent getIntent=getIntent();
        name.setText(getIntent.getStringExtra("name"));
        address.setText(getIntent.getStringExtra("address"));
        phone.setText(getIntent.getStringExtra("phone"));
        status.setText(getIntent.getStringExtra("order"));

    }
}