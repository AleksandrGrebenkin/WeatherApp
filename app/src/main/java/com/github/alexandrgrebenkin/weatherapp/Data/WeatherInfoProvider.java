package com.github.alexandrgrebenkin.weatherapp.Data;

public class WeatherInfoProvider {
    public WeatherInfo getWeatherInfo(String cityName){
        String temperature = Integer.valueOf((int) (Math.random()*60-30)).toString();
        String wind = Integer.valueOf((int) (Math.random()*30)).toString();
        String pressure = Integer.valueOf((int) (Math.random()*100+700)).toString();
        return new WeatherInfo(cityName, temperature, wind, pressure);
    }
}
