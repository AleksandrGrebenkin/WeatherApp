package com.github.alexandrgrebenkin.weatherapp.data.providers.implementation;

import android.os.LocaleList;
import android.util.Log;
import android.widget.Toast;

import com.github.alexandrgrebenkin.weatherapp.BuildConfig;
import com.github.alexandrgrebenkin.weatherapp.data.Place;
import com.github.alexandrgrebenkin.weatherapp.data.Utils;
import com.github.alexandrgrebenkin.weatherapp.data.providers.PlacesProvider;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

public class InternetPlaceProvider implements PlacesProvider {
    private static final String TAG = "PLACES";
    private static final String PLACES_URL =
            "https://eu1.locationiq.com/v1/search.php?key={TOKEN}&q={PLACE}&limit=10&format=json&accept-language={LOCALE}"
                    .replace("{TOKEN}", BuildConfig.LOCATION_TOKEN);

    @Override
    public List<Place> getPlaces(String cityName) {
        List<Place> places = new ArrayList<>();
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
                String result = Utils.getLines(in);
                Gson gson = new Gson();
                final Place[] objects = gson.fromJson(result, Place[].class);
                for (Place place : objects) {
                    if (place.getPlaceClass().equals("place") && place.getOsmType().equals("relation")) {
                        places.add(place);
                    }
                }
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
        return places;
    }
}
