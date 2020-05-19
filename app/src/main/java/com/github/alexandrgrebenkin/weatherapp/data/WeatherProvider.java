package com.github.alexandrgrebenkin.weatherapp.data;

import java.util.List;

public interface WeatherProvider {
    CurrentWeatherInfo getCurrentWeatherInfo(String cityName);
    List<DayWeatherInfo> getForecastWeatherInfo(String cityName);
}
