package com.github.alexandrgrebenkin.weatherapp.data.providers;

import com.github.alexandrgrebenkin.weatherapp.data.Place;

import java.util.List;

public interface PlacesProvider {
    List<Place> getPlaces(String cityName);
}
