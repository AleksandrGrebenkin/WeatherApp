package com.github.alexandrgrebenkin.weatherapp.data.rest.weatherunlocked;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class ForecastWeatherRequest {
    @SerializedName("Days")
    private List<DayWeatherRequest> Days = new ArrayList<>();

    List<DayWeatherRequest> getDays() {
        return Days;
    }

    void setDays(List<DayWeatherRequest> days) {
        Days = days;
    }
}
