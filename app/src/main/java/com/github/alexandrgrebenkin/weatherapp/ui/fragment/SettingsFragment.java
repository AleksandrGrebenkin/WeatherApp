package com.github.alexandrgrebenkin.weatherapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexandrgrebenkin.weatherapp.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsFragment extends Fragment {

    public static final String SETTINGS = "com.github.alexandrgrebenkin.weatherapp.SETTINGS";

    private SwitchMaterial darkThemeSwitch;
    private boolean isDarkTheme;
    private Listener listener;

    public interface Listener {
        void settingsChanged(boolean isDarkTheme);
    }

    public static SettingsFragment newInstance(boolean isDarkTheme) {
        SettingsFragment settingsFragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putBoolean(SETTINGS, isDarkTheme);
        settingsFragment.setArguments(args);
        return settingsFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (SettingsFragment.Listener) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        isDarkTheme = getArguments().getBoolean(SETTINGS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
        setDarkThemeSwitchListener();
    }

    private void init() {
        View view = getView();
        if (view != null) {
            darkThemeSwitch = view.findViewById(R.id.fragment_settings__sw_dark_theme);
            darkThemeSwitch.setChecked(isDarkTheme);
        }
    }

    private void setDarkThemeSwitchListener() {
        darkThemeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isDarkTheme = isChecked;
            listener.settingsChanged(isDarkTheme);
        });
    }
}