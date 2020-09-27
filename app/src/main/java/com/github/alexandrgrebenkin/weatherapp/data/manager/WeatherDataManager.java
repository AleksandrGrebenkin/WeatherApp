package com.github.alexandrgrebenkin.weatherapp.data.manager;

import android.location.Address;


import com.github.alexandrgrebenkin.weatherapp.data.provider.WeatherProvider;
import com.github.alexandrgrebenkin.weatherapp.data.rest.openweathermap.OpenWeatherProvider;
import com.github.alexandrgrebenkin.weatherapp.data.rest.weatherunlocked.WeatherUnlockedProvider;

public class WeatherDataManager {
    private WeatherProvider weatherProvider = new OpenWeatherProvider();

    public void loadWeatherInfo(Address address, WeatherProvider.WeatherInfoListener weatherInfoListener) {
        weatherProvider.loadWeatherInfo(address, weatherInfoListener);
    }


}
