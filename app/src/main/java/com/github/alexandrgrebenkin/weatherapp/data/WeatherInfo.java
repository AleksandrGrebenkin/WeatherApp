package com.github.alexandrgrebenkin.weatherapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class WeatherInfo implements Parcelable {
    private String cityName;
    private String temperature;
    private String wind;
    private String pressure;
    private String dayOfWeek;

    public WeatherInfo(String cityName, String temperature, String wind, String pressure, String dayOfWeek) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.wind = wind;
        this.pressure = pressure;
        this.dayOfWeek = dayOfWeek;
    }

    protected WeatherInfo(Parcel in) {
        List<String> data = new ArrayList<>();
        in.readStringList(data);
        cityName = data.get(0);
        temperature = data.get(1);
        wind = data.get(2);
        pressure = data.get(3);
        dayOfWeek = data.get(4);
    }

    public static final Creator<WeatherInfo> CREATOR = new Creator<WeatherInfo>() {
        @Override
        public WeatherInfo createFromParcel(Parcel in) {
            return new WeatherInfo(in);
        }

        @Override
        public WeatherInfo[] newArray(int size) {
            return new WeatherInfo[size];
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
        data.add(dayOfWeek);

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

    public String getDayOfWeek() {
        return dayOfWeek;
    }
}
