package com.github.alexandrgrebenkin.weatherapp.ui.viewmodel;

import java.util.List;

public class ForecastWeatherViewModel {
    private List<DayWeatherViewModel> dayWeatherViewModelList;

    public List<DayWeatherViewModel> getDayWeatherViewModelList() {
        return dayWeatherViewModelList;
    }

    public void setDayWeatherViewModelList(List<DayWeatherViewModel> dayWeatherViewModelList) {
        this.dayWeatherViewModelList = dayWeatherViewModelList;
    }
}
