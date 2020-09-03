package com.github.alexandrgrebenkin.weatherapp.ui.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

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

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        PlaceViewModel guest = (PlaceViewModel) obj;
        return (lat == guest.lat || lat != null && lat.equals(guest.getLat()))
                && (lon == guest.lon || lon != null && lon.equals(guest.getLon()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((lat == null) ? 0 : lat.hashCode());
        result = prime * result + ((lon == null) ? 0 : lon.hashCode());
        return result;
    }
}
