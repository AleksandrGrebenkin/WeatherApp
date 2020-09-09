package com.github.alexandrgrebenkin.weatherapp.ui.fragment;

import android.location.Address;
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

import com.github.alexandrgrebenkin.weatherapp.ui.event.CurrentWeatherLoaderEvent;
import com.github.alexandrgrebenkin.weatherapp.ui.event.ForecastWeatherLoaderEvent;
import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.ui.adapter.DayForecastAdapter;
import com.github.alexandrgrebenkin.weatherapp.ui.loader.WeatherLoader;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.CurrentWeatherViewModel;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.ForecastWeatherViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class HomeFragment extends Fragment {

    public static final String ADDRESS = "com.github.alexandrgrebenkin.weatherapp.ADDRESS";

    private TextView temperature;
    private TextView wind;
    private TextView pressure;

    private RecyclerView dayOfWeekRecyclerView;

    private WeatherLoader weatherLoader;
    private Address address;

    public static HomeFragment newInstance(Address address) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putParcelable(ADDRESS, address);
        homeFragment.setArguments(args);
        return homeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        address = getArguments().getParcelable(ADDRESS);
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
        EventBus.getDefault().register(this);
        updateWeather();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void initialize() {
        View view = getView();
        if (view != null) {
            temperature = view.findViewById(R.id.fragment_home__tv_temperature);
            wind = view.findViewById(R.id.fragment_home__tv_wind);
            pressure = view.findViewById(R.id.fragment_home__tv_pressure);
            dayOfWeekRecyclerView = view.findViewById(R.id.fragment_home__rv_day_info);
            weatherLoader = new WeatherLoader(getResources());
        }
    }

    private void updateWeather() {
        weatherLoader.getCurrentWeather(address);
        weatherLoader.getForecastWeather(address);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(CurrentWeatherLoaderEvent event) {
        updateCurrentWeather(event.getCurrentWeatherViewModel());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(ForecastWeatherLoaderEvent event) {
        updateForecastWeather(event.getForecastWeatherViewModel());
    }
}