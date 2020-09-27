package com.github.alexandrgrebenkin.weatherapp.data.provider;

import android.location.Address;

import com.github.alexandrgrebenkin.weatherapp.data.entity.WeatherInfo;

public interface WeatherProvider {
    void loadWeatherInfo(Address address, WeatherInfoListener weatherInfoListener);

    interface WeatherInfoListener {
        void onLoad(WeatherInfo weatherInfo);
    }
}
