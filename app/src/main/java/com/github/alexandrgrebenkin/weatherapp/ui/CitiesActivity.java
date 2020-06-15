package com.github.alexandrgrebenkin.weatherapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.alexandrgrebenkin.weatherapp.data.PlaceInfo;
import com.github.alexandrgrebenkin.weatherapp.data.providers.implementation.InternetPlaceProvider;
import com.github.alexandrgrebenkin.weatherapp.data.Place;
import com.github.alexandrgrebenkin.weatherapp.data.providers.PlacesProvider;
import com.github.alexandrgrebenkin.weatherapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.List;


public class CitiesActivity extends BaseActivity {
    private static final String PLACE_LIST = "com.github.alexandrgrebenkin.weatherapp.PLACE_LIST";

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
            PlaceInfo placeInfo = new PlaceInfo(place.getDisplayName().split(",")[0],
                    place.getLat(), place.getLon());
            pushCityInfoIntentResult(placeInfo);
        });
    }

    private void pushCityInfoIntentResult(PlaceInfo placeInfo) {
        if (hasErrors) {
            return;
        }
        Intent intentResult = new Intent();
        intentResult.putExtra(MainActivity.PLACE_INFO, placeInfo);
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

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Place[] placeArray = (Place[]) savedInstanceState.getParcelableArray(PLACE_LIST);
        if (placeArray != null) {
            refreshList(Arrays.asList(placeArray));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (placeList != null) {
            Place[] placeArray = new Place[placeList.size()];
            placeList.toArray(placeArray);
            outState.putParcelableArray(PLACE_LIST, placeArray);
        }
    }
}
