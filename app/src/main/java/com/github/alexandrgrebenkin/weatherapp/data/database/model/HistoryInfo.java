package com.github.alexandrgrebenkin.weatherapp.data.database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "history_info")
public class HistoryInfo {

    @PrimaryKey
    @ColumnInfo(name = "date_utc")
    public Date dateUTC;

    @ColumnInfo(name = "city_name")
    public String cityName;

    @ColumnInfo(name = "temp_celsius")
    public float tempCelsius;
}
