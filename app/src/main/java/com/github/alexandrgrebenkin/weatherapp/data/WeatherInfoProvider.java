package com.github.alexandrgrebenkin.weatherapp.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WeatherInfoProvider {
    public static WeatherInfo getWeatherInfo(String cityName){

        return new WeatherInfo(cityName, getTemperature(), getWind(), getPressure(), getDayOfWeek(0));
    }

    public static List<WeatherInfo> getWeekWeatherInfo(String cityName) {
        List<WeatherInfo> weatherInfoList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            weatherInfoList.add(new WeatherInfo(cityName, getTemperature(), getWind(), getPressure(), getDayOfWeek(i)));
        }
        return weatherInfoList;
    }

    private static String getTemperature() {
        String temperatureValue = Integer.valueOf((int) (Math.random()*30-15)).toString();
        String temperatureMeasure = "℃";
        return temperatureValue + " " + temperatureMeasure;
    }

    private static String getWind() {
        String windValue = Integer.valueOf((int) (Math.random()*10)).toString();
        String windMeasure = "m/s";
        return windValue + " " + windMeasure;
    }

    private static String getPressure() {
        String pressureValue = Integer.valueOf((int) (Math.random()*20+750)).toString();
        String pressureMeasure = "mmHg";
        return pressureValue + " " + pressureMeasure;
    }

    private static String getDayOfWeek(int offset) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, offset);
        return android.text.format.DateFormat.format("EEEE", c).toString();
    }
}
