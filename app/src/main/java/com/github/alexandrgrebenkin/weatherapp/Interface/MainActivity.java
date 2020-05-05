package com.github.alexandrgrebenkin.weatherapp.Interface;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.github.alexandrgrebenkin.weatherapp.Data.WeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.R;

public class MainActivity extends AppCompatActivity implements WeatherInfoFragment.Listener, CitiesListFragment.Listener {

    private static final int REQUEST_CITY_CODE_ACTIVITY = 1;

    private WeatherInfoFragment weatherInfoFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherInfoFragment = (WeatherInfoFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_main__fragment_weather_info);
    }

    public void onCitiesListClicked() {
        Intent intent = new Intent(getApplicationContext(), CitiesActivity.class);
        startActivityForResult(intent, REQUEST_CITY_CODE_ACTIVITY);
    }

    public void onSettingsClicked() {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }

    public void onCityClicked(String cityName) {
        String url = "https://yandex.ru/search/?text=" + cityName;
        Uri uri = Uri.parse(url);
        Intent browser = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(browser);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CITY_CODE_ACTIVITY && resultCode == RESULT_OK) {
            WeatherInfo weatherInfo = data.getParcelableExtra(WeatherInfoFragment.WEATHER_INFO);
            weatherInfoFragment.updateWeatherInfo(weatherInfo);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void pushWeatherInfo(WeatherInfo weatherInfo) {
        weatherInfoFragment.updateWeatherInfo(weatherInfo);
    }
}
