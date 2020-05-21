package com.github.alexandrgrebenkin.weatherapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.alexandrgrebenkin.weatherapp.data.CurrentWeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.data.InternetPlaceProvider;
import com.github.alexandrgrebenkin.weatherapp.data.Place;
import com.github.alexandrgrebenkin.weatherapp.data.PlacesProvider;
import com.github.alexandrgrebenkin.weatherapp.data.RandomWeatherProvider;
import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.data.WeatherProvider;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;


public class CitiesActivity extends BaseActivity {

    private TextInputEditText cityName;
    private TextInputLayout cityNameLayout;
    private MaterialButton cityFind;
    private RecyclerView cities;
    private TextView emptyData;

    private List<Place> placeList;

    private boolean hasErrors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        initialize();
        checkPlaceData();
        startCityFindClickListener();
        //startFavoriteCityListListener();
        Snackbar.make(cityFind, R.string.cities_info_message, Snackbar.LENGTH_LONG).show();
    }

    private void initialize() {
        cityName = findViewById(R.id.action_cities__tiet_city_name);
        cityNameLayout = findViewById(R.id.action_cities__til_city_name);
        cityFind = findViewById(R.id.action_cities_btn_city_find);
        cities = findViewById(R.id.activity_cities__rv_cities);
        emptyData = findViewById(R.id.activity_cities__tv_empty_data);
    }

    private void checkEmpty(TextView et, TextInputLayout textInputLayout) {
        if (et.getText().toString().length() < 2) {
            String error = getResources().getString(R.string.less_2_symbols_length_error);
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
            if (hasErrors) {
                return;
            }
            final Handler handler = new Handler();
            new Thread(() -> {
                PlacesProvider provider = new InternetPlaceProvider();
                List<Place> places = provider.getPlaces(cityName.getText().toString());
                handler.post(() -> {
                    refreshList(places);
                });
            }).start();
        });
    }

    private void refreshList(List<Place> places) {
        placeList = places;
        checkPlaceData();
        PlaceAdapter placeAdapter = new PlaceAdapter(placeList);
        cities.setAdapter(placeAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        cities.setLayoutManager(layoutManager);

        placeAdapter.setOnItemClickListener((view, position) -> {
            Place place = places.get(position);
            Toast.makeText(getApplicationContext(),
                    String.format("lat: %s; lon: %s", place.getLat(), place.getLon()),
                    Toast.LENGTH_SHORT).show();
        });
//        placeArrayAdapter.notifyDataSetChanged();
//        cities.invalidateViews();
//        cities.refreshDrawableState();
//        ArrayList<String> strings = new ArrayList<>();
//        strings.add("String1");
//        strings.add("String2");
//        strings.add("String3");
//        ArrayAdapter<String> stringAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
//                strings);
//        cities.setAdapter(stringAdapter);
    }

    private void pushCityInfoIntentResult(String city) {
        if (hasErrors) {
            return;
        }
        WeatherProvider weatherProvider = new RandomWeatherProvider();
        CurrentWeatherInfo currentWeatherInfo = weatherProvider.getCurrentWeatherInfo(city);
        Intent intentResult = new Intent();
        intentResult.putExtra(MainActivity.WEATHER_INFO, currentWeatherInfo);
        setResult(RESULT_OK, intentResult);
        finish();
    }

    private void checkPlaceData() {
        if (placeList == null || placeList.isEmpty()) {
            cities.setVisibility(View.GONE);
            emptyData.setVisibility(View.VISIBLE);
        } else {
            cities.setVisibility(View.VISIBLE);
            emptyData.setVisibility(View.GONE);
        }
    }
//    private void startFavoriteCityListListener() {
//        cities.setOnItemClickListener((parent, view, position, id) -> {
//            hasErrors = false;
//            String city = ((TextView) view).getText().toString();
//            pushCityInfoIntentResult(city);
//        });
//    }
}
