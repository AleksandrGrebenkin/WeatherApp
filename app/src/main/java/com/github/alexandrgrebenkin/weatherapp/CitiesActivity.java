package com.github.alexandrgrebenkin.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CitiesActivity extends Activity implements Constants{

    private EditText cityName;

    private Button cityFind;

    private TextView moscow;
    private TextView berlin;
    private TextView paris;


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
        moscow = findViewById(R.id.activity_cities__tv_moscow);
        berlin = findViewById(R.id.activity_cities__tv_berlin);
        paris = findViewById(R.id.activity_cities__tv_paris);
    }

    private void startCityFindClickListener() {
        cityFind.setOnClickListener((v) -> {
            pushCityInfoIntentResult(cityName);
        });
    }

    private void pushCityInfoIntentResult(TextView textView) {
        Intent intentResult = new Intent();
        String cityNameString = textView.getText().toString();
        intentResult.putExtra(CITY, cityNameString);
        setResult(RESULT_OK, intentResult);
        finish();
    }

    /*
    * Временное решение для обработки избранных городов.
    * */
    private void startFavoriteCityListListener() {
        moscow.setOnClickListener((v) -> {
            pushCityInfoIntentResult(moscow);
        });
        berlin.setOnClickListener((v) -> {
            pushCityInfoIntentResult(berlin);
        });
        paris.setOnClickListener((v) -> {
            pushCityInfoIntentResult(paris);
        });
    }
}
