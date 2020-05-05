package com.github.alexandrgrebenkin.weatherapp.Interface;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.alexandrgrebenkin.weatherapp.Data.WeatherInfo;
import com.github.alexandrgrebenkin.weatherapp.Data.WeatherInfoProvider;
import com.github.alexandrgrebenkin.weatherapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CitiesListFragment extends Fragment {

    private EditText cityName;
    private Button cityFind;
    private ListView cities;

    private Listener listener;

    interface Listener {
        void pushWeatherInfo(WeatherInfo weatherInfo);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (Listener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cities_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
        startCityFindClickListener();
        startFavoriteCityListListener();
    }

    private void initialize() {
        View view = getView();
        cityName = view.findViewById(R.id.fragment_cities_list__et_city_name);
        cityFind = view.findViewById(R.id.fragment_cities_list_btn_city_find);
        cities = view.findViewById(R.id.fragment_cities_list__lv_cities);
    }

    private void startCityFindClickListener() {
        cityFind.setOnClickListener((v) -> {
            String city = cityName.getText().toString();
            pushCityInfoIntentResult(city);
        });
    }

    private void pushCityInfoIntentResult(String city) {
        if (listener != null) {
            WeatherInfoProvider weatherInfoProvider = new WeatherInfoProvider();
            WeatherInfo weatherInfo = weatherInfoProvider.getWeatherInfo(city);
            listener.pushWeatherInfo(weatherInfo);
        }

    }

    private void startFavoriteCityListListener() {
        cities.setOnItemClickListener((parent, view, position, id) -> {
            String city = ((TextView) view).getText().toString();
            pushCityInfoIntentResult(city);
        });

    }
}
