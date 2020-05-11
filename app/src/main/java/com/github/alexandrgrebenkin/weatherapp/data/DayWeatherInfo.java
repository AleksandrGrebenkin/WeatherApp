package com.github.alexandrgrebenkin.weatherapp.data;

import java.time.LocalDate;

public class DayWeatherInfo {
    private LocalDate date;
    private String tempMaxC;
    private String tempMinC;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTempMaxC() {
        return tempMaxC;
    }

    public void setTempMaxC(String tempMaxC) {
        this.tempMaxC = tempMaxC;
    }

    public String getTempMinC() {
        return tempMinC;
    }

    public void setTempMinC(String tempMinC) {
        this.tempMinC = tempMinC;
    }
}
