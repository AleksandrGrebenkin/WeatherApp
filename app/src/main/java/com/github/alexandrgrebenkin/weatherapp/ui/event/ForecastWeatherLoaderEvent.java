package com.github.alexandrgrebenkin.weatherapp.ui.event;

import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.ForecastWeatherViewModel;

public class ForecastWeatherLoaderEvent {
    private ForecastWeatherViewModel forecastWeatherViewModel;

    public ForecastWeatherLoaderEvent(ForecastWeatherViewModel forecastWeatherViewModel) {
        this.forecastWeatherViewModel = forecastWeatherViewModel;
    }

    public ForecastWeatherViewModel getForecastWeatherViewModel() {
        return forecastWeatherViewModel;
    }
}
