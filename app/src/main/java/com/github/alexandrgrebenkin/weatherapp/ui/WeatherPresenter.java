package com.github.alexandrgrebenkin.weatherapp.ui;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import com.github.alexandrgrebenkin.weatherapp.data.database.model.HistoryInfo;
import com.github.alexandrgrebenkin.weatherapp.data.entity.CurrentWeather;
import com.github.alexandrgrebenkin.weatherapp.ui.activity.NavigationActivity;

import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.HistoryInfoViewModel;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.WeatherViewModel;

import java.util.List;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
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
                }, throwable -> view.showUnknownErrorDialog(throwable.getLocalizedMessage()));
    }

    public void loadWeatherFromLocation() {
        loadAddressFromLocation().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(address -> {
                    if (address == null || address.getLocality() == null) {
                        view.showObjectNotFoundDialog();
                    } else {
                        loadWeather(address);
                    }
                }, throwable -> view.showUnknownErrorDialog(throwable.getLocalizedMessage()));
    }

    Single<Address> loadAddressFromLocation() {
        return Single.create(emitter -> {
            try {
                Geocoder geocoder = new Geocoder(view, getCurrentLocale());
                Location location = view.getLocation();
                Address address = geocoder.getFromLocation(
                        location.getLatitude(), location.getLongitude(), 1).get(0);
                emitter.onSuccess(address);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }

    private Single<Address> loadAddress() {
        return Single.create(emitter -> {
            try {
                Geocoder geocoder = new Geocoder(view, getCurrentLocale());
                Address address = geocoder.getFromLocationName(view.getCityNameQuery(), 1).get(0);
                emitter.onSuccess(address);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }

    private void loadWeather(Address address) {
        model.loadWeather(address, weatherInfo -> {
            WeatherConverter weatherConverter = new WeatherConverter(view);
            WeatherViewModel weatherViewModel = weatherConverter.getWeatherViewModel(weatherInfo);
            writeHistoryInfoIntoDB(weatherInfo.getCurrentWeather())
                    .subscribeOn(Schedulers.io())
                    .subscribe(() -> Log.d("HistoryInfo", "Added successful"),
                            throwable -> view.showUnknownErrorDialog(throwable.getLocalizedMessage()));
            view.showWeather(weatherViewModel);
        });
    }

    public void loadHistory() {
        loadHistoryInfoFromDB().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(historyInfos -> {
                    WeatherConverter weatherConverter = new WeatherConverter(view);
                    List<HistoryInfoViewModel> historyInfoViewModelList =
                            weatherConverter.getHistoryInfoViewModelList(historyInfos);
                    view.showHistory(historyInfoViewModelList);
                }, throwable -> view.showUnknownErrorDialog(throwable.getLocalizedMessage()));
    }

    private Single<List<HistoryInfo>> loadHistoryInfoFromDB() {
        return Single.create(emitter -> {
            try {
                emitter.onSuccess(model.loadHistoryInfoFromDB());
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }

    private Completable writeHistoryInfoIntoDB(CurrentWeather currentWeather) {
        return Completable.create(emitter -> {
            try {
                model.writeHistoryInfoIntoDB(currentWeather);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }

    private Locale getCurrentLocale() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return view.getResources().getConfiguration().getLocales().get(0);
        } else {
            return view.getResources().getConfiguration().locale;
        }
    }
}
