package com.github.alexandrgrebenkin.weatherapp.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.AboutDevFragment;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.CitiesFragment;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.FavoritesFragment;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.FeedbackFragment;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.HomeFragment;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.SettingsFragment;
import com.github.alexandrgrebenkin.weatherapp.ui.viewmodel.PlaceViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.HashSet;
import java.util.Set;

public class NavigationActivity extends BaseActivity
        implements CitiesFragment.Listener,
        SettingsFragment.Listener,
        FavoritesFragment.Listener,
        NavigationView.OnNavigationItemSelectedListener {

    private PlaceViewModel placeViewModel;
    private DrawerLayout drawer;
    private Menu menu;
    private Toolbar toolbar;

    private Set<PlaceViewModel> favorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        toolbar = initToolbar();
        initDrawer(toolbar);
        initNavigationListener();

        favorites = new HashSet<>();

        if (savedInstanceState == null) {
            placeViewModel = getDefaultPlace();
            setHomeFragment();
        } else {
            placeViewModel = savedInstanceState.getParcelable(HomeFragment.PLACE);
        }

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

    private void initNavigationListener() {
        NavigationView navigationView = findViewById(R.id.activity_navigation__nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setHomeFragment() {
        HomeFragment homeFragment = HomeFragment.newInstance(placeViewModel);
        setFragment(homeFragment);
        getSupportActionBar().setTitle(placeViewModel.getName());
        if (menu != null) {
            updateFavoriteIcon();
        }
    }

    private void setSearchFragment() {
        setFragment(new CitiesFragment());
        getSupportActionBar().setTitle(R.string.search);
    }

    private void setSettingsFragment() {
        SettingsFragment settingsFragment = SettingsFragment.newInstance(isDarkTheme());
        setFragment(settingsFragment);
        getSupportActionBar().setTitle(R.string.settings);
    }

    private void setFavoritesFragment() {
        FavoritesFragment favoritesFragment = FavoritesFragment.newInstance(favorites);
        setFragment(favoritesFragment);
        getSupportActionBar().setTitle(R.string.favorites);
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

    private void optionMenuItemClick(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.menu_main__add_favorite: {
                favorites.add(placeViewModel);
                break;
            }
            case R.id.menu_main__remove_favorite: {
                favorites.remove(placeViewModel);
                break;
            }
        }

        updateFavoriteIcon();
    }

    @Override
    public void itemClicked(PlaceViewModel placeViewModel) {
        this.placeViewModel = placeViewModel;
        setHomeFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        updateFavoriteIcon();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        optionMenuItemClick(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.menu_nav__i_search:
                setSearchFragment();
                break;
            case R.id.menu_nav__i_settings:
                setSettingsFragment();
                break;
            case R.id.menu_nav__i_favorites:
                setFavoritesFragment();
                break;
            case R.id.menu_nav__i_feedback:
                setFeedbackFragment();
                break;
            case R.id.menu_nav__i_about:
                setAboutDevFragment();
                break;
            default:
                setHomeFragment();
        }

        if (id != R.id.menu_nav__i_home) {
            setFavoritesInvisible();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(HomeFragment.PLACE, placeViewModel);
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

    @Override
    public void favoriteClick(PlaceViewModel placeViewModel) {
        this.placeViewModel = placeViewModel;
        setHomeFragment();
    }

    private PlaceViewModel getDefaultPlace() {
        PlaceViewModel placeViewModel = new PlaceViewModel();
        placeViewModel.setName("Москва");
        placeViewModel.setDisplayName("Москва, Центральный административный округ, Россия");
        placeViewModel.setLat("55.7504461");
        placeViewModel.setLon("37.6174943");
        return placeViewModel;
    }

    private void updateFavoriteIcon(){
        boolean isFavorite;
        if (favorites.contains(placeViewModel)){
            isFavorite = true;
        } else {
            isFavorite = false;
        }
        menu.findItem(R.id.menu_main__add_favorite).setVisible(!isFavorite);
        menu.findItem(R.id.menu_main__remove_favorite).setVisible(isFavorite);
    }

    private void setFavoritesInvisible(){
        menu.findItem(R.id.menu_main__add_favorite).setVisible(false);
        menu.findItem(R.id.menu_main__remove_favorite).setVisible(false);
    }
}