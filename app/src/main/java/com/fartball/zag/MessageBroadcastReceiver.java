package com.fartball.zag;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;


public class MessageBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int type = intent.getIntExtra(MessagesHelper.TYPE_EXTRA, 0);

        Intent intentToRepeat = new Intent(context, MainActivity.class);
        intentToRepeat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, type, intentToRepeat, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager nm = new MessagesHelper().getNotificationManager(context);
        Notification notification = buildNotification(context, pendingIntent, nm).build();
        nm.notify(type, notification);

    }


    public NotificationCompat.Builder buildNotification(Context context, PendingIntent pendingIntent, NotificationManager nm) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default",
                    "Daily Notification",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Daily Notification");
            if (nm != null) {
                nm.createNotificationChannel(channel);
            }
        }


        return new NotificationCompat.Builder(context, "default")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setContentTitle(context.getResources().getString(R.string.push))
                .setStyle(new NotificationCompat.BigPictureStyle().setBigContentTitle(context.getResources().getString(R.string.push)))
                .setAutoCancel(true);
    }
}
