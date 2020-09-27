package com.github.alexandrgrebenkin.weatherapp.data.rest.weatherunlocked;

import android.location.Address;
import android.util.Log;

import com.github.alexandrgrebenkin.weatherapp.BuildConfig;
import com.github.alexandrgrebenkin.weatherapp.data.entity.CurrentWeather;
import com.github.alexandrgrebenkin.weatherapp.data.entity.WeatherCondition;
import com.github.alexandrgrebenkin.weatherapp.data.entity.WeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.data.provider.WeatherProvider;
import com.github.alexandrgrebenkin.weatherapp.data.entity.DayWeather;
import com.github.alexandrgrebenkin.weatherapp.data.entity.ForecastWeather;
import com.github.alexandrgrebenkin.weatherapp.data.rest.openweathermap.entities.WeatherRestModel;
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
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WeatherUnlockedProvider implements WeatherProvider {
    private static final String TAG = "WEATHER_APP";
    private static final String WEATHER_URL =
            "http://api.weatherunlocked.com/api/{TYPE}/{LAT},{LON}?app_id={APP_ID}&app_key={APP_KEY}"
                    .replace("{APP_ID}", BuildConfig.WEATHER_APP_ID)
                    .replace("{APP_KEY}", BuildConfig.WEATHER_APP_KEY);

    @Override
    public void loadWeatherInfo(Address address, WeatherInfoListener weatherInfoListener) {
        getWeatherInfo(address).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weatherInfo -> {
                    weatherInfoListener.onLoad(weatherInfo);
                });
    }

    private Observable<WeatherInfo> getWeatherInfo(Address address) {
        return Observable.create(emitter -> {
            WeatherInfo weatherInfo = new WeatherInfo(getCurrentWeather(address),
                    getForecastWeather(address));
            emitter.onNext(weatherInfo);
        });
    }

    private CurrentWeather getCurrentWeather(Address address) {
        CurrentWeatherRequest request = getCurrentWeatherRequest(address);
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setCityName(address.getLocality());
        currentWeather.setTempCelsius(request.getTempC());
        currentWeather.setWindSpeedMS(request.getWindSpeedMS());
        currentWeather.setPressureMm(request.getPressureInches() * 25.4f);
        currentWeather.setWeatherCondition(getWeatherCondition(request.getWeatherCode()));
        return currentWeather;
    }

    private ForecastWeather getForecastWeather(Address address) {
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

    private List<DayWeather> getDayWeatherList(List<DayWeatherRequest> dayWeatherRequestList) {
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

    private WeatherCondition getWeatherCondition(float weatherCode) {
        int weatherId = (int) weatherCode;
        if (weatherId == 0) {
            return WeatherCondition.CLEAR;
        } else if (weatherId >= 1 && weatherId <= 3) {
            return WeatherCondition.CLOUDS;
        } else if (weatherId >= 10 && weatherId <= 49) {
            return WeatherCondition.FOG;
        } else if (weatherId >= 50 && weatherId <= 57) {
            return WeatherCondition.DRIZZLE;
        } else if (weatherId >= 60 && weatherId <= 67) {
            return WeatherCondition.RAIN;
        } else if (weatherId >= 68 && weatherId <= 79) {
            return WeatherCondition.SNOW;
        } else if (weatherId >= 80 && weatherId <= 82) {
            return WeatherCondition.RAIN;
        } else if (weatherId >= 83 && weatherId <= 88) {
            return WeatherCondition.SNOW;
        } else if (weatherId >= 91 && weatherId <= 94) {
            return WeatherCondition.THUNDERSTORM;
        } else {
            return WeatherCondition.UNKNOWN;
        }
    }

        private String getLines (BufferedReader in){
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
