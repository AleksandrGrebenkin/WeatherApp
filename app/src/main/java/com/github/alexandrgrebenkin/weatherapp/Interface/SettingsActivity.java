package com.github.alexandrgrebenkin.weatherapp.Interface;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.alexandrgrebenkin.weatherapp.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
}
