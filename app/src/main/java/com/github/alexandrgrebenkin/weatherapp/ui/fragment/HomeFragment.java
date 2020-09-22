package com.github.alexandrgrebenkin.weatherapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.ui.adapter.DayForecastAdapter;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.CurrentWeatherViewModel;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.ForecastWeatherViewModel;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.WeatherViewModel;

import java.util.Objects;

public class HomeFragment extends Fragment {

    public static final String WEATHER_VIEW_MODEL = "com.github.alexandrgrebenkin.weatherapp.WEATHER_VIEW_MODEL";

    private OnMenuItemSelectedListener menuListener;

    private TextView temperature;
    private TextView wind;
    private TextView pressure;
    private SimpleDraweeView weatherImage;

    private SearchView searchView;

    private RecyclerView dayOfWeekRecyclerView;
    private WeatherViewModel weatherViewModel;

    public interface OnMenuItemSelectedListener {
        void onSearchItemSelected(String cityNameQuery);

        void onCurrentLocationItemSelected();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnMenuItemSelectedListener) {
            menuListener = (OnMenuItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement "
                    + this.getClass().getName()
                    + OnMenuItemSelectedListener.class.getName());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            weatherViewModel = getArguments().getParcelable(WEATHER_VIEW_MODEL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
        updateWeather();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
        searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                menuListener.onSearchItemSelected(query);
                searchView.onActionViewCollapsed();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_location:
                menuListener.onCurrentLocationItemSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static HomeFragment newInstance(WeatherViewModel weatherViewModel) {
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
            weatherImage = view.findViewById(R.id.fragment_home__iv_weather_image);
            dayOfWeekRecyclerView = view.findViewById(R.id.fragment_home__rv_day_info);
        }
    }

    private void updateWeather() {
        updateCurrentWeather(weatherViewModel.getCurrentWeatherViewModel());
        updateForecastWeather(weatherViewModel.getForecastWeatherViewModel());
    }

    private void updateCurrentWeather(CurrentWeatherViewModel currentWeatherViewModel) {
        temperature.setText(currentWeatherViewModel.getTemperature());
        wind.setText(currentWeatherViewModel.getWindSpeed());
        pressure.setText(currentWeatherViewModel.getPressure());
        weatherImage.setImageURI(currentWeatherViewModel.getImageUri());
    }

    private void updateForecastWeather(ForecastWeatherViewModel forecastWeatherViewModel) {
        DayForecastAdapter dayForecastAdapter = new DayForecastAdapter(forecastWeatherViewModel);
        dayOfWeekRecyclerView.setAdapter(dayForecastAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                Objects.requireNonNull(getActivity()).getApplicationContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(
                Objects.requireNonNull(ContextCompat.getDrawable(getActivity(), R.drawable.separator)));
        dayOfWeekRecyclerView.addItemDecoration(itemDecoration);
        dayOfWeekRecyclerView.setLayoutManager(layoutManager);
    }
}