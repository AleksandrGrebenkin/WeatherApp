package com.github.alexandrgrebenkin.weatherapp.data.entity;

import java.time.LocalDate;

public class DayWeather {
    private LocalDate date;
    private float maxTempCelsius;
    private float minTempCelsius;

    public LocalDate getDate() {
        return date;
    }

    public float getMaxTempCelsius() {
        return maxTempCelsius;
    }

    public float getMinTempCelsius() {
        return minTempCelsius;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setMaxTempCelsius(float maxTempCelsius) {
        this.maxTempCelsius = maxTempCelsius;
    }

    public void setMinTempCelsius(float minTempCelsius) {
        this.minTempCelsius = minTempCelsius;
    }
}
