package com.github.alexandrgrebenkin.weatherapp.data.rest.openweathermap.entities;

import com.google.gson.annotations.SerializedName;

public class MinutelyRestModel {
    @SerializedName("dt")
    public int dt;
    @SerializedName("precipitation")
    public float precipitation;
}
