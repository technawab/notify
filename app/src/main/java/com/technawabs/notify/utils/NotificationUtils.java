package com.technawabs.notify.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.RemoteMessage;
import com.technawabs.notify.R;
import com.technawabs.notify.activities.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class NotificationUtils {

    public static final String TAG = NotificationUtils.class.getSimpleName();
    public static final int NOTIFICATION_ID = 100;
    public static final int icon = R.mipmap.ic_launcher;

    public static void showNotification(Bundle extras, Context context) {
        makeNotification(extras, context, "gcm.notification.icon", extras.getString("gcm.notification.title"), extras.getString("gcm.notification.body"));
    }

    public static void showBigTextStyleNotification(Context context, RemoteMessage remoteMessage) {
        Bundle bundle = new Bundle();
        if (remoteMessage != null) {
            Log.d(TAG, "Payload is not null");
//            if (remoteMessage.getData().get(MyRefersConstants.JobDetailConstants.JOB_ID) != null) {
//                Long jobId = Long.valueOf(remoteMessage.getData().get(MyRefersConstants.JobDetailConstants.JOB_ID));
//                bundle.putLong(MyRefersConstants.JobDetailConstants.JOB_ID, jobId);
//            }
//            if (remoteMessage.getData().get(MyRefersConstants.JobDetailConstants.JOB_TITLE) != null) {
//                bundle.putString(MyRefersConstants.JobDetailConstants.JOB_TITLE, remoteMessage.getData().get(MyRefersConstants.JobDetailConstants.JOB_TITLE));
//            }
//            if (remoteMessage.getData().get(MyRefersConstants.JobDetailConstants.JOB_COMPANY) != null) {
//                bundle.putString(MyRefersConstants.JobDetailConstants.JOB_TITLE, remoteMessage.getData().get(MyRefersConstants.JobDetailConstants.JOB_TITLE));
//            }
//            if (remoteMessage.getData().get(MyRefersConstants.JobDetailConstants.JOB_LOCATION) != null) {
//                bundle.putString(MyRefersConstants.JobDetailConstants.JOB_LOCATION, remoteMessage.getData().get(MyRefersConstants.JobDetailConstants.JOB_LOCATION));
//            }
            makeNotification(bundle, context, remoteMessage.getNotification().getIcon(), remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }
    }

    private static void makeNotification(Bundle bundle, Context context, String imageUrl, String title, String body) {
        Bitmap remotePicture = null;
        Bitmap mutableBitmap = null;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.BigPictureStyle notificationStyle = new NotificationCompat.BigPictureStyle();
        notificationStyle.setSummaryText(body);
        Canvas canvas = null;
        try {
            remotePicture = BitmapFactory.decodeStream((InputStream) new URL(imageUrl).getContent());
            mutableBitmap = remotePicture.copy(Bitmap.Config.ARGB_8888, true);
            mutableBitmap.eraseColor(Color.WHITE);
            canvas = new Canvas(mutableBitmap);
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(remotePicture, 0f, 0f, null);
        } catch (IOException e) {
            Log.d(TAG, "IOException");
        }
        notificationStyle.bigPicture(mutableBitmap);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent gotoIntent;
        if (!bundle.isEmpty()) {
            Log.d(TAG, "Payload is not null");
            gotoIntent = new Intent(context, MainActivity.class);
            gotoIntent.putExtras(bundle);
            gotoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } else {
            Log.d(TAG, "Payload is null");
            gotoIntent = new Intent(context, MainActivity.class);
            gotoIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, gotoIntent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_UPDATE_CURRENT);
        context.startActivity(gotoIntent);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        Notification notification = mBuilder.setSmallIcon(icon).setTicker(title)
                .setWhen(0)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentIntent(contentIntent)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentText(body)
                .setStyle(notificationStyle)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .build();
        notificationManager.notify(NOTIFICATION_ID, notification);
        System.exit(0);
    }

}
