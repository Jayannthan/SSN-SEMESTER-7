package com.example.alarm;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.example.alarm.databinding.ActivityMainBinding;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
	private ActivityMainBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		createNotificationChannel();
		ToggleButton b1 = findViewById(R.id.set);
		TimePicker t1 = findViewById(R.id.time);
		t1.setIs24HourView(true);
		b1.setOnCheckedChangeListener((buttonView, isChecked) -> {
			if (isChecked) {
				setAlarm();
			} else {
				cancelAlarm();
			}
		});
	}

	private void createNotificationChannel() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			CharSequence name = "alarm";
			String desc = "Channel for alarm";
			int importance = NotificationManager.IMPORTANCE_HIGH;
			NotificationChannel channel = new NotificationChannel("alarm", name, importance);
			channel.setDescription(desc);
			NotificationManager notifs = getSystemService(NotificationManager.class);
			notifs.createNotificationChannel(channel);
		}
	}

	public void setAlarm() {
		TimePicker timePicker = findViewById(R.id.time);
		AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
		Intent intent = new Intent(this, AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 234, intent,
				PendingIntent.FLAG_IMMUTABLE);
		int hour = timePicker.getHour();
		int minute = timePicker.getMinute();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, 0);
		long triggerTime = calendar.getTimeInMillis();
		int timeInSec = 1;
		alarm.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
		Toast.makeText(this, "Alarm set for " + hour + ":" + minute, Toast.LENGTH_SHORT).show();
	}

	public void cancelAlarm() {
		AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
		Intent intent = new Intent(this, AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 234, intent,
				PendingIntent.FLAG_IMMUTABLE);
		if (alarm != null) {
			alarm.cancel(pendingIntent);
		}
		Toast.makeText(this, "Alarm unset!", Toast.LENGTH_SHORT).show();
	}
}
