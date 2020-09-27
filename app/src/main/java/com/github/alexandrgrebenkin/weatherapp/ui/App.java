package com.github.alexandrgrebenkin.weatherapp.ui;

import android.app.Application;

import androidx.room.Room;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.github.alexandrgrebenkin.weatherapp.data.database.dao.HistoryInfoDao;
import com.github.alexandrgrebenkin.weatherapp.data.database.db_impl.AppDatabase;

public class App extends Application {

    private static App instance;

    private AppDatabase db;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"app_database")
                .build();


        Fresco.initialize(this);
    }

    public HistoryInfoDao getHistoryInfoDao() {
        return db.getHistoryInfoDao();
    }
}
