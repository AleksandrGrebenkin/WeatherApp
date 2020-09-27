package com.github.alexandrgrebenkin.weatherapp.ui.activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.ui.broadcastreciever.NetworkReceiver;

public class BaseActivity extends AppCompatActivity {

    private static final String NameSharedPreference = "LOGIN";

    private static final String IsDarkTheme = "IS_DARK_THEME";
    private SharedPreferences sharedPreferences;

    public static final String SystemNotificationChanelId = "2";
    private BroadcastReceiver networkReceiver = new NetworkReceiver();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        if (isDarkTheme()) {
            setTheme(R.style.AppDarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }


        registerReceiver(networkReceiver, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
        initNotificationChannel();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(networkReceiver);
        super.onDestroy();
    }

    protected boolean isDarkTheme() {
        return sharedPreferences.getBoolean(IsDarkTheme, true);
    }

    protected void setDarkTheme(boolean isDarkTheme) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IsDarkTheme, isDarkTheme);
        editor.apply();
    }

    private void initNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel channel = new NotificationChannel(SystemNotificationChanelId,
                    "SystemNotification", importance);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}
