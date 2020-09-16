package com.github.alexandrgrebenkin.weatherapp.ui;

import android.content.Context;
import android.location.Address;
import android.net.Uri;

import com.github.alexandrgrebenkin.weatherapp.data.entity.WeatherCondition;
import com.github.alexandrgrebenkin.weatherapp.data.entity.WeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.data.manager.WeatherDataManager;
import com.github.alexandrgrebenkin.weatherapp.data.entity.CurrentWeather;
import com.github.alexandrgrebenkin.weatherapp.data.entity.DayWeather;
import com.github.alexandrgrebenkin.weatherapp.data.entity.ForecastWeather;
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

public class WeatherConverter {

    private Context context;

    public WeatherConverter(Context context) {
        this.context = context;
    }

    public WeatherViewModel getWeatherViewModel(WeatherInfo weatherInfo) {
        return new WeatherViewModel(
                getCurrentWeatherViewModel(weatherInfo.getCurrentWeather()),
                getForecastWeatherViewModel(weatherInfo.getForecastWeather())
        );
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

        currentWeatherViewModel.setImageUri(
                getWeatherImageUriFromCondition(currentWeather.getWeatherCondition()));

        return currentWeatherViewModel;
    }

    private ForecastWeatherViewModel getForecastWeatherViewModel(ForecastWeather forecastWeather) {
        ForecastWeatherViewModel forecastWeatherViewModel = new ForecastWeatherViewModel();
        forecastWeatherViewModel.setDayWeatherViewModelList(
                getWeatherViewModelList(forecastWeather.getDayWeatherList()));
        return forecastWeatherViewModel;
    }

    private String getResourceString(int id) {
        return context.getResources().getString(id);
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

    private Uri getWeatherImageUriFromCondition(WeatherCondition weatherCondition) {
        switch (weatherCondition) {
            case THUNDERSTORM:
                return Uri.parse("https://images.unsplash.com/photo-1566996675874-f00a61522322?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1328&q=80");
            case SNOW:
                return Uri.parse("https://images.unsplash.com/photo-1477601263568-180e2c6d046e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80");
            case RAIN:
                return Uri.parse("https://images.unsplash.com/photo-1444384851176-6e23071c6127?ixlib=rb-1.2.1&auto=format&fit=crop&w=1267&q=80");
            case DRIZZLE:
                return Uri.parse("https://images.unsplash.com/photo-1486016006115-74a41448aea2?ixlib=rb-1.2.1&auto=format&fit=crop&w=1347&q=80");
            case CLOUDS:
                return Uri.parse("https://images.unsplash.com/photo-1501630834273-4b5604d2ee31?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
            case TORNADO:
                return Uri.parse("https://images.unsplash.com/photo-1527482937786-6608f6e14c15?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80");
            case FOG:
                return Uri.parse("https://images.unsplash.com/photo-1479476437642-f85d89e5ad7b?ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
            case SMOKE:
                return Uri.parse("https://images.unsplash.com/photo-1569142916960-885f868d6123?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1393&q=80");
            case CLEAR:
                return Uri.parse("https://images.unsplash.com/photo-1590077428593-a55bb07c4665?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1302&q=80");
            default:
                return Uri.parse("https://images.unsplash.com/photo-1535391879778-3bae11d29a24?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80");
        }
    }

}
