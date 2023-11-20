package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

// Create a new class for the BroadcastReceiver
public class SmsReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "SMS_CHANNEL_ID";

    @Override
    public void onReceive(Context context, Intent intent) {
        // Display a Toast message when an SMS is received
        Toast.makeText(context, "Received SMS", Toast.LENGTH_SHORT).show();

        // Check if the received intent is an SMS
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            // Process the received SMS
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                // Extract SMS messages from the intent
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus != null) {
                    for (Object pdu : pdus) {
                        SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                        // Extract sender and message content
                        String sender = smsMessage.getDisplayOriginatingAddress();
                        String message = smsMessage.getMessageBody();
                        // Create and show a notification
                        createNotification(context, "New SMS from " + sender, message);
                    }
                }
            }
        }
    }

    // Method to create and show a notification
    private void createNotification(Context context, String title, String content) {
        // Create a notification channel for devices running Android Oreo and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "SMS Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        // Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info) // Set the notification icon
                .setContentTitle(title) // Set the notification title
                .setContentText(content) // Set the notification content
                .setPriority(NotificationCompat.PRIORITY_DEFAULT); // Set the notification priority

        // Show the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, builder.build()); // Use a unique notification ID (1 in this case)
    }
}
