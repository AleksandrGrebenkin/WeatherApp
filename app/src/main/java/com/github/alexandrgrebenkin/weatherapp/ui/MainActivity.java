package com.github.alexandrgrebenkin.weatherapp.ui;

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

import com.github.alexandrgrebenkin.weatherapp.data.CurrentWeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.data.DayWeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.data.PlaceInfo;
import com.github.alexandrgrebenkin.weatherapp.data.providers.implementation.InternetWeatherProvider;
import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.data.providers.WeatherProvider;

import java.util.List;

public class MainActivity extends BaseActivity {

    private final static int REQUEST_CITY_CODE_ACTIVITY = 1;
    private final static int SETTING_CODE = 101;

    final static String PLACE_INFO = "com.github.alexandrgrebenkin.weatherapp.PLACE_INFO";

    private TextView city;
    private TextView temperature;
    private TextView wind;
    private TextView pressure;

    private ImageButton citiesList;
    private ImageButton settings;

    private RecyclerView dayOfWeekRecyclerView;

    private WeatherProvider weatherProvider;
    private PlaceInfo placeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        updateWeather(null);
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
        weatherProvider = new InternetWeatherProvider();
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

    private void updateWeekForecast(){
        Handler handler = new Handler();
        new Thread(() -> {
            List<DayWeatherInfo> dayWeatherInfoList = weatherProvider.getForecastWeatherInfo(placeInfo);
            handler.post(() -> {
                setupDayOfWeekRecyclerView(dayWeatherInfoList);
            });
        }).start();
    }

    private void setupDayOfWeekRecyclerView(List<DayWeatherInfo> dayWeatherInfoList) {
        WeekInfo weekInfo = new WeekInfo(dayWeatherInfoList);
        dayOfWeekRecyclerView.setAdapter(weekInfo);

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
            PlaceInfo placeInfo = data.getParcelableExtra(PLACE_INFO);
            updateWeather(placeInfo);
        }
        if (requestCode == SETTING_CODE) {
            recreate();
        }
    }

    private void updateWeather(PlaceInfo placeInfo) {
        if (placeInfo == null) {
            placeInfo = new PlaceInfo("Moscow", "55.7504461", "37.6174943");
        }
        this.placeInfo = placeInfo;
        Handler handler = new Handler();
        PlaceInfo finalPlaceInfo = placeInfo;
        new Thread(() -> {
            CurrentWeatherInfo currentWeatherInfo = weatherProvider.getCurrentWeatherInfo(finalPlaceInfo);
            handler.post(() -> {
                updateViewData(currentWeatherInfo);
            });
        }).start();

    }

    private void updateViewData(CurrentWeatherInfo currentWeatherInfo) {
        this.city.setText(currentWeatherInfo.getCityName());
        String temp = currentWeatherInfo.getTempCelsius() + getString(R.string.celsius);
        this.temperature.setText(temp);
        String windSpeed =  currentWeatherInfo.getWindSpeedMS() + " " + getString(R.string.meter_per_second);
        this.wind.setText(windSpeed);
        String pressure = currentWeatherInfo.getPressureMm() + " " + getString(R.string.mm_of_mercury);
        this.pressure.setText(pressure);
        updateWeekForecast();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            updateWeather(savedInstanceState.getParcelable(PLACE_INFO));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(PLACE_INFO, placeInfo);
    }
}
