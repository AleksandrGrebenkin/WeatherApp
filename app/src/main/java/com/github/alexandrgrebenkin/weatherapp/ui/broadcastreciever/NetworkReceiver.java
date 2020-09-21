package com.github.alexandrgrebenkin.weatherapp.ui.broadcastreciever;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

import androidx.core.app.NotificationCompat;

import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.ui.activity.BaseActivity;

public class NetworkReceiver extends BroadcastReceiver {

    private final int wifiNotificationId = 1000;

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_DISABLED) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context,
                    BaseActivity.SystemNotificationChanelId)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentText(context.getResources().getString(R.string.network_connection_notification))
                    .setContentText(context.getResources().getString(R.string.wifi_connection_disabled));
            notificationManager.notify(wifiNotificationId, builder.build());
        } else {
            notificationManager.cancel(wifiNotificationId);
        }
    }

}
