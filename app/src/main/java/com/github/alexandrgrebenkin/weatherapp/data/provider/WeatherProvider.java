package com.github.alexandrgrebenkin.weatherapp.data.provider;

import android.location.Address;

import com.github.alexandrgrebenkin.weatherapp.data.entity.CurrentWeather;
import com.github.alexandrgrebenkin.weatherapp.data.entity.ForecastWeather;

public interface WeatherProvider {
    CurrentWeather getCurrentWeather(Address address);

    ForecastWeather getForecastWeather(Address address);
}
