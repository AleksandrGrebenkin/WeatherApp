package com.github.alexandrgrebenkin.weatherapp.data.entity;

public class Place {

    private String name;
    private String displayName;
    private String lat;
    private String lon;

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
}
