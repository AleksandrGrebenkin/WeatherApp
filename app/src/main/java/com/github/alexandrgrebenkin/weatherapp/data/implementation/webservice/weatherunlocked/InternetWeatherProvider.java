package com.github.alexandrgrebenkin.weatherapp.data.implementation.webservice.weatherunlocked;

import android.location.Address;
import android.util.Log;

import com.github.alexandrgrebenkin.weatherapp.BuildConfig;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class InternetWeatherProvider implements WeatherProvider {
    private static final String TAG = "WEATHER_APP";
    private static final String WEATHER_URL =
            "http://api.weatherunlocked.com/api/{TYPE}/{LAT},{LON}?app_id={APP_ID}&app_key={APP_KEY}"
                    .replace("{APP_ID}", BuildConfig.WEATHER_APP_ID)
                    .replace("{APP_KEY}", BuildConfig.WEATHER_APP_KEY);

    @Override
    public CurrentWeather getCurrentWeather(Address address) {
        CurrentWeatherRequest request = getCurrentWeatherRequest(address);
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setCityName(address.getLocality());
        currentWeather.setTempCelsius(request.getTempC());
        currentWeather.setWindSpeedMS(request.getWindSpeedMS());
        currentWeather.setPressureMm(request.getPressureInches() * 25.4f);
        return currentWeather;
    }

    @Override
    public ForecastWeather getForecastWeather(Address address) {
        ForecastWeatherRequest forecastRequest = getForecastWeatherRequest(address);
        ForecastWeather forecastWeather = new ForecastWeather();
        forecastWeather.setDayWeatherList(getDayWeatherList(forecastRequest.getDays()));
        return forecastWeather;
    }

    private ForecastWeatherRequest getForecastWeatherRequest(Address address) {
        ForecastWeatherRequest forecastWeatherRequest = null;
        String weatherUrl = WEATHER_URL
                .replace("{TYPE}", "forecast")
                .replace("{LAT}", String.valueOf(address.getLatitude()))
                .replace("{LON}", String.valueOf(address.getLongitude()));
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

    private CurrentWeatherRequest getCurrentWeatherRequest(Address address) {
        CurrentWeatherRequest currentWeatherRequest = null;
        String weatherUrl = WEATHER_URL
                .replace("{TYPE}", "current")
                .replace("{LAT}", String.valueOf(address.getLatitude()))
                .replace("{LON}", String.valueOf(address.getLongitude()));
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
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                dayWeather.setDate(sdf.parse(dayRequest.getDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dayWeather.setMaxTempCelsius(dayRequest.getTempMaxC());
            dayWeather.setMinTempCelsius(dayRequest.getTempMinC());
            dayWeatherList.add(dayWeather);
        }
        return dayWeatherList;
    }

    private String getLines(BufferedReader in) {
        StringBuilder sb = new StringBuilder();
        try {
            String line = null;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
