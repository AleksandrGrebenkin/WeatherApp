package com.github.alexandrgrebenkin.weatherapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.github.alexandrgrebenkin.weatherapp.data.CurrentWeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.data.RandomWeatherProvider;
import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.data.WeatherProvider;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class CitiesActivity extends BaseActivity {

    private TextInputEditText cityName;
    private TextInputLayout cityNameLayout;

    private MaterialButton cityFind;

    private ListView cities;

    private boolean hasErrors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        initialize();
        startCityFindClickListener();
        startFavoriteCityListListener();
        Snackbar.make(cityFind, "Введите название города или выберите из списка", Snackbar.LENGTH_LONG).show();
    }

    private void initialize() {
        cityName = findViewById(R.id.action_cities__tiet_city_name);
        cityNameLayout = findViewById(R.id.action_cities__til_city_name);
        cityFind = findViewById(R.id.action_cities_btn_city_find);
        cities = findViewById(R.id.activity_cities__lv_cities);
    }

    private void checkEmpty(TextView et, TextInputLayout textInputLayout) {
        if (et.getText().toString().isEmpty()) {
            String error = getResources().getString(R.string.empty_field_error);
            hasErrors = true;
            textInputLayout.setError(error);
        } else {
            hasErrors = false;
            textInputLayout.setError(null);
        }
    }

    private void startCityFindClickListener() {
        cityFind.setOnClickListener((v) -> {
            checkEmpty(cityName, cityNameLayout);
            String city = cityName.getText().toString();
            pushCityInfoIntentResult(city);
        });
    }

    private void pushCityInfoIntentResult(String city) {
        if (hasErrors) {
            return;
        }
        WeatherProvider weatherProvider = new RandomWeatherProvider();
        CurrentWeatherInfo currentWeatherInfo = weatherProvider.getCurrentForecast(city);
        Intent intentResult = new Intent();
        intentResult.putExtra(MainActivity.WEATHER_INFO, currentWeatherInfo);
        setResult(RESULT_OK, intentResult);
        finish();
    }

    private void startFavoriteCityListListener() {
        cities.setOnItemClickListener((parent, view, position, id) -> {
            hasErrors = false;
            String city = ((TextView) view).getText().toString();
            pushCityInfoIntentResult(city);
        });
    }
}
