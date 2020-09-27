package com.github.alexandrgrebenkin.weatherapp.data.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.github.alexandrgrebenkin.weatherapp.data.database.model.HistoryInfo;

import java.util.List;

@Dao
public interface HistoryInfoDao {
    @Query("SELECT * FROM history_info ORDER BY date_utc DESC")
    List<HistoryInfo> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHistoryInfo(HistoryInfo historyInfo);
}
