package com.example.exercise7;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;

public class ReadActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read);

		TextView contentsFromFile = (TextView) findViewById(R.id.contentsFromFile);

		Intent i = getIntent();

		String readFileName = i.getStringExtra("fileName");
		String readFileContents = readFromFile(readFileName);
		contentsFromFile.setText(readFileContents);
	}

	private String readFromFile(String readFileName) {
		File path = getApplicationContext().getFilesDir();
		File readFrom = new File(path + "/" + readFileName);
		byte[] content = new byte[(int) readFrom.length()];

		try {
			FileInputStream reader = new FileInputStream(readFrom);
			reader.read(content);
			return new String(content);
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}
}
