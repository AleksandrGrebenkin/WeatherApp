package com.github.alexandrgrebenkin.weatherapp.data.entity;

public class WeatherInfo {
    private CurrentWeather currentWeather;
    private ForecastWeather forecastWeather;

    public WeatherInfo(CurrentWeather currentWeather, ForecastWeather forecastWeather) {
        this.currentWeather = currentWeather;
        this.forecastWeather = forecastWeather;
    }

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    public ForecastWeather getForecastWeather() {
        return forecastWeather;
    }
}
