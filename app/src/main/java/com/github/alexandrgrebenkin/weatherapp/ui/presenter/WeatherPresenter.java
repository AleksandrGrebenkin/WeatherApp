package com.github.alexandrgrebenkin.weatherapp.ui.presenter;

import android.content.res.Resources;

import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.data.entity.Place;
import com.github.alexandrgrebenkin.weatherapp.data.manager.WeatherDataManager;
import com.github.alexandrgrebenkin.weatherapp.data.entity.CurrentWeather;
import com.github.alexandrgrebenkin.weatherapp.data.entity.DayWeather;
import com.github.alexandrgrebenkin.weatherapp.data.entity.ForecastWeather;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.CurrentWeatherViewModel;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.DayWeatherViewModel;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.ForecastWeatherViewModel;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.PlaceViewModel;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WeatherPresenter {
    private WeatherDataManager weatherDataManager;
    private Resources resources;

    public WeatherPresenter(Resources resources) {
        this.weatherDataManager = new WeatherDataManager();
        this.resources = resources;
    }

    public CurrentWeatherViewModel getCurrentWeather(PlaceViewModel placeViewModel) {
        CurrentWeatherViewModel currentWeatherViewModel = new CurrentWeatherViewModel();
        CurrentWeather currentWeather = weatherDataManager
                .getCurrentWeather(getPlaceFromViewModel(placeViewModel));

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

    public ForecastWeatherViewModel getForecastWeather(PlaceViewModel placeViewModel) {
        ForecastWeatherViewModel forecastWeatherViewModel = new ForecastWeatherViewModel();
        ForecastWeather forecastWeather = weatherDataManager
                .getForecastWeather(getPlaceFromViewModel(placeViewModel));
        forecastWeatherViewModel.setDayWeatherViewModelList(
                getWeatherViewModelList(forecastWeather.getDayWeatherList()));
        return forecastWeatherViewModel;
    }

    private String getResourceString (int id) {
        return resources.getString(id);
    }

    private List<DayWeatherViewModel> getWeatherViewModelList (List<DayWeather> dayWeatherList) {
        List<DayWeatherViewModel> dayWeatherViewModelList = new ArrayList<>();
        Locale locale = resources.getConfiguration().getLocales().get(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM", locale);
        for (int i = 0; i < dayWeatherList.size(); i++) {
            DayWeather dayWeather = dayWeatherList.get(i);
            DayWeatherViewModel dayWeatherViewModel = new DayWeatherViewModel();

            String date = dayWeather.getDate().format(formatter);
            dayWeatherViewModel.setDate(date);

            String dayOfWeek = dayWeather.getDate().getDayOfWeek().getDisplayName(TextStyle.FULL, locale);
            dayWeatherViewModel.setDayOfWeek(dayOfWeek);

            String maxTemperature = formatTemperature(dayWeather.getMaxTempCelsius());
            dayWeatherViewModel.setMaxTemperature(maxTemperature);

            String minTemperature = formatTemperature(dayWeather.getMinTempCelsius());
            dayWeatherViewModel.setMinTemperature(minTemperature);

            dayWeatherViewModelList.add(dayWeatherViewModel);
        }
        return dayWeatherViewModelList;
    }

    private String getRoundValueFromFloat (float f) {
        DecimalFormat decimalFormat = new DecimalFormat("###");
        return decimalFormat.format(f);
    }

    private String getRoundValueFromFloat (float f, int precision) {
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

    private Place getPlaceFromViewModel(PlaceViewModel placeViewModel) {
        Place place = new Place();
        place.setName(placeViewModel.getName());
        place.setDisplayName(placeViewModel.getDisplayName());
        place.setLat(placeViewModel.getLat());
        place.setLon(placeViewModel.getLon());
        return place;
    }

}
