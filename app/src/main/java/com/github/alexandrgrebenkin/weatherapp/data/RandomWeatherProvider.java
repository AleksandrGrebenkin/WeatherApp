package com.github.alexandrgrebenkin.weatherapp.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RandomWeatherProvider implements WeatherProvider {

    public CurrentWeatherInfo getCurrentWeatherInfo(String cityName){

        return new CurrentWeatherInfo(cityName, getTemperature(), getWind(), getPressure());
    }

    public List<DayWeatherInfo> getForecastWeatherInfo(String cityName) {
        List<DayWeatherInfo> weekForecastList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            DayWeatherInfo dayWeatherInfo = new DayWeatherInfo();
            dayWeatherInfo.setDate(LocalDate.now().plusDays(i));
            Integer maxTempValue = (int) (Math.random()*30-15);
            Integer minTempValue = maxTempValue - (int) (Math.random()*5+1);
            dayWeatherInfo.setTempMaxC(maxTempValue.toString());
            dayWeatherInfo.setTempMinC(minTempValue.toString());
            weekForecastList.add(dayWeatherInfo);
        }
        return weekForecastList;
    }

    private String getTemperature() {
        String temperatureValue = Integer.valueOf((int) (Math.random()*30-15)).toString();
        String temperatureMeasure = "℃";
        return temperatureValue + " " + temperatureMeasure;
    }

    private String getWind() {
        String windValue = Integer.valueOf((int) (Math.random()*10)).toString();
        String windMeasure = "m/s";
        return windValue + " " + windMeasure;
    }

    private String getPressure() {
        String pressureValue = Integer.valueOf((int) (Math.random()*20+750)).toString();
        String pressureMeasure = "mmHg";
        return pressureValue + " " + pressureMeasure;
    }
}
