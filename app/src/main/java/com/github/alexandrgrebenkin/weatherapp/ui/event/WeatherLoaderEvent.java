package com.github.alexandrgrebenkin.weatherapp.ui.event;

import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.WeatherViewModel;

public class WeatherLoaderEvent {
    private WeatherViewModel weatherViewModel;

    public WeatherLoaderEvent(WeatherViewModel weatherViewModel) {
        this.weatherViewModel = weatherViewModel;
    }

    public WeatherViewModel getWeatherViewModel() {
        return weatherViewModel;
    }
}
