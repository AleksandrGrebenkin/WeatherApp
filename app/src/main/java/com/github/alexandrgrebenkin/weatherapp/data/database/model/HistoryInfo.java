package com.github.alexandrgrebenkin.weatherapp.data.database.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.util.Date;

@Entity(tableName = "history_info", primaryKeys = {"date_utc", "city_name"})
public class HistoryInfo {

    @ColumnInfo(name = "date_utc")
    @NonNull
    public Date dateUTC;

    @ColumnInfo(name = "city_name")
    @NonNull
    public String cityName;

    @ColumnInfo(name = "temp_celsius")
    public float tempCelsius;
}
