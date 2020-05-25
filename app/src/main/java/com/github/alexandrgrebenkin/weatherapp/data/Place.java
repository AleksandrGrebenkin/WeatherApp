package com.github.alexandrgrebenkin.weatherapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Place implements Parcelable {
    @SerializedName("lat")
    private String lat;

    @SerializedName("lon")
    private String lon;

    @SerializedName("display_name")
    private String displayName;

    @SerializedName("class")
    private String placeClass;

    @SerializedName("osm_type")
    private String osmType;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPlaceClass() {
        return placeClass;
    }

    public void setPlaceClass(String placeClass) {
        this.placeClass = placeClass;
    }

    public String getOsmType() {
        return osmType;
    }

    public void setOsmType(String osmType) {
        this.osmType = osmType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.lat);
        dest.writeString(this.lon);
        dest.writeString(this.displayName);
        dest.writeString(this.placeClass);
        dest.writeString(this.osmType);
    }

    public Place() {
    }

    protected Place(Parcel in) {
        this.lat = in.readString();
        this.lon = in.readString();
        this.displayName = in.readString();
        this.placeClass = in.readString();
        this.osmType = in.readString();
    }

    public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel source) {
            return new Place(source);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
}
