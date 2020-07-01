package com.github.alexandrgrebenkin.weatherapp.ui.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class CurrentWeatherViewModel implements Parcelable {
    private String cityName;
    private String temperature;
    private String windSpeed;
    private String pressure;

    public CurrentWeatherViewModel(){
    }

    protected CurrentWeatherViewModel(Parcel in) {
        List<String> data = new ArrayList<>();
        in.readStringList(data);
        cityName = data.get(0);
        temperature = data.get(1);
        windSpeed = data.get(2);
        pressure = data.get(3);
    }

    public static final Parcelable.Creator<CurrentWeatherViewModel> CREATOR
            = new Parcelable.Creator<CurrentWeatherViewModel>() {
        @Override
        public CurrentWeatherViewModel createFromParcel(Parcel in) {
            return new CurrentWeatherViewModel(in);
        }

        @Override
        public CurrentWeatherViewModel[] newArray(int size) {
            return new CurrentWeatherViewModel[size];
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
        data.add(windSpeed);
        data.add(pressure);

        dest.writeStringList(data);
    }

    public String getCityName() {
        return cityName;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getPressure() {
        return pressure;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
}
