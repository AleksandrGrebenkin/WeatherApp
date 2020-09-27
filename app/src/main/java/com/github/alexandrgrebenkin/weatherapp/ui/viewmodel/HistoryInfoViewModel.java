package com.github.alexandrgrebenkin.weatherapp.ui.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

public class HistoryInfoViewModel implements Parcelable {
    private String cityName;
    private String date;
    private String temperature;

    public String getCityName() {
        return cityName;
    }

    public String getDate() {
        return date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cityName);
        dest.writeString(this.date);
        dest.writeString(this.temperature);
    }

    public HistoryInfoViewModel() {
    }

    protected HistoryInfoViewModel(Parcel in) {
        this.cityName = in.readString();
        this.date = in.readString();
        this.temperature = in.readString();
    }

    public static final Parcelable.Creator<HistoryInfoViewModel> CREATOR = new Parcelable.Creator<HistoryInfoViewModel>() {
        @Override
        public HistoryInfoViewModel createFromParcel(Parcel source) {
            return new HistoryInfoViewModel(source);
        }

        @Override
        public HistoryInfoViewModel[] newArray(int size) {
            return new HistoryInfoViewModel[size];
        }
    };
}
