package com.github.alexandrgrebenkin.weatherapp.data.database.db_impl;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.github.alexandrgrebenkin.weatherapp.data.database.dao.HistoryInfoDao;
import com.github.alexandrgrebenkin.weatherapp.data.database.model.DateConverter;
import com.github.alexandrgrebenkin.weatherapp.data.database.model.HistoryInfo;

@Database(entities = {HistoryInfo.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract HistoryInfoDao getHistoryInfoDao();
}
