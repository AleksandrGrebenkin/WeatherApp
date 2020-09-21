package com.github.alexandrgrebenkin.weatherapp.ui.service;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PushesService extends FirebaseMessagingService {

    private String FirebaseTAG = "FirebaseTAG";
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

        Log.d(FirebaseTAG, "Refreshed token: " + s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d(FirebaseTAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(FirebaseTAG, "Message data payload: " + remoteMessage.getData());

            String message = remoteMessage.getNotification().getTitle();
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

        if (remoteMessage.getNotification() != null) {
            Log.d(FirebaseTAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }
}
