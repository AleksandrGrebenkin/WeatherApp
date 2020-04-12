package com.github.alexandrgrebenkin.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView city;
    private TextView temperatureValue;
    private TextView windValue;
    private TextView pressureValue;

    private ImageButton cities;
    private ImageButton settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        startCitiesClickListener();
        startSettingsClickListener();

    }

    private void initialize() {
        city = findViewById(R.id.activity_main__tv_city);
        temperatureValue = findViewById(R.id.activity_main__tv_temperature_value);
        windValue = findViewById(R.id.activity_main__tv_wind_value);
        pressureValue = findViewById(R.id.activity_main__tv_pressure_value);
        cities = findViewById(R.id.activity_main__ib_cities);
        settings = findViewById(R.id.activity_main__ib_settings);
    }

    private void startCitiesClickListener() {
        cities.setOnClickListener((v) -> {
            Intent intent = new Intent(this, CitiesActivity.class);
            startActivity(intent);
        });
    }

    private void startSettingsClickListener() {
        settings.setOnClickListener((v) -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });
    }

}
