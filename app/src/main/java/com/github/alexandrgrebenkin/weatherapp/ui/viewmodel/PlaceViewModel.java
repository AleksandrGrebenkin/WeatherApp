package com.github.alexandrgrebenkin.weatherapp.ui.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

public class PlaceViewModel implements Parcelable {

    private String name;
    private String displayName;
    private String lat;
    private String lon;

    public PlaceViewModel() {
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.displayName);
        dest.writeString(this.lat);
        dest.writeString(this.lon);
    }

    protected PlaceViewModel(Parcel in) {
        this.name = in.readString();
        this.displayName = in.readString();
        this.lat = in.readString();
        this.lon = in.readString();
    }

    public static final Creator<PlaceViewModel> CREATOR = new Creator<PlaceViewModel>() {
        @Override
        public PlaceViewModel createFromParcel(Parcel source) {
            return new PlaceViewModel(source);
        }

        @Override
        public PlaceViewModel[] newArray(int size) {
            return new PlaceViewModel[size];
        }
    };
}
