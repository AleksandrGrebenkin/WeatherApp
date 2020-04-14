package com.github.alexandrgrebenkin.weatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Switch;

import androidx.annotation.NonNull;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}
