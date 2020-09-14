package com.github.alexandrgrebenkin.weatherapp.data.provider;

import com.github.alexandrgrebenkin.weatherapp.data.entity.WeatherInfo;

public interface WeatherInfoListener {
    void onLoad(WeatherInfo weatherInfo);
}
