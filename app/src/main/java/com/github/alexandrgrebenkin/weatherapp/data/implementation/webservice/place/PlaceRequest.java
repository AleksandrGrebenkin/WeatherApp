package com.github.alexandrgrebenkin.weatherapp.data.implementation.webservice.place;

import com.google.gson.annotations.SerializedName;

class PlaceRequest {
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

    String getLat() {
        return lat;
    }

    String getLon() {
        return lon;
    }

    String getDisplayName() {
        return displayName;
    }

    String getPlaceClass() {
        return placeClass;
    }

    String getOsmType() {
        return osmType;
    }

    void setLat(String lat) {
        this.lat = lat;
    }

    void setLon(String lon) {
        this.lon = lon;
    }

    void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    void setPlaceClass(String placeClass) {
        this.placeClass = placeClass;
    }

    void setOsmType(String osmType) {
        this.osmType = osmType;
    }
}
