package com.github.alexandrgrebenkin.weatherapp.Interface;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.alexandrgrebenkin.weatherapp.Data.WeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.Data.WeatherInfoProvider;
import com.github.alexandrgrebenkin.weatherapp.R;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CITY_CODE_ACTIVITY = 1;
    final static String WEATHER_INFO = "com.github.alexandrgrebenkin.weatherapp.WEATHER_INFO";

    private TextView city;
    private TextView temperature;
    private TextView wind;
    private TextView pressure;

    private ImageButton citiesList;
    private ImageButton settings;

    private WeatherInfo weatherInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        startCitiesClickListener();
        startSettingsClickListener();
        startCityClickListener();
        updateWeather();
    }

    private void initialize() {
        city = findViewById(R.id.activity_main__tv_city);
        temperature = findViewById(R.id.activity_main__tv_temperature);
        wind = findViewById(R.id.activity_main__tv_wind);
        pressure = findViewById(R.id.activity_main__tv_pressure);
        citiesList = findViewById(R.id.activity_main__ib_citiesList);
        settings = findViewById(R.id.activity_main__ib_settings);
    }

    private void startCitiesClickListener() {
        citiesList.setOnClickListener((v) -> {
            Intent intent = new Intent(getApplicationContext(), CitiesActivity.class);
            startActivityForResult(intent, REQUEST_CITY_CODE_ACTIVITY);
        });
    }

    private void startSettingsClickListener() {
        settings.setOnClickListener((v) -> {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        });
    }

    private void startCityClickListener() {
        city.setOnClickListener((v) -> {
            String url = "https://yandex.ru/search/?text=" + city.getText().toString();
            Uri uri = Uri.parse(url);
            Intent browser = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browser);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CITY_CODE_ACTIVITY && resultCode == RESULT_OK) {
            weatherInfo = data.getParcelableExtra(WEATHER_INFO);
            updateViewData();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateWeather() {
        WeatherInfoProvider weatherInfoProvider = new WeatherInfoProvider();
        weatherInfo = weatherInfoProvider.getWeatherInfo("Example City");
        updateViewData();
    }

    private void updateViewData() {
        city.setText(weatherInfo.getCityName());
        temperature.setText(weatherInfo.getTemperature());
        wind.setText(weatherInfo.getWind());
        pressure.setText(weatherInfo.getPressure());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        weatherInfo = savedInstanceState.getParcelable(WEATHER_INFO);
        updateViewData();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(WEATHER_INFO, weatherInfo);
    }
}
