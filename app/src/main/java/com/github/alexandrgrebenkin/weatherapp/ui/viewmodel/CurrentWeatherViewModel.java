package com.github.alexandrgrebenkin.weatherapp.ui.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

public class CurrentWeatherViewModel implements Parcelable {
    private String cityName;
    private String temperature;
    private String windSpeed;
    private String pressure;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cityName);
        dest.writeString(this.temperature);
        dest.writeString(this.windSpeed);
        dest.writeString(this.pressure);
    }

    public CurrentWeatherViewModel() {
    }

    protected CurrentWeatherViewModel(Parcel in) {
        this.cityName = in.readString();
        this.temperature = in.readString();
        this.windSpeed = in.readString();
        this.pressure = in.readString();
    }

    public static final Parcelable.Creator<CurrentWeatherViewModel> CREATOR = new Parcelable.Creator<CurrentWeatherViewModel>() {
        @Override
        public CurrentWeatherViewModel createFromParcel(Parcel source) {
            return new CurrentWeatherViewModel(source);
        }

        @Override
        public CurrentWeatherViewModel[] newArray(int size) {
            return new CurrentWeatherViewModel[size];
        }
    };
}
