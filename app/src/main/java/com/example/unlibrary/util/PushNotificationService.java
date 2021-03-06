/*
 * PushNotificationService
 *
 * November 22, 2020
 *
 * Copyright (c) Team 24, Fall2020, CMPUT301, University of Alberta
 */

package com.example.unlibrary.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.unlibrary.MainActivity;
import com.example.unlibrary.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

/**
 * Handles incoming message from FCM
 */
public class PushNotificationService extends FirebaseMessagingService {

    private final String CHANNEL_ID = "admin_channel";

    /**
     * Handles the incoming message.
     *
     * @param remoteMessage the message begin handled
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // https://blog.usejournal.com/send-device-to-device-push-notifications-without-server-side-code-238611c143
        NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        int notificationID = new Random().nextInt(3000);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            setupChannels(notificationManager);
        }
        
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_add)
                .setContentTitle(remoteMessage.getData().get("title"))
                .setContentText(remoteMessage.getData().get("message"))
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_MAX);

        notificationManager.notify(notificationID, notification.build());
    }

    /**
     * Create notification channel
     *
     * @param notificationManager the notification
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupChannels(NotificationManager notificationManager){
        CharSequence name = "New notification";
        String description = "Device to device notification";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        channel.setShowBadge(true);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        channel.enableVibration(true);
        
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(channel);
        }
    }
}
