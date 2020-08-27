package com.github.alexandrgrebenkin.weatherapp.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.alexandrgrebenkin.weatherapp.R;

public class BaseActivity extends AppCompatActivity {

    private static final String NameSharedPreference = "LOGIN";

    private static final String IsDarkTheme = "IS_DARK_THEME";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        if (isDarkTheme()) {
            setTheme(R.style.AppDarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
    }

    protected boolean isDarkTheme() {
        return sharedPreferences.getBoolean(IsDarkTheme, true);
    }

    protected void setDarkTheme(boolean isDarkTheme) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IsDarkTheme, isDarkTheme);
        editor.apply();
    }

    protected void restartActivity() {
        Intent i = getIntent();
        this.overridePendingTransition(0, 0);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.finish();
        //restart the activity without animation
        this.overridePendingTransition(0, 0);
        this.startActivity(i);
    }
}
