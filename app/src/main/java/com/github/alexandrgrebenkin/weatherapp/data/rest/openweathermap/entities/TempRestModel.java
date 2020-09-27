package com.github.alexandrgrebenkin.weatherapp.data.rest.openweathermap.entities;

import com.google.gson.annotations.SerializedName;

public class TempRestModel {
    @SerializedName("day")
    public float day;
    @SerializedName("min")
    public float min;
    @SerializedName("max")
    public float max;
    @SerializedName("night")
    public float night;
    @SerializedName("eve")
    public float eve;
    @SerializedName("morn")
    public float morn;
}
