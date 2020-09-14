package com.github.alexandrgrebenkin.weatherapp.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.alexandrgrebenkin.weatherapp.ui.WeatherModel;
import com.github.alexandrgrebenkin.weatherapp.ui.WeatherPresenter;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.ObjectNotFoundDialogFragment;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.UnknownErrorDialogFragment;
import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.AboutDevFragment;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.FeedbackFragment;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.HomeFragment;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.SettingsFragment;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.WeatherViewModel;
import com.google.android.material.navigation.NavigationView;

public class NavigationActivity extends BaseActivity
        implements SettingsFragment.Listener,
        NavigationView.OnNavigationItemSelectedListener {

    public static final String CITY_NAME_QUERY = "com.github.alexandrgrebenkin.weatherapp.CITY_NAME_QUERY";

    private DrawerLayout drawer;

    private SearchView searchView;

    private WeatherPresenter presenter;

    private String cityNameQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = initToolbar();
        initDrawer(toolbar);
        initNavigationListener();

        if (savedInstanceState == null) {
            cityNameQuery = "Москва";
        } else {
            cityNameQuery = savedInstanceState.getString(CITY_NAME_QUERY);
        }
        setWeatherPresenter();
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
        presenter.viewIsReady();
    }

    private void initNavigationListener() {
        NavigationView navigationView = findViewById(R.id.activity_navigation__nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void updateWeather(WeatherViewModel weatherViewModel) {
        getSupportActionBar().setTitle(weatherViewModel.getCurrentWeatherViewModel().getCityName());
        HomeFragment homeFragment = HomeFragment.getInstance(weatherViewModel);
        setFragment(homeFragment);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                cityNameQuery = query;
                presenter.loadWeather();
                searchView.onActionViewCollapsed();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {
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

        if (id != R.id.menu_nav__i_home) {
            searchView.setVisibility(View.GONE);
        } else {
            searchView.setVisibility(View.VISIBLE);
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
        } else if (!searchView.isIconified()) {
            searchView.onActionViewCollapsed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void settingsChanged(boolean isDarkTheme) {
        setDarkTheme(isDarkTheme);
        recreate();
    }

    public void showObjectNotFoundDialog(){
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
}