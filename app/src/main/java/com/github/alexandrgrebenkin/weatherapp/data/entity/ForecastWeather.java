package com.github.alexandrgrebenkin.weatherapp.data.entity;

import java.util.List;

public class ForecastWeather {
    private List<DayWeather> dayWeatherList;

    public List<DayWeather> getDayWeatherList() {
        return dayWeatherList;
    }

    public void setDayWeatherList(List<DayWeather> dayWeatherList) {
        this.dayWeatherList = dayWeatherList;
    }
}
