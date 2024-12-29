package com.example.zakatcalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Load saved theme
        SharedPreferences sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE);
        boolean isDarkTheme = sharedPreferences.getBoolean("isDarkTheme", false);
        if (isDarkTheme) {
            setTheme(R.style.Theme_ZakatCalculator_Dark);
        } else {
            setTheme(R.style.Theme_ZakatCalculator_Light);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Theme switch logic
        Button changeThemeButton = findViewById(R.id.changeThemeButton);
        changeThemeButton.setOnClickListener(view -> {
            // Toggle theme
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isDarkTheme", !isDarkTheme);
            editor.apply();

            // Restart activity to apply the new theme
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        });

        // Navigate to About Page
        Button aboutPageButton = findViewById(R.id.aboutPageButton);
        aboutPageButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });

        // Navigate to Calculator Page
        Button calculatorButton = findViewById(R.id.calculatorButton);
        calculatorButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
            startActivity(intent);
        });

        // Share app button logic
        Button shareButton = findViewById(R.id.shareButton);
        shareButton.setOnClickListener(view -> {
            // Create an implicit intent for sharing the app
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out this app: [Insert app URL or name]");
            sendIntent.setType("text/plain");

            // Start the activity to share the app
            startActivity(Intent.createChooser(sendIntent, "Share via"));
        });
    }
}
