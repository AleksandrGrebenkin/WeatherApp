package com.github.alexandrgrebenkin.weatherapp.ui;

import android.os.Bundle;

import com.github.alexandrgrebenkin.weatherapp.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SwitchMaterial darkThemeSwitch = findViewById(R.id.activity_settings__sw_dark_theme);
        darkThemeSwitch.setChecked(isDarkTheme());
        darkThemeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setDarkTheme(isChecked);
            recreate();
        });

    }
}
