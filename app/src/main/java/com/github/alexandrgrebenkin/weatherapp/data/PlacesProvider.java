package com.github.alexandrgrebenkin.weatherapp.data;

import java.util.List;

public interface PlacesProvider {
    List<Place> getPlaces(String cityName);
}
