package com.github.alexandrgrebenkin.weatherapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.ui.adapter.PlaceAdapter;
import com.github.alexandrgrebenkin.weatherapp.ui.presenter.PlacePresenter;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.PlaceViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.List;

public class CitiesFragment extends Fragment {

    private static final String PLACE_LIST = "com.github.alexandrgrebenkin.weatherapp.PLACE_LIST";

    private TextInputEditText cityName;
    private TextInputLayout cityNameLayout;
    private MaterialButton cityFind;
    private RecyclerView cities;
    private TextView emptyData;

    private List<PlaceViewModel> placeViewModelList;

    private boolean hasErrors;

    private Listener listener;

    public interface Listener {
        void itemClicked(PlaceViewModel placeViewModel);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cities, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        initialize();
        checkPlaceData();
        startCityFindClickListener();
        Snackbar.make(cityFind, R.string.cities_info_message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (Listener) context;
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            PlaceViewModel[] placeArray = (PlaceViewModel[]) savedInstanceState.getParcelableArray(PLACE_LIST);
            if (placeArray != null) {
                refreshPlaceList(Arrays.asList(placeArray));
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (placeViewModelList != null) {
            PlaceViewModel[] placeViewModelArray = new PlaceViewModel[placeViewModelList.size()];
            placeViewModelList.toArray(placeViewModelArray);
            outState.putParcelableArray(PLACE_LIST, placeViewModelArray);
        }
    }

    private void initialize() {
        View view = getView();
        if (view != null) {
            cityName = view.findViewById(R.id.fragment_cities__tiet_city_name);
            cityNameLayout = view.findViewById(R.id.fragment_cities__til_city_name);
            cityFind = view.findViewById(R.id.fragment_cities_btn_city_find);
            cities = view.findViewById(R.id.fragment_cities__rv_cities);
            emptyData = view.findViewById(R.id.fragment_cities__tv_empty_data);
        }
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
            Handler handler = new Handler();
            new Thread(() -> {
                PlacePresenter placePresenter = new PlacePresenter();
                String placeName = cityName.getText().toString();
                List<PlaceViewModel> placeViewModelList = placePresenter.getPlaces(placeName);
                handler.post(() -> {
                    refreshPlaceList(placeViewModelList);
                });
            }).start();
            InputMethodManager imm = (InputMethodManager) getActivity()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(cityFind.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        });
    }

    private void refreshPlaceList(List<PlaceViewModel> placeViewModelList) {
        this.placeViewModelList = placeViewModelList;
        checkPlaceData();
        PlaceAdapter placeAdapter = new PlaceAdapter(this.placeViewModelList);
        cities.setAdapter(placeAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        cities.setLayoutManager(layoutManager);

        placeAdapter.setOnItemClickListener((view, position) -> {
            if (hasErrors) {
                return;
            }
            cityName.onEditorAction(EditorInfo.IME_ACTION_DONE);
            listener.itemClicked(placeViewModelList.get(position));
        });
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


}