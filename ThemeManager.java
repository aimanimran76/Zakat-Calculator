package com.example.zakatcalculator;

import android.content.Context;
import android.content.SharedPreferences;

public class ThemeManager {

    private static final String PREFS_NAME = "AppPreferences";
    private static final String KEY_THEME = "isDarkTheme";

    public static void applyTheme(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean isDarkTheme = sharedPreferences.getBoolean(KEY_THEME, false);
        if (isDarkTheme) {
            context.setTheme(R.style.Theme_ZakatCalculator_Dark);
        } else {
            context.setTheme(R.style.Theme_ZakatCalculator_Light);
        }
    }

    public static void toggleTheme(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean isDarkTheme = sharedPreferences.getBoolean(KEY_THEME, false);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_THEME, !isDarkTheme);
        editor.apply();
    }
}
