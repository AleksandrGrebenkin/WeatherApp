package com.github.alexandrgrebenkin.weatherapp.data;

import java.util.List;

public interface WeatherProvider {
    CurrentWeatherInfo getCurrentForecast(String cityName);
    List<DayWeatherInfo> getWeekForecast(String cityName);
}
