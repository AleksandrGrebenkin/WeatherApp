package com.github.alexandrgrebenkin.weatherapp.data.providers.implementation;

import android.util.Log;

import com.github.alexandrgrebenkin.weatherapp.BuildConfig;
import com.github.alexandrgrebenkin.weatherapp.data.CurrentWeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.data.CurrentWeatherRequest;
import com.github.alexandrgrebenkin.weatherapp.data.DayWeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.data.PlaceInfo;
import com.github.alexandrgrebenkin.weatherapp.data.Utils;
import com.github.alexandrgrebenkin.weatherapp.data.providers.WeatherProvider;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class InternetWeatherProvider implements WeatherProvider {
    private static final String TAG = "WEATHER_APP";
    private static final String WEATHER_URL =
            "http://api.weatherunlocked.com/api/{TYPE}/{LAT},{LON}?app_id={APP_ID}&app_key={APP_KEY}"
                    .replace("{APP_ID}", BuildConfig.WEATHER_APP_ID)
                    .replace("{APP_KEY}", BuildConfig.WEATHER_APP_KEY);

    public CurrentWeatherRequest getCurrentWeatherRequest(PlaceInfo placeInfo) {
        CurrentWeatherRequest currentWeatherRequest = null;
        String weatherUrl = WEATHER_URL
                .replace("{TYPE}", "current")
                .replace("{LAT}", placeInfo.getLat())
                .replace("{LON}", placeInfo.getLon());
        try {
            final URL uri = new URL(weatherUrl);
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) uri.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10_000);
                InputStream inStream = urlConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(inStream);
                BufferedReader in = new BufferedReader(isr);
                String result = Utils.getLines(in);
                Gson gson = new Gson();
                final CurrentWeatherRequest request = gson.fromJson(result, CurrentWeatherRequest.class);
                currentWeatherRequest = request;
            } catch (IOException e) {
                Log.e(TAG, "Failed connection:" + e.getMessage());
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "Incorrect URL");
            e.printStackTrace();
        }
        return currentWeatherRequest;
    }

    @Override
    public CurrentWeatherInfo getCurrentWeatherInfo(PlaceInfo placeInfo) {
        CurrentWeatherRequest request = getCurrentWeatherRequest(placeInfo);
        String temperature = String.valueOf(request.getTemp_c());
        String wind = String.valueOf(request.getWindspd_ms());
        String pressure = String.valueOf(request.getSlp_mb());
        return new CurrentWeatherInfo(placeInfo.getName(), temperature, wind, pressure);
    }

    @Override
    public List<DayWeatherInfo> getForecastWeatherInfo(String cityName) {
        return null;
    }
}
