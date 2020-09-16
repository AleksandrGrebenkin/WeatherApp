package com.github.alexandrgrebenkin.weatherapp.data.entity;

public class CurrentWeather {
    private String cityName;
    private float tempCelsius;
    private float windSpeedMS;
    private float pressureMm;
    private WeatherCondition weatherCondition;

    public String getCityName() {
        return cityName;
    }

    public float getTempCelsius() {
        return tempCelsius;
    }

    public float getWindSpeedMS() {
        return windSpeedMS;
    }

    public float getPressureMm() {
        return pressureMm;
    }

    public WeatherCondition getWeatherCondition() {
        return weatherCondition;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setTempCelsius(float tempCelsius) {
        this.tempCelsius = tempCelsius;
    }

    public void setWindSpeedMS(float windSpeedMS) {
        this.windSpeedMS = windSpeedMS;
    }

    public void setPressureMm(float pressureMm) {
        this.pressureMm = pressureMm;
    }

    public void setWeatherCondition(WeatherCondition weatherCondition) {
        this.weatherCondition = weatherCondition;
    }
}
