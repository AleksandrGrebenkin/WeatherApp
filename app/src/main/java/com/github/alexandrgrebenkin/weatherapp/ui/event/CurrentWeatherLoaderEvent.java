package com.github.alexandrgrebenkin.weatherapp.ui.event;

import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.CurrentWeatherViewModel;

public class CurrentWeatherLoaderEvent {
    private CurrentWeatherViewModel currentWeatherViewModel;

    public CurrentWeatherLoaderEvent(CurrentWeatherViewModel currentWeatherViewModel) {
        this.currentWeatherViewModel = currentWeatherViewModel;
    }

    public CurrentWeatherViewModel getCurrentWeatherViewModel() {
        return currentWeatherViewModel;
    }
}
