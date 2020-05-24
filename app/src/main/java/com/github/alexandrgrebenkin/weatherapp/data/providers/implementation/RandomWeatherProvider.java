package com.github.alexandrgrebenkin.weatherapp.data.providers.implementation;

import com.github.alexandrgrebenkin.weatherapp.data.CurrentWeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.data.DayWeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.data.PlaceInfo;
import com.github.alexandrgrebenkin.weatherapp.data.providers.WeatherProvider;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RandomWeatherProvider implements WeatherProvider {

    public CurrentWeatherInfo getCurrentWeatherInfo(PlaceInfo placeInfo){

        return new CurrentWeatherInfo(placeInfo.getName(), getTemperature(), getWind(), getPressure());
    }

    public List<DayWeatherInfo> getForecastWeatherInfo(PlaceInfo placeInfo) {
        List<DayWeatherInfo> weekForecastList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            DayWeatherInfo dayWeatherInfo = new DayWeatherInfo();
            dayWeatherInfo.setDate(LocalDate.now().plusDays(i)
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            float maxTempValue = (float) (Math.random()*30-15);
            float minTempValue = (float) (maxTempValue - (Math.random()*5+1));
            dayWeatherInfo.setTempMinC(minTempValue);
            dayWeatherInfo.setTempMaxC(maxTempValue);
            weekForecastList.add(dayWeatherInfo);
        }
        return weekForecastList;
    }

    private String getTemperature() {
        return Integer.valueOf((int) (Math.random()*30-15)).toString();
    }

    private String getWind() {
        return Integer.valueOf((int) (Math.random()*10)).toString();
    }

    private String getPressure() {
        return Integer.valueOf((int) (Math.random()*20+750)).toString();
    }
}
