package com.github.alexandrgrebenkin.weatherapp.data.manager;

import com.github.alexandrgrebenkin.weatherapp.data.entity.Place;
import com.github.alexandrgrebenkin.weatherapp.data.implementation.webservice.place.InternetPlaceProvider;
import com.github.alexandrgrebenkin.weatherapp.data.provider.PlacesProvider;

import java.util.List;

public class PlaceDataManager {
    private PlacesProvider placesProvider = new InternetPlaceProvider();

    public List<Place> getPlaces(String placeName){
        return placesProvider.getPlaces(placeName);
    }
}
