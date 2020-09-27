package com.github.alexandrgrebenkin.weatherapp.ui.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherViewModel implements Parcelable {
    private CurrentWeatherViewModel currentWeatherViewModel;
    private ForecastWeatherViewModel forecastWeatherViewModel;

    public WeatherViewModel(CurrentWeatherViewModel currentWeatherViewModel,
                            ForecastWeatherViewModel forecastWeatherViewModel) {
        this.currentWeatherViewModel = currentWeatherViewModel;
        this.forecastWeatherViewModel = forecastWeatherViewModel;
    }

    public CurrentWeatherViewModel getCurrentWeatherViewModel() {
        return currentWeatherViewModel;
    }

    public ForecastWeatherViewModel getForecastWeatherViewModel() {
        return forecastWeatherViewModel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.currentWeatherViewModel, flags);
        dest.writeParcelable(this.forecastWeatherViewModel, flags);
    }

    protected WeatherViewModel(Parcel in) {
        this.currentWeatherViewModel = in.readParcelable(CurrentWeatherViewModel.class.getClassLoader());
        this.forecastWeatherViewModel = in.readParcelable(ForecastWeatherViewModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<WeatherViewModel> CREATOR = new Parcelable.Creator<WeatherViewModel>() {
        @Override
        public WeatherViewModel createFromParcel(Parcel source) {
            return new WeatherViewModel(source);
        }

        @Override
        public WeatherViewModel[] newArray(int size) {
            return new WeatherViewModel[size];
        }
    };
}
