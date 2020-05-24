package com.github.alexandrgrebenkin.weatherapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class CurrentWeatherInfo implements Parcelable {
    private String cityName;
    private String tempCelsius;
    private String windSpeedMS;
    private String pressureMm;

    public CurrentWeatherInfo(String cityName, String tempCelsius, String windSpeedMS, String pressureMm) {
        this.cityName = cityName;
        this.tempCelsius = tempCelsius;
        this.windSpeedMS = windSpeedMS;
        this.pressureMm = pressureMm;
    }

    protected CurrentWeatherInfo(Parcel in) {
        List<String> data = new ArrayList<>();
        in.readStringList(data);
        cityName = data.get(0);
        tempCelsius = data.get(1);
        windSpeedMS = data.get(2);
        pressureMm = data.get(3);
    }

    public static final Creator<CurrentWeatherInfo> CREATOR = new Creator<CurrentWeatherInfo>() {
        @Override
        public CurrentWeatherInfo createFromParcel(Parcel in) {
            return new CurrentWeatherInfo(in);
        }

        @Override
        public CurrentWeatherInfo[] newArray(int size) {
            return new CurrentWeatherInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        List<String> data = new ArrayList<>();
        data.add(cityName);
        data.add(tempCelsius);
        data.add(windSpeedMS);
        data.add(pressureMm);

        dest.writeStringList(data);
    }

    public String getCityName() {
        return cityName;
    }


    public String getTempCelsius() {
        return tempCelsius;
    }


    public String getWindSpeedMS() {
        return windSpeedMS;
    }

    public String getPressureMm() {
        return pressureMm;
    }
}
