package com.github.alexandrgrebenkin.weatherapp.ui.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

public class DayWeatherViewModel implements Parcelable {
    private String date;
    private String dayOfWeek;
    private String maxTemperature;
    private String minTemperature;

    public String getDate() {
        return date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = minTemperature;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeString(this.dayOfWeek);
        dest.writeString(this.maxTemperature);
        dest.writeString(this.minTemperature);
    }

    public DayWeatherViewModel() {
    }

    protected DayWeatherViewModel(Parcel in) {
        this.date = in.readString();
        this.dayOfWeek = in.readString();
        this.maxTemperature = in.readString();
        this.minTemperature = in.readString();
    }

    public static final Parcelable.Creator<DayWeatherViewModel> CREATOR = new Parcelable.Creator<DayWeatherViewModel>() {
        @Override
        public DayWeatherViewModel createFromParcel(Parcel source) {
            return new DayWeatherViewModel(source);
        }

        @Override
        public DayWeatherViewModel[] newArray(int size) {
            return new DayWeatherViewModel[size];
        }
    };
}
