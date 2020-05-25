package com.github.alexandrgrebenkin.weatherapp.data;

import com.google.gson.annotations.SerializedName;

public class CurrentWeatherRequest {
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

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public float getWeatherCode() {
        return weatherCode;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public float getTempC() {
        return tempC;
    }

    public float getTempF() {
        return tempF;
    }

    public float getFeelsLikeC() {
        return feelsLikeC;
    }

    public float getFeelLikeF() {
        return feelLikeF;
    }

    public float getHumidityPercent() {
        return humidityPercent;
    }

    public float getWindSpeedMPH() {
        return windSpeedMPH;
    }

    public float getWindSpeedKmH() {
        return windSpeedKmH;
    }

    public float getWindSpeedKnots() {
        return windSpeedKnots;
    }

    public float getWindSpeedMS() {
        return windSpeedMS;
    }

    public float getWindDirectionDegrees() {
        return windDirectionDegrees;
    }

    public String getWindDirectionCompass() {
        return windDirectionCompass;
    }

    public float getCloudAmountPercent() {
        return cloudAmountPercent;
    }

    public float getVisibilityKm() {
        return visibilityKm;
    }

    public float getVisibilityMi() {
        return visibilityMi;
    }

    public float getPressureMillibars() {
        return pressureMillibars;
    }

    public float getPressureInches() {
        return pressureInches;
    }

    // Setter Methods

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public void setWeatherCode(float weatherCode) {
        this.weatherCode = weatherCode;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public void setTempC(float tempC) {
        this.tempC = tempC;
    }

    public void setTempF(float tempF) {
        this.tempF = tempF;
    }

    public void setFeelsLikeC(float feelsLikeC) {
        this.feelsLikeC = feelsLikeC;
    }

    public void setFeelLikeF(float feelLikeF) {
        this.feelLikeF = feelLikeF;
    }

    public void setHumidityPercent(float humidityPercent) {
        this.humidityPercent = humidityPercent;
    }

    public void setWindSpeedMPH(float windSpeedMPH) {
        this.windSpeedMPH = windSpeedMPH;
    }

    public void setWindSpeedKmH(float windSpeedKmH) {
        this.windSpeedKmH = windSpeedKmH;
    }

    public void setWindSpeedKnots(float windSpeedKnots) {
        this.windSpeedKnots = windSpeedKnots;
    }

    public void setWindSpeedMS(float windSpeedMS) {
        this.windSpeedMS = windSpeedMS;
    }

    public void setWindDirectionDegrees(float windDirectionDegrees) {
        this.windDirectionDegrees = windDirectionDegrees;
    }

    public void setWindDirectionCompass(String windDirectionCompass) {
        this.windDirectionCompass = windDirectionCompass;
    }

    public void setCloudAmountPercent(float cloudAmountPercent) {
        this.cloudAmountPercent = cloudAmountPercent;
    }

    public void setVisibilityKm(float visibilityKm) {
        this.visibilityKm = visibilityKm;
    }

    public void setVisibilityMi(float visibilityMi) {
        this.visibilityMi = visibilityMi;
    }

    public void setPressureMillibars(float pressureMillibars) {
        this.pressureMillibars = pressureMillibars;
    }

    public void setPressureInches(float pressureInches) {
        this.pressureInches = pressureInches;
    }
}
