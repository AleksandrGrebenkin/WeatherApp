package com.github.alexandrgrebenkin.weatherapp.ui.viewmodel;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class CurrentWeatherViewModel implements Parcelable {
    private String cityName;
    private String temperature;
    private String windSpeed;
    private String pressure;
    private Uri imageUri;

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

    public Uri getImageUri() {
        return imageUri;
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

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
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
        dest.writeParcelable(this.imageUri, flags);
    }

    public CurrentWeatherViewModel() {
    }

    protected CurrentWeatherViewModel(Parcel in) {
        this.cityName = in.readString();
        this.temperature = in.readString();
        this.windSpeed = in.readString();
        this.pressure = in.readString();
        this.imageUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<CurrentWeatherViewModel> CREATOR = new Creator<CurrentWeatherViewModel>() {
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
