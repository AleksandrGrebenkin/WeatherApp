package com.github.alexandrgrebenkin.weatherapp.data.provider;

import com.github.alexandrgrebenkin.weatherapp.data.entity.Place;
import com.github.alexandrgrebenkin.weatherapp.data.entity.CurrentWeather;
import com.github.alexandrgrebenkin.weatherapp.data.entity.ForecastWeather;

public interface WeatherProvider {
    CurrentWeather getCurrentWeather(Place place);

    ForecastWeather getForecastWeather(Place place);
}
