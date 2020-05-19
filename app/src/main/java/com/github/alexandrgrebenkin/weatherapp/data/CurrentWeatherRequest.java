package com.github.alexandrgrebenkin.weatherapp.data;

import android.os.Parcel;
import android.os.Parcelable;

public class CurrentWeatherRequest {
    private float lat;
    private float lon;
    private float alt_m;
    private float alt_ft;
    private String wx_desc;
    private float wx_code;
    private String wx_icon;
    private float temp_c;
    private float temp_f;
    private float feelslike_c;
    private float feelslike_f;
    private float humid_pct;
    private float windspd_mph;
    private float windspd_kmh;
    private float windspd_kts;
    private float windspd_ms;
    private float winddir_deg;
    private String winddir_compass;
    private float cloudtotal_pct;
    private float vis_km;
    private float vis_mi;
    private String vis_desc = null;
    private float slp_mb;
    private float slp_in;
    private float dewpoint_c;
    private float dewpoint_f;


    // Getter Methods

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    public float getAlt_m() {
        return alt_m;
    }

    public float getAlt_ft() {
        return alt_ft;
    }

    public String getWx_desc() {
        return wx_desc;
    }

    public float getWx_code() {
        return wx_code;
    }

    public String getWx_icon() {
        return wx_icon;
    }

    public float getTemp_c() {
        return temp_c;
    }

    public float getTemp_f() {
        return temp_f;
    }

    public float getFeelslike_c() {
        return feelslike_c;
    }

    public float getFeelslike_f() {
        return feelslike_f;
    }

    public float getHumid_pct() {
        return humid_pct;
    }

    public float getWindspd_mph() {
        return windspd_mph;
    }

    public float getWindspd_kmh() {
        return windspd_kmh;
    }

    public float getWindspd_kts() {
        return windspd_kts;
    }

    public float getWindspd_ms() {
        return windspd_ms;
    }

    public float getWinddir_deg() {
        return winddir_deg;
    }

    public String getWinddir_compass() {
        return winddir_compass;
    }

    public float getCloudtotal_pct() {
        return cloudtotal_pct;
    }

    public float getVis_km() {
        return vis_km;
    }

    public float getVis_mi() {
        return vis_mi;
    }

    public String getVis_desc() {
        return vis_desc;
    }

    public float getSlp_mb() {
        return slp_mb;
    }

    public float getSlp_in() {
        return slp_in;
    }

    public float getDewpoint_c() {
        return dewpoint_c;
    }

    public float getDewpoint_f() {
        return dewpoint_f;
    }

    // Setter Methods

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public void setAlt_m(float alt_m) {
        this.alt_m = alt_m;
    }

    public void setAlt_ft(float alt_ft) {
        this.alt_ft = alt_ft;
    }

    public void setWx_desc(String wx_desc) {
        this.wx_desc = wx_desc;
    }

    public void setWx_code(float wx_code) {
        this.wx_code = wx_code;
    }

    public void setWx_icon(String wx_icon) {
        this.wx_icon = wx_icon;
    }

    public void setTemp_c(float temp_c) {
        this.temp_c = temp_c;
    }

    public void setTemp_f(float temp_f) {
        this.temp_f = temp_f;
    }

    public void setFeelslike_c(float feelslike_c) {
        this.feelslike_c = feelslike_c;
    }

    public void setFeelslike_f(float feelslike_f) {
        this.feelslike_f = feelslike_f;
    }

    public void setHumid_pct(float humid_pct) {
        this.humid_pct = humid_pct;
    }

    public void setWindspd_mph(float windspd_mph) {
        this.windspd_mph = windspd_mph;
    }

    public void setWindspd_kmh(float windspd_kmh) {
        this.windspd_kmh = windspd_kmh;
    }

    public void setWindspd_kts(float windspd_kts) {
        this.windspd_kts = windspd_kts;
    }

    public void setWindspd_ms(float windspd_ms) {
        this.windspd_ms = windspd_ms;
    }

    public void setWinddir_deg(float winddir_deg) {
        this.winddir_deg = winddir_deg;
    }

    public void setWinddir_compass(String winddir_compass) {
        this.winddir_compass = winddir_compass;
    }

    public void setCloudtotal_pct(float cloudtotal_pct) {
        this.cloudtotal_pct = cloudtotal_pct;
    }

    public void setVis_km(float vis_km) {
        this.vis_km = vis_km;
    }

    public void setVis_mi(float vis_mi) {
        this.vis_mi = vis_mi;
    }

    public void setVis_desc(String vis_desc) {
        this.vis_desc = vis_desc;
    }

    public void setSlp_mb(float slp_mb) {
        this.slp_mb = slp_mb;
    }

    public void setSlp_in(float slp_in) {
        this.slp_in = slp_in;
    }

    public void setDewpoint_c(float dewpoint_c) {
        this.dewpoint_c = dewpoint_c;
    }

    public void setDewpoint_f(float dewpoint_f) {
        this.dewpoint_f = dewpoint_f;
    }
}
