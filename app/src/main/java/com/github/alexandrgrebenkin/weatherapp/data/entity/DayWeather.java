package com.github.alexandrgrebenkin.weatherapp.data.entity;

import java.time.LocalDate;
import java.util.Date;

public class DayWeather {
    private Date date;
    private float maxTempCelsius;
    private float minTempCelsius;

    public Date getDate() {
        return date;
    }

    public float getMaxTempCelsius() {
        return maxTempCelsius;
    }

    public float getMinTempCelsius() {
        return minTempCelsius;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMaxTempCelsius(float maxTempCelsius) {
        this.maxTempCelsius = maxTempCelsius;
    }

    public void setMinTempCelsius(float minTempCelsius) {
        this.minTempCelsius = minTempCelsius;
    }
}
