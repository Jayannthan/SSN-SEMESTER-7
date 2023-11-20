package com.example.alarm;

import static androidx.core.content.ContextCompat.startActivity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {
	static Uri alarmrt1 = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "INSIDE WOHOOO", Toast.LENGTH_LONG).show();
		Intent i = new Intent(context, In.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
				Intent.FLAG_ACTIVITY_CLEAR_TASK);
		PendingIntent p = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_IMMUTABLE);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "alarm")
				.setSmallIcon(R.drawable.ic_launcher_foreground)
				.setContentTitle("Your Alarm is going off!!!")
				.setContentInfo("You set this alarm!")
				.setAutoCancel(true)
				.setDefaults(NotificationCompat.DEFAULT_ALL)
				.setPriority(NotificationCompat.PRIORITY_HIGH)
				.setContentIntent(p);
		NotificationManagerCompat notifications = NotificationManagerCompat.from(context);
		if (ActivityCompat.checkSelfPermission(context,
				android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
			return;
		} else {
			notifications.notify(123, builder.build());
		}
		Ringtone ringtone = RingtoneManager.getRingtone(context.getApplicationContext(), alarmrt1);
		// Toast.makeText(context, ringtone.toString(), Toast.LENGTH_SHORT).show();
		intent.putExtra("RINGTONE_URI", alarmrt1);
		ringtone.play();
	}

	public static Uri getInstant() {
		return alarmrt1;
	}
}
