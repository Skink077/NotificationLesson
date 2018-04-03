package com.example.kamal.notificationlesson;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATION_ID = 525252;
    private static final String EXTRA_ITEM_ID = "12314";
    private String channelId = "Kamalov";
    private long itemId = 12345678910L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button notificationButton = findViewById(R.id.am_notification_bnt);
        TextView textView = findViewById(R.id.am_text_txt);
        notificationButton.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                    intent, PendingIntent.FLAG_UPDATE_CURRENT);
            intent.putExtra(EXTRA_ITEM_ID, itemId);

            TaskStackBuilder stackBuilder = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                stackBuilder = TaskStackBuilder.create(this);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                stackBuilder.addParentStack(MainActivity.class);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                stackBuilder.addNextIntent(intent);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_action_name)
                    .setColor(ContextCompat.getColor(this, R.color.colorAccent))
                    .setContentTitle("Text")
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setContentText("message");
            Notification notification = builder.build();
            NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelId, "11111", NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);
            }
            notificationManager.notify(1, notification);
        });
    }
}
