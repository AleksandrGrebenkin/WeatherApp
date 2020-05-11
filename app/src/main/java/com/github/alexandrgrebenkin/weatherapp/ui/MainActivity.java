package com.github.alexandrgrebenkin.weatherapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.alexandrgrebenkin.weatherapp.data.CurrentWeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.data.RandomWeatherProvider;
import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.data.WeatherProvider;

public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_CITY_CODE_ACTIVITY = 1;
    final static String WEATHER_INFO = "com.github.alexandrgrebenkin.weatherapp.WEATHER_INFO";

    private TextView city;
    private TextView temperature;
    private TextView wind;
    private TextView pressure;

    private ImageButton citiesList;
    private ImageButton settings;

    private RecyclerView dayOfWeekRecyclerView;

    private CurrentWeatherInfo currentWeatherInfo;
    private WeatherProvider weatherProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        updateWeather();
        startCitiesClickListener();
        startSettingsClickListener();
        startCityClickListener();
        setupDayOfWeekRecyclerView();
    }

    private void initialize() {
        city = findViewById(R.id.activity_main__tv_city);
        temperature = findViewById(R.id.activity_main__tv_temperature);
        wind = findViewById(R.id.activity_main__tv_wind);
        pressure = findViewById(R.id.activity_main__tv_pressure);
        citiesList = findViewById(R.id.activity_main__ib_citiesList);
        settings = findViewById(R.id.activity_main__ib_settings);
        dayOfWeekRecyclerView = findViewById(R.id.activity_main__rv_day_info);
        weatherProvider = new RandomWeatherProvider();
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
            startActivity(intent);
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

    private void setupDayOfWeekRecyclerView() {
        WeekInfo weekInfo = new WeekInfo(weatherProvider.getWeekForecast(city.getText().toString()));
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
        if (requestCode == REQUEST_CITY_CODE_ACTIVITY && resultCode == RESULT_OK) {
            currentWeatherInfo = data.getParcelableExtra(WEATHER_INFO);
            updateViewData();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateWeather() {
        currentWeatherInfo = weatherProvider.getCurrentForecast("Example City");
        updateViewData();
    }

    private void updateViewData() {
        city.setText(currentWeatherInfo.getCityName());
        temperature.setText(currentWeatherInfo.getTemperature());
        wind.setText(currentWeatherInfo.getWind());
        pressure.setText(currentWeatherInfo.getPressure());
        setupDayOfWeekRecyclerView();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentWeatherInfo = savedInstanceState.getParcelable(WEATHER_INFO);
        updateViewData();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(WEATHER_INFO, currentWeatherInfo);
    }
}
