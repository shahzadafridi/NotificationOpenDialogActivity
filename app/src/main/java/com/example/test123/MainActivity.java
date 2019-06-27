package com.example.test123;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void sendNotification(View view){
         sendMeNotification("Hell this is notification.");
    }

    public void sendMeNotification(String message) {

        NotificationManager manager = (NotificationManager)  getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder notification = null;
        Intent main = new Intent(this, AboutActivity.class);
        main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 001, main, PendingIntent.FLAG_ONE_SHOT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(this.getString(R.string.channel_id), this.getString(R.string.channel_name), importance);
            channel.setDescription("It's a personal channel");
            channel.enableVibration(false);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            manager.createNotificationChannel(channel);
            notification = new NotificationCompat.Builder(this, channel.getId());
        } else {
            notification = new NotificationCompat.Builder(this, this.getString(R.string.channel_id));
        }
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notification.setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(Notification.PRIORITY_HIGH)
                .setContentTitle(this.getString(R.string.app_name))
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setAutoCancel(true)
                .setSound(sound)
                .setLights(Color.RED, 200, 200)
                .setContentIntent(pendingIntent);

        manager.notify(0, notification.build());
    }
}
