package com.github.alexandrgrebenkin.weatherapp.data;

import java.util.Calendar;

public class DayInfo {
    /* TODO
    *  Класс, содержащий погоду на день
    * Включая температуру днем и ночью (с возможностью добавления утра и вечера)
    * Информацию о погоде днем и ночью хранить в объекте класса WeatherInfo
    * */
    private Calendar date;
    private WeatherInfo day;
    private WeatherInfo night;
}
