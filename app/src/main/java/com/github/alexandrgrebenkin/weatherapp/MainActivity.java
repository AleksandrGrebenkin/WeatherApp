package com.github.alexandrgrebenkin.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout activityMain;

    private TextView city;
    private TextView temperatureValue;

    private Switch darkTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        initDarkThemeSwitchListener();
    }

    private void initialize() {
        activityMain = findViewById(R.id.activity_main__cl_activity_main);
        city = findViewById(R.id.activity_main__tv_city);
        temperatureValue = findViewById(R.id.activity_main__tv_temperature_value);
        darkTheme = findViewById(R.id.activity_main__sw_dark_theme);
    }

    private void initDarkThemeSwitchListener() {
        darkTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setDarkTheme();
                } else {
                    setLightTheme();
                }
            }
        });
    }

    private void setDarkTheme() {
        activityMain.setBackgroundColor(getResources().getColor(R.color.darkTheme));
        city.setTextColor(getResources().getColor(R.color.lightTheme));
        temperatureValue.setTextColor(getResources().getColor(R.color.lightTheme));
        darkTheme.setTextColor(getResources().getColor(R.color.lightTheme));
    }

    private void setLightTheme() {
        activityMain.setBackgroundColor(getResources().getColor(R.color.lightTheme));
        city.setTextColor(getResources().getColor(R.color.darkTheme));
        temperatureValue.setTextColor(getResources().getColor(R.color.darkTheme));
        darkTheme.setTextColor(getResources().getColor(R.color.darkTheme));
    }
}
