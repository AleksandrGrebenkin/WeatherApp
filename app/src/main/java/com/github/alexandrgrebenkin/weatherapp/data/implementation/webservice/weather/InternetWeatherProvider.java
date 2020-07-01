package com.github.alexandrgrebenkin.weatherapp.data.implementation.webservice.weather;

import android.util.Log;

import com.github.alexandrgrebenkin.weatherapp.BuildConfig;
import com.github.alexandrgrebenkin.weatherapp.data.entity.Place;
import com.github.alexandrgrebenkin.weatherapp.data.entity.CurrentWeather;
import com.github.alexandrgrebenkin.weatherapp.data.provider.WeatherProvider;
import com.github.alexandrgrebenkin.weatherapp.data.entity.DayWeather;
import com.github.alexandrgrebenkin.weatherapp.data.entity.ForecastWeather;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InternetWeatherProvider implements WeatherProvider {
    private static final String TAG = "WEATHER_APP";
    private static final String WEATHER_URL =
            "http://api.weatherunlocked.com/api/{TYPE}/{LAT},{LON}?app_id={APP_ID}&app_key={APP_KEY}"
                    .replace("{APP_ID}", BuildConfig.WEATHER_APP_ID)
                    .replace("{APP_KEY}", BuildConfig.WEATHER_APP_KEY);

    @Override
    public CurrentWeather getCurrentWeather(Place place) {
        CurrentWeatherRequest request = getCurrentWeatherRequest(place);
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setCityName(place.getName());
        currentWeather.setTempCelsius(request.getTempC());
        currentWeather.setWindSpeedMS(request.getWindSpeedMS());
        currentWeather.setPressureMm(request.getPressureInches() * 25.4f);
        return currentWeather;
    }

    @Override
    public ForecastWeather getForecastWeather(Place place) {
        ForecastWeatherRequest forecastRequest = getForecastWeatherRequest(place);
        ForecastWeather forecastWeather = new ForecastWeather();
        forecastWeather.setDayWeatherList(getDayWeatherList(forecastRequest.getDays()));
        return forecastWeather;
    }

    private ForecastWeatherRequest getForecastWeatherRequest(Place place) {
        ForecastWeatherRequest forecastWeatherRequest = null;
        String weatherUrl = WEATHER_URL
                .replace("{TYPE}", "forecast")
                .replace("{LAT}", place.getLat())
                .replace("{LON}", place.getLon());
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
                String result = getLines(in);
                Gson gson = new Gson();
                forecastWeatherRequest = gson.fromJson(result, ForecastWeatherRequest.class);
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
        return forecastWeatherRequest;
    }

    private CurrentWeatherRequest getCurrentWeatherRequest(Place place) {
        CurrentWeatherRequest currentWeatherRequest = null;
        String weatherUrl = WEATHER_URL
                .replace("{TYPE}", "current")
                .replace("{LAT}", place.getLat())
                .replace("{LON}", place.getLon());
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
                String result = getLines(in);
                Gson gson = new Gson();
                currentWeatherRequest = gson.fromJson(result, CurrentWeatherRequest.class);
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

    private List<DayWeather> getDayWeatherList (List<DayWeatherRequest> dayWeatherRequestList) {
        List<DayWeather> dayWeatherList = new ArrayList<>();
        for (int i = 0; i < dayWeatherRequestList.size(); i++) {
            DayWeatherRequest dayRequest = dayWeatherRequestList.get(i);
            DayWeather dayWeather = new DayWeather();
            dayWeather.setDate(LocalDate.parse(dayRequest.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            dayWeather.setMaxTempCelsius(dayRequest.getTempMaxC());
            dayWeather.setMinTempCelsius(dayRequest.getTempMinC());
            dayWeatherList.add(dayWeather);
        }
        return dayWeatherList;
    }

    private String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }
}
