package com.github.alexandrgrebenkin.weatherapp.ui.viewmodel;

public class CurrentWeatherViewModel {
    private String cityName;
    private String temperature;
    private String windSpeed;
    private String pressure;

    public String getCityName() {
        return cityName;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getPressure() {
        return pressure;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
}
