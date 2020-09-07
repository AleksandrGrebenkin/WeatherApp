package com.github.alexandrgrebenkin.weatherapp.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.location.Address;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.alexandrgrebenkin.weatherapp.ui.loader.AddressLoader;
import com.github.alexandrgrebenkin.weatherapp.ui.event.AddressLoaderEvent;
import com.github.alexandrgrebenkin.weatherapp.R;
import com.github.alexandrgrebenkin.weatherapp.ui.event.UnknownExceptionEvent;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.AboutDevFragment;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.FeedbackFragment;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.HomeFragment;
import com.github.alexandrgrebenkin.weatherapp.ui.fragment.SettingsFragment;
import com.google.android.material.navigation.NavigationView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class NavigationActivity extends BaseActivity
        implements SettingsFragment.Listener,
        NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    private SearchView searchView;

    private Address address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        EventBus.getDefault().register(this);

        Toolbar toolbar = initToolbar();
        initDrawer(toolbar);
        initNavigationListener();

        if (savedInstanceState == null) {
            getDefaultAddress();
        } else {
            address = savedInstanceState.getParcelable(HomeFragment.ADDRESS);
            setHomeFragment();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
        HomeFragment homeFragment = HomeFragment.newInstance(address);
        setFragment(homeFragment);

        getSupportActionBar().setTitle(address.getLocality());
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
                loadAddress(query);
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
                setHomeFragment();
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
        outState.putParcelable(HomeFragment.ADDRESS, address);
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

    private void getDefaultAddress() {
        loadAddress("Москва");
    }

    private void loadAddress(String query) {
        AddressLoader addressLoader = new AddressLoader();
        addressLoader.loadAddress(NavigationActivity.this, query);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(AddressLoaderEvent event) {
        if (event.getAddress() == null || event.getAddress().getLocality() == null) {
            Toast.makeText(this, "Объект не найден", Toast.LENGTH_SHORT).show();
        } else {
            address = event.getAddress();
            setHomeFragment();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(UnknownExceptionEvent event) {
        Toast.makeText(this, "Unknown error", Toast.LENGTH_SHORT).show();
    }
}