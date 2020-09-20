package com.github.alexandrgrebenkin.weatherapp.ui;

import android.content.Context;
import android.location.Address;

import com.github.alexandrgrebenkin.weatherapp.data.database.model.HistoryInfo;
import com.github.alexandrgrebenkin.weatherapp.data.entity.CurrentWeather;
import com.github.alexandrgrebenkin.weatherapp.data.entity.WeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.data.manager.WeatherDataManager;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeatherModel {
    private final Context context;

    public WeatherModel(Context context) {
        this.context = context;
    }

    public void loadWeather(Address address, LoadWeatherCallback callback) {
        WeatherDataManager weatherDataManager = new WeatherDataManager();
        weatherDataManager.loadWeatherInfo(address, callback::onLoad);
    }

    public void writeHistoryInfoIntoDB(CurrentWeather currentWeather) {
        HistoryInfo historyInfo = new HistoryInfo();
        historyInfo.cityName = currentWeather.getCityName();
        historyInfo.tempCelsius = currentWeather.getTempCelsius();

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        historyInfo.dateUTC = new Date(calendar.getTimeInMillis());
        App.getInstance().getHistoryInfoDao().insertHistoryInfo(historyInfo);
    }

    public List<HistoryInfo> loadHistoryInfoFromDB() {
        return App.getInstance().getHistoryInfoDao().getAll();
    }

    interface LoadWeatherCallback {
        void onLoad(WeatherInfo weatherInfo);
    }
}
