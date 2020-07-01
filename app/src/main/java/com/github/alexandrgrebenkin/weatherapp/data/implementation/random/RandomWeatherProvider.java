package com.github.alexandrgrebenkin.weatherapp.data.implementation.random;

import com.github.alexandrgrebenkin.weatherapp.data.entity.Place;
import com.github.alexandrgrebenkin.weatherapp.data.entity.CurrentWeather;
import com.github.alexandrgrebenkin.weatherapp.data.provider.WeatherProvider;
import com.github.alexandrgrebenkin.weatherapp.data.entity.DayWeather;
import com.github.alexandrgrebenkin.weatherapp.data.entity.ForecastWeather;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RandomWeatherProvider implements WeatherProvider {

    public CurrentWeather getCurrentWeather(Place place){
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setCityName(place.getName());
        currentWeather.setTempCelsius(getTemperature());
        currentWeather.setWindSpeedMS(getWind());
        currentWeather.setPressureMm(getPressure());
        return currentWeather;
    }

    @Override
    public ForecastWeather getForecastWeather(Place place) {
        ForecastWeather forecastWeather = new ForecastWeather();
        forecastWeather.setDayWeatherList(getDayWeatherList(place));
        return forecastWeather;
    }

    public List<DayWeather> getDayWeatherList(Place place) {
        List<DayWeather> weekForecastList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            DayWeather dayWeather = new DayWeather();
            dayWeather.setDate(LocalDate.now().plusDays(i));
            float maxTempValue = (float) (Math.random()*30-15);
            float minTempValue = (float) (maxTempValue - (Math.random()*5+1));
            dayWeather.setMinTempCelsius(minTempValue);
            dayWeather.setMaxTempCelsius(maxTempValue);
            weekForecastList.add(dayWeather);
        }
        return weekForecastList;
    }

    private float getTemperature() {
        return (float) (Math.random()*30-15);
    }

    private float getWind() {
        return (float) (Math.random()*10);
    }

    private float getPressure() {
        return (float) (Math.random()*20+750);
    }
}
