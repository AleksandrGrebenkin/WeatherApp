package com.github.alexandrgrebenkin.weatherapp.ui.viewmodel;

public class WeatherViewModel {
    private CurrentWeatherViewModel currentWeatherViewModel;
    private ForecastWeatherViewModel forecastWeatherViewModel;

    public WeatherViewModel(CurrentWeatherViewModel currentWeatherViewModel,
                            ForecastWeatherViewModel forecastWeatherViewModel) {
        this.currentWeatherViewModel = currentWeatherViewModel;
        this.forecastWeatherViewModel = forecastWeatherViewModel;
    }

    public CurrentWeatherViewModel getCurrentWeatherViewModel() {
        return currentWeatherViewModel;
    }

    public ForecastWeatherViewModel getForecastWeatherViewModel() {
        return forecastWeatherViewModel;
    }
}
