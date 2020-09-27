package com.github.alexandrgrebenkin.weatherapp.data.rest.openweathermap.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OpenWeatherRequestRestModel {
    @SerializedName("lat")
    public float lat;
    @SerializedName("lon")
    public float lon;
    @SerializedName("timezone")
    public String timezone;
    @SerializedName("timezone_offset")
    public int timezoneOffset;
    @SerializedName("current")
    public CurrentRestModel current;
    @SerializedName("minutely")
    public List<MinutelyRestModel> minutely = new ArrayList<>();
    @SerializedName("hourly")
    public List<HourlyRestModel> hourly = new ArrayList<>();
    @SerializedName("daily")
    public List<DailyRestModel> daily = new ArrayList<>();
}
