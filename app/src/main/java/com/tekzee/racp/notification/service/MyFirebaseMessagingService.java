package com.tekzee.racp.notification.service;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.tekzee.racp.R;
import com.tekzee.racp.notification.Config;
import com.tekzee.racp.notification.NotificationUtils;
import com.tekzee.racp.ui.home.HomeActivity;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    private NotificationUtils notificationUtils;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage == null)
            return;


        if (remoteMessage.getData().size() > 0) {
            try {
                Map <String, String> messageDataPayload = remoteMessage.getData();
                handleDataMessage(messageDataPayload);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void handleDataMessage(Map <String, String> json) {
        try {
            String message = json.get("message");
            if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
                // app is in foreground, broadcast the push message
                Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
                pushNotification.putExtra("message", json.get("message"));
                pushNotification.putExtra("type", json.get("type"));
                pushNotification.putExtra("bookingid", json.get("bookingid"));
                LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

                // play notification sound
                NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
                notificationUtils.playNotificationSound();

                // app is in foreground showing notification as well as broadcasting
                Intent resultIntent = new Intent(getApplicationContext(), HomeActivity.class);
                resultIntent.putExtra("message", message);
                showNotificationMessage(getApplicationContext(), getResources().getString(R.string.app_name), message, "" + System.currentTimeMillis(), resultIntent);
            } else {
                // app is in background, show the notification in notification tray
                Intent resultIntent = new Intent(getApplicationContext(), HomeActivity.class);
                resultIntent.putExtra("message", message);
                showNotificationMessage(getApplicationContext(), getResources().getString(R.string.app_name), message, "" + System.currentTimeMillis(), resultIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }

}