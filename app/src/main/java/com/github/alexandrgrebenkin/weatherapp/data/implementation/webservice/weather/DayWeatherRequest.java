package com.github.alexandrgrebenkin.weatherapp.data.implementation.webservice.weather;

import com.google.gson.annotations.SerializedName;

class DayWeatherRequest {

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


    String getDate() {
        return date;
    }

    String getSunriseTime() {
        return sunriseTime;
    }

    String getSunsetTime() {
        return sunsetTime;
    }

    String getMoonriseTime() {
        return moonriseTime;
    }

    String getMoonsetTime() {
        return moonsetTime;
    }

    float getTempMaxC() {
        return tempMaxC;
    }

    float getTempMaxF() {
        return tempMaxF;
    }

    float getTempMinC() {
        return tempMinC;
    }

    float getTempMinF() {
        return tempMinF;
    }

    float getPrecipitationTotalMillimeters() {
        return precipitationTotalMillimeters;
    }

    float getPrecipitationTotalInches() {
        return precipitationTotalInches;
    }

    float getRainTotalMillimeters() {
        return rainTotalMillimeters;
    }

    float getRainTotalInches() {
        return rainTotalInches;
    }

    float getSnowTotalMillimeters() {
        return snowTotalMillimeters;
    }

    float getSnowTotalInches() {
        return snowTotalInches;
    }

    float getProbablyPrecipitationPercent() {
        return probablyPrecipitationPercent;
    }

    float getHumidityMaxPercent() {
        return humidityMaxPercent;
    }

    float getHumidityMinPercent() {
        return humidityMinPercent;
    }

    float getWindSpeedMaxMPH() {
        return windSpeedMaxMPH;
    }

    float getWindSpeedMaxKmH() {
        return windSpeedMaxKmH;
    }

    float getWindSpeedMaxKnots() {
        return windSpeedMaxKnots;
    }

    float getWindSpeedMaxMS() {
        return windSpeedMaxMS;
    }

    float getPressureMaxInches() {
        return pressureMaxInches;
    }

    float getPressureMaxMillibars() {
        return pressureMaxMillibars;
    }

    float getPressureMinInches() {
        return pressureMinInches;
    }

    float getPressureMinMillibars() {
        return pressureMinMillibars;
    }


    void setDate(String date) {
        this.date = date;
    }

    void setSunriseTime(String sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    void setSunsetTime(String sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    void setMoonriseTime(String moonriseTime) {
        this.moonriseTime = moonriseTime;
    }

    void setMoonsetTime(String moonsetTime) {
        this.moonsetTime = moonsetTime;
    }

    void setTempMaxC(float tempMaxC) {
        this.tempMaxC = tempMaxC;
    }

    void setTempMaxF(float tempMaxF) {
        this.tempMaxF = tempMaxF;
    }

    void setTempMinC(float tempMinC) {
        this.tempMinC = tempMinC;
    }

    void setTempMinF(float tempMinF) {
        this.tempMinF = tempMinF;
    }

    void setPrecipitationTotalMillimeters(float precipitationTotalMillimeters) {
        this.precipitationTotalMillimeters = precipitationTotalMillimeters;
    }

    void setPrecipitationTotalInches(float precipitationTotalInches) {
        this.precipitationTotalInches = precipitationTotalInches;
    }

    void setRainTotalMillimeters(float rainTotalMillimeters) {
        this.rainTotalMillimeters = rainTotalMillimeters;
    }

    void setRainTotalInches(float rainTotalInches) {
        this.rainTotalInches = rainTotalInches;
    }

    void setSnowTotalMillimeters(float snowTotalMillimeters) {
        this.snowTotalMillimeters = snowTotalMillimeters;
    }

    void setSnowTotalInches(float snowTotalInches) {
        this.snowTotalInches = snowTotalInches;
    }

    void setProbablyPrecipitationPercent(float probablyPrecipitationPercent) {
        this.probablyPrecipitationPercent = probablyPrecipitationPercent;
    }

    void setHumidityMaxPercent(float humidityMaxPercent) {
        this.humidityMaxPercent = humidityMaxPercent;
    }

    void setHumidityMinPercent(float humidityMinPercent) {
        this.humidityMinPercent = humidityMinPercent;
    }

    void setWindSpeedMaxMPH(float windSpeedMaxMPH) {
        this.windSpeedMaxMPH = windSpeedMaxMPH;
    }

    void setWindSpeedMaxKmH(float windSpeedMaxKmH) {
        this.windSpeedMaxKmH = windSpeedMaxKmH;
    }

    void setWindSpeedMaxKnots(float windSpeedMaxKnots) {
        this.windSpeedMaxKnots = windSpeedMaxKnots;
    }

    void setWindSpeedMaxMS(float windSpeedMaxMS) {
        this.windSpeedMaxMS = windSpeedMaxMS;
    }

    void setPressureMaxInches(float pressureMaxInches) {
        this.pressureMaxInches = pressureMaxInches;
    }

    void setPressureMaxMillibars(float pressureMaxMillibars) {
        this.pressureMaxMillibars = pressureMaxMillibars;
    }

    void setPressureMinInches(float pressureMinInches) {
        this.pressureMinInches = pressureMinInches;
    }

    void setPressureMinMillibars(float pressureMinMillibars) {
        this.pressureMinMillibars = pressureMinMillibars;
    }
}
