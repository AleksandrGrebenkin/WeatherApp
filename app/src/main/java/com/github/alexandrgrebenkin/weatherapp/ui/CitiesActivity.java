package com.github.alexandrgrebenkin.weatherapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.alexandrgrebenkin.weatherapp.data.CurrentWeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.data.RandomWeatherProvider;
import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.data.WeatherProvider;

public class CitiesActivity extends AppCompatActivity {

    private EditText cityName;

    private Button cityFind;

    private ListView cities;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        initialize();
        startCityFindClickListener();
        startFavoriteCityListListener();
    }

    private void initialize() {
        cityName = findViewById(R.id.action_cities__et_city_name);
        cityFind = findViewById(R.id.action_cities_btn_city_find);
        cities = findViewById(R.id.activity_cities__lv_cities);
    }

    private void startCityFindClickListener() {
        cityFind.setOnClickListener((v) -> {
            String city = cityName.getText().toString();
            pushCityInfoIntentResult(city);
        });
    }

    private void pushCityInfoIntentResult(String city) {
        WeatherProvider weatherProvider = new RandomWeatherProvider();
        CurrentWeatherInfo currentWeatherInfo = weatherProvider.getCurrentForecast(city);
        Intent intentResult = new Intent();
        intentResult.putExtra(MainActivity.WEATHER_INFO, currentWeatherInfo);
        setResult(RESULT_OK, intentResult);
        finish();
    }

    private void startFavoriteCityListListener() {
        cities.setOnItemClickListener((parent, view, position, id) -> {
            String city = ((TextView) view).getText().toString();
            pushCityInfoIntentResult(city);
        });
    }
}
