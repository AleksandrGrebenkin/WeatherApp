package com.github.alexandrgrebenkin.weatherapp.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ForecastWeatherRequest {
    @SerializedName("Days")
    List<DayWeatherInfo> Days = new ArrayList<>();

    public List<DayWeatherInfo> getDays() {
        return Days;
    }

    public void setDays(List<DayWeatherInfo> days) {
        Days = days;
    }
}
