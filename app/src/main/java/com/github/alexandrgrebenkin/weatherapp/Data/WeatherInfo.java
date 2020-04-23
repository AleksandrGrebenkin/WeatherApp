package com.github.alexandrgrebenkin.weatherapp.Data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class WeatherInfo implements Parcelable {
    private String cityName;
    private String temperatureValue;
    private String windValue;
    private String pressureValue;

    public WeatherInfo(String cityName, String temperatureValue, String windValue, String pressureValue) {
        this.cityName = cityName;
        this.temperatureValue = temperatureValue;
        this.windValue = windValue;
        this.pressureValue = pressureValue;
    }

    protected WeatherInfo(Parcel in) {
        List<String> data = new ArrayList<>();
        in.readStringList(data);
        cityName = data.get(0);
        temperatureValue = data.get(1);
        windValue = data.get(2);
        pressureValue = data.get(3);
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
        data.add(temperatureValue);
        data.add(windValue);
        data.add(pressureValue);

        dest.writeStringList(data);
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(String temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public String getWindValue() {
        return windValue;
    }

    public void setWindValue(String windValue) {
        this.windValue = windValue;
    }

    public String getPressureValue() {
        return pressureValue;
    }

    public void setPressureValue(String pressureValue) {
        this.pressureValue = pressureValue;
    }
}
