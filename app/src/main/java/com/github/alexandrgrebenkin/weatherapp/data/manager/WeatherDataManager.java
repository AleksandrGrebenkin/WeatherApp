package com.github.alexandrgrebenkin.weatherapp.data.manager;

import android.location.Address;

import com.github.alexandrgrebenkin.weatherapp.data.entity.WeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.data.provider.WeatherProvider;
import com.github.alexandrgrebenkin.weatherapp.data.webservice.weatherunlocked.WeatherUnlockedProvider;

public class WeatherDataManager {
    private WeatherProvider weatherProvider = new WeatherUnlockedProvider();

    public WeatherInfo getWeatherInfo(Address address) {
        return weatherProvider.getWeatherInfo(address);
    }
}
