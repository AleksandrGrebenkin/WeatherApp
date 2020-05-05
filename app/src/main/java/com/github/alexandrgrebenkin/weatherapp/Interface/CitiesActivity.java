package com.github.alexandrgrebenkin.weatherapp.Interface;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.alexandrgrebenkin.weatherapp.Data.WeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.R;

public class CitiesActivity extends AppCompatActivity implements CitiesListFragment.Listener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
    }

    @Override
    public void pushWeatherInfo(WeatherInfo weatherInfo) {
        Intent intentResult = new Intent();
        intentResult.putExtra(WeatherInfoFragment.WEATHER_INFO, weatherInfo);
        setResult(RESULT_OK, intentResult);
        finish();
    }
}
