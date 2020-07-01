package com.github.alexandrgrebenkin.weatherapp.data.implementation.webservice.place;

import android.util.Log;

import com.github.alexandrgrebenkin.weatherapp.BuildConfig;
import com.github.alexandrgrebenkin.weatherapp.data.entity.Place;
import com.github.alexandrgrebenkin.weatherapp.data.provider.PlacesProvider;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class InternetPlaceProvider implements PlacesProvider {
    private static final String TAG = "PLACES";
    private static final String PLACES_URL =
            "https://eu1.locationiq.com/v1/search.php?key={TOKEN}&q={PLACE}&limit=10&format=json&accept-language={LOCALE}"
                    .replace("{TOKEN}", BuildConfig.LOCATION_TOKEN);

    @Override
    public List<Place> getPlaces(String cityName) {
        List<Place> places = new ArrayList<>();
        for (PlaceRequest placeRequest : getPlaceRequestArray(cityName)) {
            if (placeRequest.getPlaceClass().equals("place")
                    && placeRequest.getOsmType().equals("relation")) {
                Place place = new Place();
                place.setName(placeRequest.getDisplayName().split(",")[0]);
                place.setDisplayName(placeRequest.getDisplayName());
                place.setLat(placeRequest.getLat());
                place.setLon(placeRequest.getLon());
                places.add(place);
            }
        }
        return places;
    }

    private PlaceRequest[] getPlaceRequestArray(String cityName) {
        PlaceRequest[] placeRequests = null;
        try {
            String placesUrl = PLACES_URL
                    .replace("{PLACE}", cityName)
                    .replace("{LOCALE}", Locale.getDefault().getLanguage());
            final URL uri = new URL(placesUrl);
            HttpsURLConnection urlConnection = null;
            try {
                urlConnection = (HttpsURLConnection) uri.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10_000);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream()));
                String result = getLines(in);
                Gson gson = new Gson();
                placeRequests = gson.fromJson(result, PlaceRequest[].class);
            } catch (IOException e) {
                Log.e(TAG, "Failed connection:" + e.getMessage());
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "Incorrect URL");
            e.printStackTrace();
        }
        return placeRequests;
    }

    private String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }
}
