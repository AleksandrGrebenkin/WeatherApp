package com.github.alexandrgrebenkin.weatherapp.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.ui.adapter.DayForecastAdapter;
import com.github.alexandrgrebenkin.weatherapp.ui.presenter.WeatherPresenter;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.CurrentWeatherViewModel;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.ForecastWeatherViewModel;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.PlaceViewModel;

public class HomeFragment extends Fragment {

    public static final String PLACE = "com.github.alexandrgrebenkin.weatherapp.PLACE";

    private TextView temperature;
    private TextView wind;
    private TextView pressure;

    private RecyclerView dayOfWeekRecyclerView;

    private WeatherPresenter weatherPresenter;
    private PlaceViewModel placeViewModel;

    public static HomeFragment newInstance(PlaceViewModel placeViewModel) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putParcelable(PLACE, placeViewModel);
        homeFragment.setArguments(args);
        return homeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        placeViewModel = getArguments().getParcelable(PLACE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        initialize();
        updateWeather(placeViewModel);
    }

    private void initialize() {
        View view = getView();
        if (view != null) {
            temperature = view.findViewById(R.id.fragment_home__tv_temperature);
            wind = view.findViewById(R.id.fragment_home__tv_wind);
            pressure = view.findViewById(R.id.fragment_home__tv_pressure);
            dayOfWeekRecyclerView = view.findViewById(R.id.fragment_home__rv_day_info);
            weatherPresenter = new WeatherPresenter(getResources());
        }
    }

    private void updateWeather(PlaceViewModel placeViewModel) {
        this.placeViewModel = placeViewModel;
        Handler handler = new Handler();
        new Thread(() -> {
            CurrentWeatherViewModel currentWeather = weatherPresenter.getCurrentWeather(placeViewModel);
            ForecastWeatherViewModel forecastWeather = weatherPresenter.getForecastWeather(placeViewModel);
            handler.post(() -> {
                updateCurrentWeather(currentWeather);
                updateForecastWeather(forecastWeather);
            });
        }).start();
    }

    private void updateCurrentWeather(CurrentWeatherViewModel currentWeatherViewModel) {
        this.temperature.setText(currentWeatherViewModel.getTemperature());
        this.wind.setText(currentWeatherViewModel.getWindSpeed());
        this.pressure.setText(currentWeatherViewModel.getPressure());
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