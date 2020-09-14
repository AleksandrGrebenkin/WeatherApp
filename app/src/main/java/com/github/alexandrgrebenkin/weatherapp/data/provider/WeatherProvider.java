package com.github.alexandrgrebenkin.weatherapp.data.provider;

import android.location.Address;

public interface WeatherProvider {
    void loadWeatherInfo(Address address, WeatherInfoListener weatherInfoListener);
}
