package com.github.alexandrgrebenkin.weatherapp.ui.loader;

import android.content.res.Resources;
import android.location.Address;

import com.github.alexandrgrebenkin.weatherapp.data.entity.WeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.data.manager.WeatherDataManager;
import com.github.alexandrgrebenkin.weatherapp.data.entity.CurrentWeather;
import com.github.alexandrgrebenkin.weatherapp.data.entity.DayWeather;
import com.github.alexandrgrebenkin.weatherapp.data.entity.ForecastWeather;
import com.github.alexandrgrebenkin.weatherapp.ui.event.UnknownExceptionEvent;
import com.github.alexandrgrebenkin.weatherapp.ui.event.WeatherLoaderEvent;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.CurrentWeatherViewModel;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.DayWeatherViewModel;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.ForecastWeatherViewModel;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.WeatherViewModel;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WeatherLoader {
    private WeatherDataManager weatherDataManager;
    private Resources resources;

    public WeatherLoader(Resources resources) {
        this.weatherDataManager = new WeatherDataManager();
        this.resources = resources;
    }

    public void loadWeather(Address address) {
        new Thread(() -> {
            try{
                WeatherInfo weatherInfo = weatherDataManager.getWeatherInfo(address);
                WeatherViewModel weatherViewModel = new WeatherViewModel(
                        getCurrentWeatherViewModel(weatherInfo.getCurrentWeather()),
                        getForecastWeatherViewModel(weatherInfo.getForecastWeather())
                );
                WeatherLoaderEvent event = new WeatherLoaderEvent(weatherViewModel);
                EventBus.getDefault().post(event);
            } catch (Exception e) {
                UnknownExceptionEvent event = new UnknownExceptionEvent(e);
                EventBus.getDefault().post(event);
            }
        }).start();

    }

    private CurrentWeatherViewModel getCurrentWeatherViewModel(CurrentWeather currentWeather) {
        CurrentWeatherViewModel currentWeatherViewModel = new CurrentWeatherViewModel();
        String cityName = currentWeather.getCityName();
        currentWeatherViewModel.setCityName(cityName);

        String temperature = formatTemperature(currentWeather.getTempCelsius());
        currentWeatherViewModel.setTemperature(temperature);

        String windSpeed = getRoundValueFromFloat(currentWeather.getWindSpeedMS())
                + " " + getResourceString(R.string.meter_per_second);
        currentWeatherViewModel.setWindSpeed(windSpeed);

        String pressure = getRoundValueFromFloat(currentWeather.getPressureMm())
                + " " + getResourceString(R.string.mm_of_mercury);
        currentWeatherViewModel.setPressure(pressure);
        return currentWeatherViewModel;
    }

    private ForecastWeatherViewModel getForecastWeatherViewModel(ForecastWeather forecastWeather) {
        ForecastWeatherViewModel forecastWeatherViewModel = new ForecastWeatherViewModel();
        forecastWeatherViewModel.setDayWeatherViewModelList(
                getWeatherViewModelList(forecastWeather.getDayWeatherList()));
        return forecastWeatherViewModel;
    }

    private String getResourceString(int id) {
        return resources.getString(id);
    }

    private List<DayWeatherViewModel> getWeatherViewModelList(List<DayWeather> dayWeatherList) {
        List<DayWeatherViewModel> dayWeatherViewModelList = new ArrayList<>();
        for (int i = 0; i < dayWeatherList.size(); i++) {
            DayWeather dayWeather = dayWeatherList.get(i);
            DayWeatherViewModel dayWeatherViewModel = new DayWeatherViewModel();

            SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM", Locale.getDefault());
            dayWeatherViewModel.setDate(dateFormat.format(dayWeather.getDate()));


            SimpleDateFormat dayOfWeekFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
            String dayOfWeek = dayOfWeekFormat.format(dayWeather.getDate());
            dayWeatherViewModel.setDayOfWeek(dayOfWeek);

            String maxTemperature = formatTemperature(dayWeather.getMaxTempCelsius());
            dayWeatherViewModel.setMaxTemperature(maxTemperature);

            String minTemperature = formatTemperature(dayWeather.getMinTempCelsius());
            dayWeatherViewModel.setMinTemperature(minTemperature);

            dayWeatherViewModelList.add(dayWeatherViewModel);
        }
        return dayWeatherViewModelList;
    }

    private String getRoundValueFromFloat(float f) {
        DecimalFormat decimalFormat = new DecimalFormat("###");
        return decimalFormat.format(f);
    }

    private String getRoundValueFromFloat(float f, int precision) {
        if (precision < 0) throw new IllegalArgumentException("Precision can't be less then zero");

        DecimalFormat decimalFormat;
        if (precision == 0) {
            decimalFormat = new DecimalFormat("###");
        } else {
            decimalFormat = new DecimalFormat("###.");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < precision; i++) {
                stringBuilder.append("#");
            }
        }
        return decimalFormat.format(f);
    }

    private String formatTemperature(float temperature) {
        StringBuilder sb = new StringBuilder();
        if (temperature > 0) {
            sb.append("+");
        }
        sb.append(getRoundValueFromFloat(temperature));
        sb.append(getResourceString(R.string.celsius));
        return sb.toString();
    }

}
