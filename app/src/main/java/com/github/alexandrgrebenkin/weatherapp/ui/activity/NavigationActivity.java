package com.github.alexandrgrebenkin.weatherapp.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.alexandrgrebenkin.weatherapp.ui.WeatherModel;
import com.github.alexandrgrebenkin.weatherapp.ui.WeatherPresenter;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.HistoryFragment;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.ObjectNotFoundDialogFragment;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.UnknownErrorDialogFragment;
import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.AboutDevFragment;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.FeedbackFragment;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.HomeFragment;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.SettingsFragment;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.HistoryInfoViewModel;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.WeatherViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class NavigationActivity extends BaseActivity
        implements HomeFragment.OnMenuItemSelectedListener,
        SettingsFragment.Listener,
        NavigationView.OnNavigationItemSelectedListener {

    public static final String CITY_NAME_QUERY = "com.github.alexandrgrebenkin.weatherapp.CITY_NAME_QUERY";

    private final String CITY_NAME = "CITY_NAME";

    private DrawerLayout drawer;

    private WeatherPresenter presenter;

    private String cityNameQuery;
    private Location currentLocation;
    private LocListener locationListener;
    private LocationManager locManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = initToolbar();
        initDrawer(toolbar);
        initNavigationListener();

        locManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (savedInstanceState == null) {
            cityNameQuery = loadCityNameFromPref();
        } else {
            cityNameQuery = savedInstanceState.getString(CITY_NAME_QUERY);
        }
        setWeatherPresenter();
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        super.onResume();
        if (locationListener == null) {
            locationListener = new LocListener();
        }
    }

    @Override
    protected void onPause() {
        if (locationListener != null) {
            locManager.removeUpdates(locationListener);
        }

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void initDrawer(Toolbar toolbar) {
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private Toolbar initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    private void setWeatherPresenter() {
        WeatherModel weatherModel = new WeatherModel(this);
        presenter = new WeatherPresenter(weatherModel);
        presenter.attachView(this);
        if (cityNameQuery == null) {
            loadWeatherFromCurrentLocation();
        } else {
            presenter.viewIsReady();
        }
    }

    private void initNavigationListener() {
        NavigationView navigationView = findViewById(R.id.activity_navigation__nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void showWeather(WeatherViewModel weatherViewModel) {
        getSupportActionBar().setTitle(weatherViewModel.getCurrentWeatherViewModel().getCityName());
        HomeFragment homeFragment = HomeFragment.newInstance(weatherViewModel);
        setFragment(homeFragment);
        saveCityNameToPref(weatherViewModel.getCurrentWeatherViewModel().getCityName());

    }

    private void saveCityNameToPref(String cityName) {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        sharedPreferences.edit()
                .putString(CITY_NAME, cityName)
                .commit();
    }

    private String loadCityNameFromPref() {
        return getPreferences(MODE_PRIVATE).getString(CITY_NAME, null);
    }

    public void showHistory(List<HistoryInfoViewModel> historyInfoViewModelList) {
        getSupportActionBar().setTitle(getResources().getString(R.string.history));
        HistoryFragment historyFragment = HistoryFragment
                .newInstance((ArrayList<HistoryInfoViewModel>) historyInfoViewModelList);
        setFragment(historyFragment);
    }

    private void setSettingsFragment() {
        SettingsFragment settingsFragment = SettingsFragment.newInstance(isDarkTheme());
        setFragment(settingsFragment);
        getSupportActionBar().setTitle(R.string.settings);
    }

    private void setFeedbackFragment() {
        setFragment(new FeedbackFragment());
        getSupportActionBar().setTitle(R.string.feedback);
    }

    private void setAboutDevFragment() {
        setFragment(new AboutDevFragment());
        getSupportActionBar().setTitle(R.string.about_developer);
    }

    private void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_navigation__fl_content_frame, fragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.menu_nav__i_history:
                presenter.loadHistory();
                break;
            case R.id.menu_nav__i_settings:
                setSettingsFragment();
                break;
            case R.id.menu_nav__i_feedback:
                setFeedbackFragment();
                break;
            case R.id.menu_nav__i_about:
                setAboutDevFragment();
                break;
            default:
                presenter.loadWeather();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CITY_NAME_QUERY, cityNameQuery);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void settingsChanged(boolean isDarkTheme) {
        setDarkTheme(isDarkTheme);
        recreate();
    }

    public void showObjectNotFoundDialog() {
        ObjectNotFoundDialogFragment objectNotFound = new ObjectNotFoundDialogFragment();
        objectNotFound.show(getSupportFragmentManager(), "objectNotFoundDialog");
    }

    public void showUnknownErrorDialog(String msg) {
        UnknownErrorDialogFragment unknownError = UnknownErrorDialogFragment
                .newInstance(msg);
        unknownError.show(getSupportFragmentManager(), "unknownErrorDialog");
    }

    public String getCityNameQuery() {
        return cityNameQuery;
    }

    @Override
    public void onSearchItemSelected(String cityNameQuery) {
        this.cityNameQuery = cityNameQuery;
        presenter.loadWeather();
    }

    @Override
    public void onCurrentLocationItemSelected() {
        loadWeatherFromCurrentLocation();
    }

    private void loadWeatherFromCurrentLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
        } else {
            if (locationListener == null) {
                locationListener = new LocListener();
            }

            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    10000L, 10000F, locationListener);

            Location loc;

            loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (loc == null) {
                loc = locManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            } else if (loc == null) {
                try {
                    loc = Objects.requireNonNull(locManager)
                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (loc != null) {
                currentLocation = loc;
                presenter.loadWeatherFromLocation();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 100) {
            boolean permissionsGranted = true;
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    permissionsGranted = false;
                    break;
                }
            }
            if (permissionsGranted) {
                recreate();
            } else {
                finishAndRemoveTask();
            }
        }
    }

    public Location getLocation() {
        return currentLocation;
    }

    private final class LocListener implements LocationListener {

        @Override
        public void onLocationChanged(@NonNull Location location) {
            currentLocation = location;
        }
    }
}