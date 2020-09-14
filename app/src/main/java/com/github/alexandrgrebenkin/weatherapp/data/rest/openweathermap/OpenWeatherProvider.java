package com.github.alexandrgrebenkin.weatherapp.data.rest.openweathermap;

import android.location.Address;
import android.util.Log;

import androidx.annotation.NonNull;

import com.github.alexandrgrebenkin.weatherapp.data.entity.CurrentWeather;
import com.github.alexandrgrebenkin.weatherapp.data.entity.DayWeather;
import com.github.alexandrgrebenkin.weatherapp.data.entity.ForecastWeather;
import com.github.alexandrgrebenkin.weatherapp.data.entity.WeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.data.provider.WeatherInfoListener;
import com.github.alexandrgrebenkin.weatherapp.data.provider.WeatherProvider;
import com.github.alexandrgrebenkin.weatherapp.data.rest.openweathermap.entities.CurrentRestModel;
import com.github.alexandrgrebenkin.weatherapp.data.rest.openweathermap.entities.DailyRestModel;
import com.github.alexandrgrebenkin.weatherapp.data.rest.openweathermap.entities.OpenWeatherRequestRestModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpenWeatherProvider implements WeatherProvider {
    private final String API_KEY = "32e91767f77ab9a08f6f59f862febfb3";
    private Address address;

    @Override
    public void loadWeatherInfo(Address address, WeatherInfoListener weatherInfoListener) {
        this.address = address;
        OpenWeatherRepo.getInstance().getAPI().loadWeather(
                String.valueOf(address.getLatitude()),
                String.valueOf(address.getLongitude()),
                API_KEY,
                "metric",
                "ru"
        ).enqueue(new Callback<OpenWeatherRequestRestModel>() {
            @Override
            public void onResponse(@NonNull Call<OpenWeatherRequestRestModel> call,
                                   @NonNull Response<OpenWeatherRequestRestModel> response) {
                if (response.body() != null && response.isSuccessful()) {
                    Log.d("OPEN_RESPONSE", response.message());
                    weatherInfoListener.onLoad(getWeatherInfo(response.body()));
                }
            }

            @Override
            public void onFailure(Call<OpenWeatherRequestRestModel> call, Throwable t) {

            }
        });
    }

    private WeatherInfo getWeatherInfo(OpenWeatherRequestRestModel openWeatherRequestRestModel) {
        WeatherInfo weatherInfo = new WeatherInfo(getCurrentWeather(openWeatherRequestRestModel),
                getForecastWeather(openWeatherRequestRestModel));
        return weatherInfo;
    }

    private ForecastWeather getForecastWeather(OpenWeatherRequestRestModel openWeatherRequestRestModel) {
        ForecastWeather forecastWeather = new ForecastWeather();
        forecastWeather.setDayWeatherList(getDayWeatherList(openWeatherRequestRestModel.daily));
        return forecastWeather;
    }

    private List<DayWeather> getDayWeatherList(List<DailyRestModel> dailyRestModelList) {
        List<DayWeather> dayWeatherList = new ArrayList<>();
        for (int i = 0; i < dailyRestModelList.size(); i++) {
            DailyRestModel dailyRestModel = dailyRestModelList.get(i);
            DayWeather dayWeather = new DayWeather();
            dayWeather.setDate(new Date(dailyRestModel.dt * 1000L));
            dayWeather.setMaxTempCelsius(dailyRestModel.temp.max);
            dayWeather.setMinTempCelsius(dailyRestModel.temp.min);
            dayWeatherList.add(dayWeather);
        }
        return dayWeatherList;
    }

    private CurrentWeather getCurrentWeather(OpenWeatherRequestRestModel openWeatherRequestRestModel) {
        CurrentRestModel currentRestModel = openWeatherRequestRestModel.current;
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setCityName(address.getLocality());
        currentWeather.setTempCelsius(currentRestModel.temp);
        currentWeather.setPressureMm(currentRestModel.pressure * 0.75f);
        currentWeather.setWindSpeedMS(currentRestModel.windSpeed);
        return currentWeather;
    }


}
