package com.github.alexandrgrebenkin.weatherapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.alexandrgrebenkin.weatherapp.ui.presenter.PlacePresenter;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.PlaceViewModel;
import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.ui.adapter.PlaceAdapter;
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

    private List<PlaceViewModel> placeViewModelList;

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
            Toast.makeText(getApplicationContext(), "I'm Clicked", Toast.LENGTH_SHORT).show();
            Handler handler = new Handler();
            new Thread(() -> {
                PlacePresenter placePresenter = new PlacePresenter();
                String placeName = cityName.getText().toString();
                List<PlaceViewModel> placeViewModelList = placePresenter.getPlaces(placeName);
                handler.post(() -> {
                    refreshPlaceList(placeViewModelList);
                });
            }).start();
        });
    }

    private void refreshPlaceList(List<PlaceViewModel> placeViewModelList) {
        this.placeViewModelList = placeViewModelList;
        checkPlaceData();
        PlaceAdapter placeAdapter = new PlaceAdapter(this.placeViewModelList);
        cities.setAdapter(placeAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        cities.setLayoutManager(layoutManager);

        placeAdapter.setOnItemClickListener((view, position) -> {
            pushCityInfoIntentResult(placeViewModelList.get(position));
        });
    }

    private void pushCityInfoIntentResult(PlaceViewModel placeViewModel) {
        if (hasErrors) {
            return;
        }
        Intent intentResult = new Intent();
        intentResult.putExtra(MainActivity.PLACE, placeViewModel);
        setResult(RESULT_OK, intentResult);
        finish();
    }

    private void checkPlaceData() {
        if (placeViewModelList == null || placeViewModelList.isEmpty()) {
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
        PlaceViewModel[] placeArray = (PlaceViewModel[]) savedInstanceState.getParcelableArray(PLACE_LIST);
        if (placeArray != null) {
            refreshPlaceList(Arrays.asList(placeArray));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (placeViewModelList != null) {
            PlaceViewModel[] placeViewModelArray = new PlaceViewModel[placeViewModelList.size()];
            placeViewModelList.toArray(placeViewModelArray);
            outState.putParcelableArray(PLACE_LIST, placeViewModelArray);
        }
    }
}
