package com.github.alexandrgrebenkin.weatherapp.data;

import android.util.Log;

import com.github.alexandrgrebenkin.weatherapp.BuildConfig;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class InternetWeatherProvider implements WeatherProvider {
    private static final String TAG = "WEATHER_APP";
    private static final String WEATHER_URL =
            "http://api.weatherunlocked.com/api/{TYPE}/51.50,-0.12?app_id={APP_ID}&app_key={APP_KEY}";
    private static final String WEATHER_APP_ID = BuildConfig.WEATHER_APP_ID;
    private static final String WEATHER_APP_KEY = BuildConfig.WEATHER_APP_KEY;

    public CurrentWeatherRequest getCurrentWeatherRequest() {
        CurrentWeatherRequest currentWeatherRequest = null;
        String weatherUrl = WEATHER_URL
                .replace("{TYPE}", "current")
                .replace("{APP_ID}", WEATHER_APP_ID)
                .replace("{APP_KEY}", WEATHER_APP_KEY);
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
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "Incorrect URL");
            e.printStackTrace();
        }
        return currentWeatherRequest;
    }

    @Override
    public CurrentWeatherInfo getCurrentWeatherInfo(String cityName) {
        CurrentWeatherRequest request = getCurrentWeatherRequest();
        String temperature = String.valueOf(request.getTemp_c());
        String wind = String.valueOf(request.getWindspd_ms());
        String pressure = String.valueOf(request.getSlp_mb());
        return new CurrentWeatherInfo(cityName, temperature, wind, pressure);
    }

    @Override
    public List<DayWeatherInfo> getForecastWeatherInfo(String cityName) {
        return null;
    }
}
