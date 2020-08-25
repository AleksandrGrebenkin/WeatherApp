package com.github.alexandrgrebenkin.weatherapp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.PlaceViewModel;
import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.CurrentWeatherViewModel;
import com.github.alexandrgrebenkin.weatherapp.ui.adapter.DayForecastAdapter;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.ForecastWeatherViewModel;
import com.github.alexandrgrebenkin.weatherapp.ui.presenter.WeatherPresenter;

public class MainActivity extends BaseActivity {

    private static final int REQUEST_CITY_CODE_ACTIVITY = 1;
    private static final int SETTING_CODE = 101;

    static final String PLACE = "com.github.alexandrgrebenkin.weatherapp.PLACE";

    private TextView city;
    private TextView temperature;
    private TextView wind;
    private TextView pressure;

    private ImageButton citiesList;
    private ImageButton settings;

    private RecyclerView dayOfWeekRecyclerView;

    private WeatherPresenter weatherPresenter;
    private PlaceViewModel placeViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            placeViewModel = new PlaceViewModel();
            placeViewModel.setName("Moscow");
            placeViewModel.setDisplayName("Moscow");
            placeViewModel.setLat("55.7504461");
            placeViewModel.setLon("37.6174943");
        } else {
            placeViewModel = savedInstanceState.getParcelable(PLACE);
        }

        initialize();
        updateWeather(placeViewModel);
        startCitiesClickListener();
        startSettingsClickListener();
        startCityClickListener();
    }

    private void initialize() {
        city = findViewById(R.id.activity_main__tv_city);
        temperature = findViewById(R.id.activity_main__tv_temperature);
        wind = findViewById(R.id.activity_main__tv_wind);
        pressure = findViewById(R.id.activity_main__tv_pressure);
        citiesList = findViewById(R.id.activity_main__ib_citiesList);
        settings = findViewById(R.id.activity_main__ib_settings);
        dayOfWeekRecyclerView = findViewById(R.id.activity_main__rv_day_info);
        weatherPresenter = new WeatherPresenter(getResources());
    }

    private void startCitiesClickListener() {
        citiesList.setOnClickListener((v) -> {
            Intent intent = new Intent(getApplicationContext(), CitiesActivity.class);
            startActivityForResult(intent, REQUEST_CITY_CODE_ACTIVITY);
        });
    }

    private void startSettingsClickListener() {
        settings.setOnClickListener((v) -> {
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivityForResult(intent, SETTING_CODE);
        });
    }

    private void startCityClickListener() {
        city.setOnClickListener((v) -> {
            String url = "https://yandex.ru/search/?text=" + city.getText().toString();
            Uri uri = Uri.parse(url);
            Intent browser = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(browser);
        });
    }

    private void updateForecastWeather(ForecastWeatherViewModel forecastWeatherViewModel) {
        DayForecastAdapter dayForecastAdapter = new DayForecastAdapter(forecastWeatherViewModel);
        dayOfWeekRecyclerView.setAdapter(dayForecastAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(),
                LinearLayoutManager.VERTICAL);
        itemDecoration.setDrawable(getDrawable(R.drawable.separator));
        dayOfWeekRecyclerView.addItemDecoration(itemDecoration);
        dayOfWeekRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CITY_CODE_ACTIVITY && resultCode == RESULT_OK) {
            PlaceViewModel placeViewModel = data.getParcelableExtra(PLACE);
            updateWeather(placeViewModel);
        }
        if (requestCode == SETTING_CODE) {
            recreate();
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
        this.city.setText(currentWeatherViewModel.getCityName());
        this.temperature.setText(currentWeatherViewModel.getTemperature());
        this.wind.setText(currentWeatherViewModel.getWindSpeed());
        this.pressure.setText(currentWeatherViewModel.getPressure());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(PLACE, placeViewModel);
    }
}
