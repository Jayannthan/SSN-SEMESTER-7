package com.example.alarm;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class In extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display);
		Intent intent = getIntent();
		Uri ringtoneUri = intent.getParcelableExtra("RINGTONE_URI");
		Ringtone ringtone = RingtoneManager.getRingtone(In.this, ringtoneUri);
		// Toast.makeText(In.this, ringtone.toString(), Toast.LENGTH_SHORT).show();
		if (ringtone.isPlaying()) {
			// Toast.makeText(In.this, "yes", Toast.LENGTH_SHORT).show(); ringtone.stop();
		} else {
			// Toast.makeText(In.this, "no", Toast.LENGTH_SHORT).show();
		}
	}
}
