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
import com.github.alexandrgrebenkin.weatherapp.data.InternetWeatherProvider;
import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.data.WeatherProvider;

public class MainActivity extends BaseActivity {

    private final static int REQUEST_CITY_CODE_ACTIVITY = 1;
    private final static int SETTING_CODE = 101;

    final static String WEATHER_INFO = "com.github.alexandrgrebenkin.weatherapp.WEATHER_INFO";

    private TextView city;
    private TextView temperature;
    private TextView wind;
    private TextView pressure;

    private ImageButton citiesList;
    private ImageButton settings;

    private RecyclerView dayOfWeekRecyclerView;

    private CurrentWeatherInfo currentWeatherInfo
            = new CurrentWeatherInfo("", "", "", "");
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
//        setupDayOfWeekRecyclerView();
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

    private void setupDayOfWeekRecyclerView() {
        WeekInfo weekInfo = new WeekInfo(weatherProvider.getForecastWeatherInfo(city.getText().toString()));
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
            updateViewData(data.getParcelableExtra(WEATHER_INFO));
        }
        if (requestCode == SETTING_CODE) {
            recreate();
        }
    }

    private void updateWeather() {
        Handler handler = new Handler();
        new Thread(() -> {
            CurrentWeatherInfo currentWeatherInfo = weatherProvider.getCurrentWeatherInfo("Example City");
            handler.post(() -> {
                updateViewData(currentWeatherInfo);
            });
        }).start();

    }

    private void updateViewData(CurrentWeatherInfo currentWeatherInfo) {
        this.currentWeatherInfo = currentWeatherInfo;
        city.setText(currentWeatherInfo.getCityName());
        temperature.setText(currentWeatherInfo.getTemperature());
        wind.setText(currentWeatherInfo.getWind());
        pressure.setText(currentWeatherInfo.getPressure());
        //setupDayOfWeekRecyclerView();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        updateViewData(savedInstanceState.getParcelable(WEATHER_INFO));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(WEATHER_INFO, currentWeatherInfo);
    }
}
