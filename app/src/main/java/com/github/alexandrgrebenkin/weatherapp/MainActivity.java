package com.github.alexandrgrebenkin.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

        if (savedInstanceState == null){
            showInfo("On Create: New Activity");
        } else {
            showInfo("On Create: Old Activity");
        }

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

    private void showInfo(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        Log.d("LIFECYCLE_LOG", message);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showInfo("On Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showInfo("On Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        showInfo("On Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showInfo("On Stop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showInfo("On Restart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showInfo("On Destroy");
    }
}
