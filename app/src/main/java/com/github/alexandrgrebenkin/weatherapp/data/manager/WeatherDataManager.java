package com.github.alexandrgrebenkin.weatherapp.data.manager;

import com.github.alexandrgrebenkin.weatherapp.data.entity.Place;
import com.github.alexandrgrebenkin.weatherapp.data.provider.WeatherProvider;
import com.github.alexandrgrebenkin.weatherapp.data.implementation.webservice.weather.InternetWeatherProvider;
import com.github.alexandrgrebenkin.weatherapp.data.entity.CurrentWeather;
import com.github.alexandrgrebenkin.weatherapp.data.entity.ForecastWeather;

public class WeatherDataManager {
    private WeatherProvider weatherProvider = new InternetWeatherProvider();

    public CurrentWeather getCurrentWeather(Place place){
        return weatherProvider.getCurrentWeather(place);
    }

    public ForecastWeather getForecastWeather(Place place) {
        return weatherProvider.getForecastWeather(place);
    }
}
