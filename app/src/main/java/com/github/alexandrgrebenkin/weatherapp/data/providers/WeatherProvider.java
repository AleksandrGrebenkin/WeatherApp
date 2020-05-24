package com.github.alexandrgrebenkin.weatherapp.data.providers;

import com.github.alexandrgrebenkin.weatherapp.data.CurrentWeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.data.DayWeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.data.PlaceInfo;

import java.util.List;

public interface WeatherProvider {
    CurrentWeatherInfo getCurrentWeatherInfo(PlaceInfo placeInfo);
    List<DayWeatherInfo> getForecastWeatherInfo(PlaceInfo placeInfo);
}
