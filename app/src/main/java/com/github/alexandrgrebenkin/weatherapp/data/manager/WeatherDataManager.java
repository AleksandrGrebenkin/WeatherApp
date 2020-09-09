package com.github.alexandrgrebenkin.weatherapp.data.manager;

import android.location.Address;

import com.github.alexandrgrebenkin.weatherapp.data.provider.WeatherProvider;
import com.github.alexandrgrebenkin.weatherapp.data.webservice.weatherunlocked.InternetWeatherProvider;
import com.github.alexandrgrebenkin.weatherapp.data.entity.CurrentWeather;
import com.github.alexandrgrebenkin.weatherapp.data.entity.ForecastWeather;

public class WeatherDataManager {
    private WeatherProvider weatherProvider = new InternetWeatherProvider();

    public CurrentWeather getCurrentWeather(Address address){
        return weatherProvider.getCurrentWeather(address);
    }

    public ForecastWeather getForecastWeather(Address address) {
        return weatherProvider.getForecastWeather(address);
    }
}
