package com.github.alexandrgrebenkin.weatherapp.ui.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ForecastWeatherViewModel implements Parcelable {
    private List<DayWeatherViewModel> dayWeatherViewModelList;

    public List<DayWeatherViewModel> getDayWeatherViewModelList() {
        return dayWeatherViewModelList;
    }

    public void setDayWeatherViewModelList(List<DayWeatherViewModel> dayWeatherViewModelList) {
        this.dayWeatherViewModelList = dayWeatherViewModelList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.dayWeatherViewModelList);
    }

    public ForecastWeatherViewModel() {
    }

    protected ForecastWeatherViewModel(Parcel in) {
        this.dayWeatherViewModelList = new ArrayList<>();
        in.readList(this.dayWeatherViewModelList, DayWeatherViewModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<ForecastWeatherViewModel> CREATOR = new Parcelable.Creator<ForecastWeatherViewModel>() {
        @Override
        public ForecastWeatherViewModel createFromParcel(Parcel source) {
            return new ForecastWeatherViewModel(source);
        }

        @Override
        public ForecastWeatherViewModel[] newArray(int size) {
            return new ForecastWeatherViewModel[size];
        }
    };
}
