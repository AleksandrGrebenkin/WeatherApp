package com.github.alexandrgrebenkin.weatherapp.data.rest.openweathermap.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HourlyRestModel {
    @SerializedName("dt")
    public int dt;
    @SerializedName("temp")
    public float temp;
    @SerializedName("feels_like")
    public float feelsLike;
    @SerializedName("pressure")
    public int pressure;
    @SerializedName("humidity")
    public int humidity;
    @SerializedName("dew_point")
    public float dewPoint;
    @SerializedName("clouds")
    public int clouds;
    @SerializedName("visibility")
    public int visibility;
    @SerializedName("wind_speed")
    public float windSpeed;
    @SerializedName("wind_deg")
    public int windDeg;
    @SerializedName("weather")
    public List<WeatherRestModel> weather = new ArrayList<>();
    @SerializedName("pop")
    public float pop;
    @SerializedName("rain")
    public RainRestModel rain;
}
