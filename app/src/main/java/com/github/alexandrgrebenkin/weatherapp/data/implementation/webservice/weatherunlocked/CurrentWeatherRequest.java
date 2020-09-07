package com.github.alexandrgrebenkin.weatherapp.data.implementation.webservice.weatherunlocked;

import com.google.gson.annotations.SerializedName;

class CurrentWeatherRequest {
    @SerializedName("lat")
    private float latitude;
    @SerializedName("lon")
    private float longitude;
    @SerializedName("wx_desc")
    private String weatherDescription;
    @SerializedName("wx_code")
    private float weatherCode;
    @SerializedName("wx_icon")
    private String weatherIcon;
    @SerializedName("temp_c")
    private float tempC;
    @SerializedName("temp_f")
    private float tempF;
    @SerializedName("feelslike_c")
    private float feelsLikeC;
    @SerializedName("feelslike_f")
    private float feelLikeF;
    @SerializedName("humid_pct")
    private float humidityPercent;
    @SerializedName("windspd_mph")
    private float windSpeedMPH;
    @SerializedName("windspd_kmh")
    private float windSpeedKmH;
    @SerializedName("windspd_kts")
    private float windSpeedKnots;
    @SerializedName("windspd_ms")
    private float windSpeedMS;
    @SerializedName("winddir_deg")
    private float windDirectionDegrees;
    @SerializedName("winddir_compass")
    private String windDirectionCompass;
    @SerializedName("cloudtotal_pct")
    private float cloudAmountPercent;
    @SerializedName("vis_km")
    private float visibilityKm;
    @SerializedName("vis_mi")
    private float visibilityMi;
    @SerializedName("slp_mb")
    private float pressureMillibars;
    @SerializedName("slp_in")
    private float pressureInches;


    // Getter Methods

    float getLatitude() {
        return latitude;
    }

    float getLongitude() {
        return longitude;
    }

    String getWeatherDescription() {
        return weatherDescription;
    }

    float getWeatherCode() {
        return weatherCode;
    }

    String getWeatherIcon() {
        return weatherIcon;
    }

    float getTempC() {
        return tempC;
    }

    float getTempF() {
        return tempF;
    }

    float getFeelsLikeC() {
        return feelsLikeC;
    }

    float getFeelLikeF() {
        return feelLikeF;
    }

    float getHumidityPercent() {
        return humidityPercent;
    }

    float getWindSpeedMPH() {
        return windSpeedMPH;
    }

    float getWindSpeedKmH() {
        return windSpeedKmH;
    }

    float getWindSpeedKnots() {
        return windSpeedKnots;
    }

    float getWindSpeedMS() {
        return windSpeedMS;
    }

    float getWindDirectionDegrees() {
        return windDirectionDegrees;
    }

    String getWindDirectionCompass() {
        return windDirectionCompass;
    }

    float getCloudAmountPercent() {
        return cloudAmountPercent;
    }

    float getVisibilityKm() {
        return visibilityKm;
    }

    float getVisibilityMi() {
        return visibilityMi;
    }

    float getPressureMillibars() {
        return pressureMillibars;
    }

    float getPressureInches() {
        return pressureInches;
    }

    // Setter Methods

    void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    void setWeatherCode(float weatherCode) {
        this.weatherCode = weatherCode;
    }

    void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    void setTempC(float tempC) {
        this.tempC = tempC;
    }

    void setTempF(float tempF) {
        this.tempF = tempF;
    }

    void setFeelsLikeC(float feelsLikeC) {
        this.feelsLikeC = feelsLikeC;
    }

    void setFeelLikeF(float feelLikeF) {
        this.feelLikeF = feelLikeF;
    }

    void setHumidityPercent(float humidityPercent) {
        this.humidityPercent = humidityPercent;
    }

    void setWindSpeedMPH(float windSpeedMPH) {
        this.windSpeedMPH = windSpeedMPH;
    }

    void setWindSpeedKmH(float windSpeedKmH) {
        this.windSpeedKmH = windSpeedKmH;
    }

    void setWindSpeedKnots(float windSpeedKnots) {
        this.windSpeedKnots = windSpeedKnots;
    }

    void setWindSpeedMS(float windSpeedMS) {
        this.windSpeedMS = windSpeedMS;
    }

    void setWindDirectionDegrees(float windDirectionDegrees) {
        this.windDirectionDegrees = windDirectionDegrees;
    }

    void setWindDirectionCompass(String windDirectionCompass) {
        this.windDirectionCompass = windDirectionCompass;
    }

    void setCloudAmountPercent(float cloudAmountPercent) {
        this.cloudAmountPercent = cloudAmountPercent;
    }

    void setVisibilityKm(float visibilityKm) {
        this.visibilityKm = visibilityKm;
    }

    void setVisibilityMi(float visibilityMi) {
        this.visibilityMi = visibilityMi;
    }

    void setPressureMillibars(float pressureMillibars) {
        this.pressureMillibars = pressureMillibars;
    }

    void setPressureInches(float pressureInches) {
        this.pressureInches = pressureInches;
    }
}
