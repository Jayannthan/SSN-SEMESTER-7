package com.example.exercise8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	IntentFilter intentFilter;

	private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			LinearLayout sms = (LinearLayout) findViewById(R.id.sms);
			TextView newSMS = new TextView(getApplicationContext());
			String msg = intent.getExtras().getString("message");
			newSMS.setText(msg);
			newSMS.setGravity(Gravity.RIGHT);
			sms.addView(newSMS);
			makeNotification(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		intentFilter = new IntentFilter();
		intentFilter.addAction("SMS_RECEIVED_ACTION");

		EditText contact = (EditText) findViewById(R.id.contact);
		EditText message = (EditText) findViewById(R.id.message);

		Button send = (Button) findViewById(R.id.send);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
			if (ContextCompat.checkSelfPermission(MainActivity.this,
					Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
				ActivityCompat.requestPermissions(MainActivity.this,
						new String[] { Manifest.permission.POST_NOTIFICATIONS }, 101);
			}
		}

		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String msg = message.getText().toString();
				String contactno = contact.getText().toString();
				LinearLayout sms = (LinearLayout) findViewById(R.id.sms);
				TextView newSMS = new TextView(getApplicationContext());
				newSMS.setText(msg);
				sms.addView(newSMS);
				sendMsg(contactno, msg);
			}
		});
	}

	protected void sendMsg(String contactno, String msg) {
		String SENT = "Message Sent";
		String DELIVERED = "Message Delivered";

		PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), PendingIntent.FLAG_IMMUTABLE);
		PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED),
				PendingIntent.FLAG_IMMUTABLE);

		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(contactno, null, msg, sentPI, deliveredPI);
	}

	@Override
	protected void onResume() {
		registerReceiver(intentReceiver, intentFilter);
		super.onResume();
	}

	@Override
	protected void onPause() {
		unregisterReceiver(intentReceiver);
		super.onPause();
	}

	public void makeNotification(String msg) {
		String channelID = "CHANNEL_ID_NOTIFICATION";

		Intent activityIntent = new Intent(this, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, PendingIntent.FLAG_IMMUTABLE);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelID);
		builder.setSmallIcon(R.drawable.ic_notifications)
				.setContentTitle("Notification")
				.setContentText(msg)
				.setAutoCancel(true)
				.setPriority(NotificationCompat.PRIORITY_DEFAULT)
				.setContentIntent(contentIntent);

		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channelID);
			if (notificationChannel == null) {
				int importance = NotificationManager.IMPORTANCE_HIGH;
				notificationChannel = new NotificationChannel(channelID, "Notification", importance);
				notificationChannel.setLightColor(Color.GREEN);
				notificationChannel.enableVibration(true);
				notificationManager.createNotificationChannel(notificationChannel);
			}
		}
		notificationManager.notify(0, builder.build());
	}
}
