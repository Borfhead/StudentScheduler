package com.example.studentscheduler;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {
    static int notificationId;
    String channel_id = "scheduler_channel";
    @Override
    public void onReceive(Context context, Intent intent) {
        createChannel(context, channel_id);
        String content = "";
        String title = "";
        switch(intent.getStringExtra("NOTIFICATION_TYPE")){
            case "COURSE_START":
                title = "Course begins today";
                content = "A course was scheduled to begin today";
                break;
            case "COURSE_END":
                title = "Course ends today";
                content = "A course was scheduled to end today";
                break;
            case "ASSESSMENT_DUE":
                title = "Assessment goal date today";
                content = "An assessment was scheduled to be taken today";
                break;
        }
        Notification n = new NotificationCompat.Builder(context, channel_id)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(content)
                .setContentTitle(title).build();

        NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId++, n);
    }


    private void createChannel(Context context, String CHANNEL_ID) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getResources().getString(R.string.channel_name);
            String description = context.getResources().getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
