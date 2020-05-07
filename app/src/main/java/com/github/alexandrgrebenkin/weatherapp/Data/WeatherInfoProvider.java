package com.github.alexandrgrebenkin.weatherapp.Data;

public class WeatherInfoProvider {
    public WeatherInfo getWeatherInfo(String cityName){
        String temperatureValue = Integer.valueOf((int) (Math.random()*60-30)).toString();
        String windValue = Integer.valueOf((int) (Math.random()*30)).toString();
        String pressureValue = Integer.valueOf((int) (Math.random()*100+700)).toString();
        String temperatureMeasure = "℃";
        String windMeasure = "m/s";
        String pressureMeasure = "mmHg";
        String temperature = temperatureValue + " " + temperatureMeasure;
        String wind = windValue + " " + windMeasure;
        String pressure = pressureValue + " " + pressureMeasure;
        return new WeatherInfo(cityName, temperature, wind, pressure);
    }
}
