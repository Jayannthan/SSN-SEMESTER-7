package com.example.exercise7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		EditText fileContents = (EditText) findViewById(R.id.fileContents);
		EditText fileName = (EditText) findViewById(R.id.fileName);

		Button write = (Button) findViewById(R.id.write);
		write.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String writeFileContents = fileContents.getText().toString();
				String writeFileName = fileName.getText().toString();
				writeToFile(writeFileName, writeFileContents);
			}
		});

		Button read = (Button) findViewById(R.id.read);
		read.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String readFileName = fileName.getText().toString();
				Intent i = new Intent(MainActivity.this, ReadActivity.class);
				i.putExtra("fileName", readFileName);
				startActivity(i);
			}
		});
	}

	private void writeToFile(String writeFileName, String writeFileContents) {
		File path = getApplicationContext().getFilesDir();

		try {
			FileOutputStream writer = new FileOutputStream(new File(path, writeFileName));
			writer.write(writeFileContents.getBytes());
			writer.close();
			Toast.makeText(getApplicationContext(), "Written to file: " + path + "/" + writeFileName,
					Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
