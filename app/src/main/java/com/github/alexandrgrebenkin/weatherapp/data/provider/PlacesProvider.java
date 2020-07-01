package com.github.alexandrgrebenkin.weatherapp.data.provider;

import com.github.alexandrgrebenkin.weatherapp.data.entity.Place;

import java.util.List;

public interface PlacesProvider {
    List<Place> getPlaces(String cityName);
}
