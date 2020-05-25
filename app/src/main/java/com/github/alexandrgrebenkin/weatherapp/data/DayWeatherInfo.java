package com.github.alexandrgrebenkin.weatherapp.data;

import com.google.gson.annotations.SerializedName;

public class DayWeatherInfo {

    @SerializedName("date")
    private String date;
    @SerializedName("sunrise_time")
    private String sunriseTime;
    @SerializedName("sunset_time")
    private String sunsetTime;
    @SerializedName("moonrise_time")
    private String moonriseTime;
    @SerializedName("moonset_time")
    private String moonsetTime;
    @SerializedName("temp_max_c")
    private float tempMaxC;
    @SerializedName("temp_max_f")
    private float tempMaxF;
    @SerializedName("temp_min_c")
    private float tempMinC;
    @SerializedName("temp_min_f")
    private float tempMinF;
    @SerializedName("precip_total_mm")
    private float precipitationTotalMillimeters;
    @SerializedName("precip_total_in")
    private float precipitationTotalInches;
    @SerializedName("rain_total_mm")
    private float rainTotalMillimeters;
    @SerializedName("rain_total_in")
    private float rainTotalInches;
    @SerializedName("snow_total_mm")
    private float snowTotalMillimeters;
    @SerializedName("snow_total_in")
    private float snowTotalInches;
    @SerializedName("prob_precip_pct")
    private float probablyPrecipitationPercent;
    @SerializedName("humid_max_pct")
    private float humidityMaxPercent;
    @SerializedName("humid_min_pct")
    private float humidityMinPercent;
    @SerializedName("windspd_max_mph")
    private float windSpeedMaxMPH;
    @SerializedName("windspd_max_kmh")
    private float windSpeedMaxKmH;
    @SerializedName("windspd_max_kts")
    private float windSpeedMaxKnots;
    @SerializedName("windspd_max_ms")
    private float windSpeedMaxMS;
    @SerializedName("slp_max_in")
    private float pressureMaxInches;
    @SerializedName("slp_max_mb")
    private float pressureMaxMillibars;
    @SerializedName("slp_min_in")
    private float pressureMinInches;
    @SerializedName("slp_min_mb")
    private float pressureMinMillibars;


    public String getDate() {
        return date;
    }

    public String getSunriseTime() {
        return sunriseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    public String getMoonriseTime() {
        return moonriseTime;
    }

    public String getMoonsetTime() {
        return moonsetTime;
    }

    public float getTempMaxC() {
        return tempMaxC;
    }

    public float getTempMaxF() {
        return tempMaxF;
    }

    public float getTempMinC() {
        return tempMinC;
    }

    public float getTempMinF() {
        return tempMinF;
    }

    public float getPrecipitationTotalMillimeters() {
        return precipitationTotalMillimeters;
    }

    public float getPrecipitationTotalInches() {
        return precipitationTotalInches;
    }

    public float getRainTotalMillimeters() {
        return rainTotalMillimeters;
    }

    public float getRainTotalInches() {
        return rainTotalInches;
    }

    public float getSnowTotalMillimeters() {
        return snowTotalMillimeters;
    }

    public float getSnowTotalInches() {
        return snowTotalInches;
    }

    public float getProbablyPrecipitationPercent() {
        return probablyPrecipitationPercent;
    }

    public float getHumidityMaxPercent() {
        return humidityMaxPercent;
    }

    public float getHumidityMinPercent() {
        return humidityMinPercent;
    }

    public float getWindSpeedMaxMPH() {
        return windSpeedMaxMPH;
    }

    public float getWindSpeedMaxKmH() {
        return windSpeedMaxKmH;
    }

    public float getWindSpeedMaxKnots() {
        return windSpeedMaxKnots;
    }

    public float getWindSpeedMaxMS() {
        return windSpeedMaxMS;
    }

    public float getPressureMaxInches() {
        return pressureMaxInches;
    }

    public float getPressureMaxMillibars() {
        return pressureMaxMillibars;
    }

    public float getPressureMinInches() {
        return pressureMinInches;
    }

    public float getPressureMinMillibars() {
        return pressureMinMillibars;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public void setSunriseTime(String sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public void setSunsetTime(String sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public void setMoonriseTime(String moonriseTime) {
        this.moonriseTime = moonriseTime;
    }

    public void setMoonsetTime(String moonsetTime) {
        this.moonsetTime = moonsetTime;
    }

    public void setTempMaxC(float tempMaxC) {
        this.tempMaxC = tempMaxC;
    }

    public void setTempMaxF(float tempMaxF) {
        this.tempMaxF = tempMaxF;
    }

    public void setTempMinC(float tempMinC) {
        this.tempMinC = tempMinC;
    }

    public void setTempMinF(float tempMinF) {
        this.tempMinF = tempMinF;
    }

    public void setPrecipitationTotalMillimeters(float precipitationTotalMillimeters) {
        this.precipitationTotalMillimeters = precipitationTotalMillimeters;
    }

    public void setPrecipitationTotalInches(float precipitationTotalInches) {
        this.precipitationTotalInches = precipitationTotalInches;
    }

    public void setRainTotalMillimeters(float rainTotalMillimeters) {
        this.rainTotalMillimeters = rainTotalMillimeters;
    }

    public void setRainTotalInches(float rainTotalInches) {
        this.rainTotalInches = rainTotalInches;
    }

    public void setSnowTotalMillimeters(float snowTotalMillimeters) {
        this.snowTotalMillimeters = snowTotalMillimeters;
    }

    public void setSnowTotalInches(float snowTotalInches) {
        this.snowTotalInches = snowTotalInches;
    }

    public void setProbablyPrecipitationPercent(float probablyPrecipitationPercent) {
        this.probablyPrecipitationPercent = probablyPrecipitationPercent;
    }

    public void setHumidityMaxPercent(float humidityMaxPercent) {
        this.humidityMaxPercent = humidityMaxPercent;
    }

    public void setHumidityMinPercent(float humidityMinPercent) {
        this.humidityMinPercent = humidityMinPercent;
    }

    public void setWindSpeedMaxMPH(float windSpeedMaxMPH) {
        this.windSpeedMaxMPH = windSpeedMaxMPH;
    }

    public void setWindSpeedMaxKmH(float windSpeedMaxKmH) {
        this.windSpeedMaxKmH = windSpeedMaxKmH;
    }

    public void setWindSpeedMaxKnots(float windSpeedMaxKnots) {
        this.windSpeedMaxKnots = windSpeedMaxKnots;
    }

    public void setWindSpeedMaxMS(float windSpeedMaxMS) {
        this.windSpeedMaxMS = windSpeedMaxMS;
    }

    public void setPressureMaxInches(float pressureMaxInches) {
        this.pressureMaxInches = pressureMaxInches;
    }

    public void setPressureMaxMillibars(float pressureMaxMillibars) {
        this.pressureMaxMillibars = pressureMaxMillibars;
    }

    public void setPressureMinInches(float pressureMinInches) {
        this.pressureMinInches = pressureMinInches;
    }

    public void setPressureMinMillibars(float pressureMinMillibars) {
        this.pressureMinMillibars = pressureMinMillibars;
    }
}
