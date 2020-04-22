package com.github.alexandrgrebenkin.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class MainActivity extends Activity {

    private TextView city;
    private TextView temperatureValue;
    private TextView windValue;
    private TextView pressureValue;

    private ImageButton cities;
    private ImageButton settings;

    private Button refresh;

    private final String CITY = "CITY";
    private final String TEMPERATURE = "TEMPERATURE";
    private final String WIND = "WIND";
    private final String PRESSURE = "PRESSURE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){

        } else {

        }

        initialize();
        startCitiesClickListener();
        startSettingsClickListener();
        startRefreshClickListener();
    }

    private void initialize() {
        city = findViewById(R.id.activity_main__tv_city);
        temperatureValue = findViewById(R.id.activity_main__tv_temperature_value);
        windValue = findViewById(R.id.activity_main__tv_wind_value);
        pressureValue = findViewById(R.id.activity_main__tv_pressure_value);
        cities = findViewById(R.id.activity_main__ib_cities);
        settings = findViewById(R.id.activity_main__ib_settings);
        refresh = findViewById(R.id.activity_main__btn_refresh);
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

    /*
    * Временная мера для генерации данных
    * */
    private void startRefreshClickListener() {
        refresh.setOnClickListener((v) -> {
            String temperature = Integer.valueOf((int) (Math.random()*60-30)).toString();
            String wind = Integer.valueOf((int) (Math.random()*30)).toString();
            String pressure = Integer.valueOf((int) (Math.random()*100+700)).toString();
            temperatureValue.setText(temperature);
            windValue.setText(wind);
            pressureValue.setText(pressure);
        });
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        city.setText(savedInstanceState.getString(CITY));
        temperatureValue.setText(savedInstanceState.getString(TEMPERATURE));
        windValue.setText(savedInstanceState.getString(WIND));
        pressureValue.setText(savedInstanceState.getString(PRESSURE));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CITY, city.getText().toString());
        outState.putString(TEMPERATURE, temperatureValue.getText().toString());
        outState.putString(WIND, windValue.getText().toString());
        outState.putString(PRESSURE, pressureValue.getText().toString());
    }


}
