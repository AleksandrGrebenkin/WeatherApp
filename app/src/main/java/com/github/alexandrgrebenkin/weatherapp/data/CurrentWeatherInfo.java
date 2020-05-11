package com.github.alexandrgrebenkin.weatherapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class CurrentWeatherInfo implements Parcelable {
    private String cityName;
    private String temperature;
    private String wind;
    private String pressure;

    public CurrentWeatherInfo(String cityName, String temperature, String wind, String pressure) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.wind = wind;
        this.pressure = pressure;
    }

    protected CurrentWeatherInfo(Parcel in) {
        List<String> data = new ArrayList<>();
        in.readStringList(data);
        cityName = data.get(0);
        temperature = data.get(1);
        wind = data.get(2);
        pressure = data.get(3);
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
        data.add(temperature);
        data.add(wind);
        data.add(pressure);

        dest.writeStringList(data);
    }

    public String getCityName() {
        return cityName;
    }


    public String getTemperature() {
        return temperature;
    }


    public String getWind() {
        return wind;
    }

    public String getPressure() {
        return pressure;
    }
}
