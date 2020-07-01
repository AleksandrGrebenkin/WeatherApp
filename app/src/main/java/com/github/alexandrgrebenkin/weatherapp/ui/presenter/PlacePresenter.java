package com.github.alexandrgrebenkin.weatherapp.ui.presenter;

import com.github.alexandrgrebenkin.weatherapp.data.entity.Place;
import com.github.alexandrgrebenkin.weatherapp.data.manager.PlaceDataManager;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.PlaceViewModel;

import java.util.ArrayList;
import java.util.List;

public class PlacePresenter {
    private PlaceDataManager placeDataManager;

    public PlacePresenter() {
        this.placeDataManager = new PlaceDataManager();
    }

    public List<PlaceViewModel> getPlaces(String placeName) {
        List<PlaceViewModel> placeViewModelList = new ArrayList<>();
        List<Place> places = placeDataManager.getPlaces(placeName);
        for (int i = 0; i < places.size(); i++) {
            Place place = places.get(i);
            PlaceViewModel placeViewModel = new PlaceViewModel();

            placeViewModel.setName(place.getName());
            placeViewModel.setDisplayName(place.getDisplayName());
            placeViewModel.setLat(place.getLat());
            placeViewModel.setLon(place.getLon());

            placeViewModelList.add(placeViewModel);
        }
        return placeViewModelList;
    }
}
