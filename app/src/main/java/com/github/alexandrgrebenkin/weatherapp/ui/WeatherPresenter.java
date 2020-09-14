package com.github.alexandrgrebenkin.weatherapp.ui;

import android.location.Address;
import android.location.Geocoder;

import com.github.alexandrgrebenkin.weatherapp.ui.activity.NavigationActivity;

import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WeatherPresenter {

    private NavigationActivity view;
    private final WeatherModel model;

    public WeatherPresenter(WeatherModel model) {
        this.model = model;
    }

    public void attachView(NavigationActivity navigationActivity) {
        view = navigationActivity;
    }

    public void detachView() {
        view = null;
    }

    public void viewIsReady() {
        loadWeather();
    }

    public void loadWeather() {
        loadAddress().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(address -> {
                    if (address == null || address.getLocality() == null) {
                        view.showObjectNotFoundDialog();
                    } else {
                        loadWeather(address);
                    }
                }, throwable -> {
                    view.showUnknownErrorDialog(throwable.getLocalizedMessage());
                });
    }

    private Observable<Address> loadAddress() {
        return Observable.create(emitter -> {
            try {
                Geocoder geocoder = new Geocoder(view, Locale.getDefault());
                Address address = geocoder.getFromLocationName(view.getCityNameQuery(), 1).get(0);
                emitter.onNext(address);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }

    private void loadWeather(Address address) {
        model.loadWeather(address, weatherInfo -> {
            WeatherConverter weatherConverter = new WeatherConverter(view);
            view.updateWeather(weatherConverter.getWeatherViewModel(weatherInfo));
        });
    }


}
