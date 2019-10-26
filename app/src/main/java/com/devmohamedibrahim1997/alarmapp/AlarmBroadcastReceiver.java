package com.devmohamedibrahim1997.alarmapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import static android.app.NotificationManager.IMPORTANCE_HIGH;
import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    private int alarmId;

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, RingtoneService.class));
        if(intent.getExtras() != null) {
            alarmId = intent.getExtras().getInt("alarmId", 0);
        }

        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(context);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "alarm_channel");
        builder.setSmallIcon(R.drawable.alarm_icon);
        builder.setColor(ContextCompat.getColor(context, android.R.color.background_dark));
        builder.setContentTitle(context.getString(R.string.app_name));
        builder.setContentText("Wake up!!");
        builder.setTicker("Wake up!!");
        builder.setVibrate(new long[]{1000, 500, 1000, 500, 1000, 500});
        builder.setContentIntent(PendingIntent.getActivity(context, alarmId, intent, FLAG_UPDATE_CURRENT));
        builder.setPriority(Notification.PRIORITY_HIGH);

        manager.notify(alarmId, builder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void createNotificationChannel(Context context) {

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        if (notificationManager == null) return;

        String name = "Simple Alarms";
        if (notificationManager.getNotificationChannel(name) == null) {
            NotificationChannel channel = new NotificationChannel("alarm_channel", name, IMPORTANCE_HIGH);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 500, 1000, 500, 1000, 500});
            channel.setBypassDnd(true);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
