package com.github.alexandrgrebenkin.weatherapp.Interface;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.alexandrgrebenkin.weatherapp.Data.WeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.Data.WeatherInfoProvider;
import com.github.alexandrgrebenkin.weatherapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherInfoFragment extends Fragment {

    static final String WEATHER_INFO = "com.github.alexandrgrebenkin.weatherapp.WEATHER_INFO";

    private TextView city;
    private TextView temperatureValue;
    private TextView windValue;
    private TextView pressureValue;

    private ImageButton citiesList;
    private ImageButton settings;

    private boolean isLandscape;

    private WeatherInfo weatherInfo;

    private Listener listener;

    interface Listener {
        void onSettingsClicked();

        void onCitiesListClicked();

        void onCityClicked(String cityName);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (Listener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            weatherInfo = savedInstanceState.getParcelable(WEATHER_INFO);
        } else {
            loadWeatherInfo();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather_info, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
        updateFragmentData();
        startCitiesListClickListener();
        startSettingsClickListener();
        startCityClickListener();
    }

    private void initialize() {
        View view = getView();
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        city = view.findViewById(R.id.fragment_weather_info__tv_city);
        temperatureValue = view.findViewById(R.id.fragment_weather_info__tv_temperature_value);
        windValue = view.findViewById(R.id.fragment_weather_info__tv_wind_value);
        pressureValue = view.findViewById(R.id.fragment_weather_info__tv_pressure_value);
        citiesList = view.findViewById(R.id.fragment_weather_info__ib_cities);
        settings = view.findViewById(R.id.fragment_weather_info__ib_settings);

        if (isLandscape) {
            citiesList.setVisibility(View.INVISIBLE);
        }
    }

    private void startCitiesListClickListener() {
        citiesList.setOnClickListener((v) -> {
            listener.onCitiesListClicked();
        });
    }

    private void startSettingsClickListener() {
        settings.setOnClickListener((v) -> {
            listener.onSettingsClicked();
        });
    }

    private void startCityClickListener() {
        city.setOnClickListener((v) -> {
            listener.onCityClicked(city.getText().toString());
        });
    }

    void updateWeatherInfo(WeatherInfo weatherInfo) {
        this.weatherInfo = weatherInfo;
        updateFragmentData();
    }

    private void updateFragmentData() {
        city.setText(weatherInfo.getCityName());
        temperatureValue.setText(weatherInfo.getTemperatureValue());
        windValue.setText(weatherInfo.getWindValue());
        pressureValue.setText(weatherInfo.getPressureValue());
    }

    private void loadWeatherInfo() {
            WeatherInfoProvider weatherInfoProvider = new WeatherInfoProvider();
            weatherInfo = weatherInfoProvider.getWeatherInfo("Example City");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(WEATHER_INFO, weatherInfo);
    }
}
