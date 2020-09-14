package com.github.alexandrgrebenkin.weatherapp.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.ui.adapter.DayForecastAdapter;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.CurrentWeatherViewModel;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.ForecastWeatherViewModel;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.WeatherViewModel;

public class HomeFragment extends Fragment {

    public static final String WEATHER_VIEW_MODEL = "com.github.alexandrgrebenkin.weatherapp.WEATHER_VIEW_MODEL";

    private TextView temperature;
    private TextView wind;
    private TextView pressure;

    private RecyclerView dayOfWeekRecyclerView;
    private WeatherViewModel weatherViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherViewModel = getArguments().getParcelable(WEATHER_VIEW_MODEL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
        updateWeather(weatherViewModel);
    }

    public static HomeFragment getInstance(WeatherViewModel weatherViewModel) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putParcelable(WEATHER_VIEW_MODEL, weatherViewModel);
        homeFragment.setArguments(args);
        return homeFragment;
    }

    private void init() {
        View view = getView();
        if (view != null) {
            temperature = view.findViewById(R.id.fragment_home__tv_temperature);
            wind = view.findViewById(R.id.fragment_home__tv_wind);
            pressure = view.findViewById(R.id.fragment_home__tv_pressure);
            dayOfWeekRecyclerView = view.findViewById(R.id.fragment_home__rv_day_info);
        }
    }

    private void updateWeather(WeatherViewModel weatherViewModel) {
        updateCurrentWeather(weatherViewModel.getCurrentWeatherViewModel());
        updateForecastWeather(weatherViewModel.getForecastWeatherViewModel());
    }

    private void updateCurrentWeather(CurrentWeatherViewModel currentWeatherViewModel) {
        temperature.setText(currentWeatherViewModel.getTemperature());
        wind.setText(currentWeatherViewModel.getWindSpeed());
        pressure.setText(currentWeatherViewModel.getPressure());
    }

    private void updateForecastWeather(ForecastWeatherViewModel forecastWeatherViewModel) {
        DayForecastAdapter dayForecastAdapter = new DayForecastAdapter(forecastWeatherViewModel);
        dayOfWeekRecyclerView.setAdapter(dayForecastAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.separator));
        dayOfWeekRecyclerView.addItemDecoration(itemDecoration);
        dayOfWeekRecyclerView.setLayoutManager(layoutManager);
    }
}