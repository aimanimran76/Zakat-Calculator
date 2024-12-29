package com.example.zakatcalculator;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Apply the user's selected theme
        ThemeManager.applyTheme(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // Initialize UI elements
        EditText weightEditText = findViewById(R.id.weightEditText);
        EditText valueEditText = findViewById(R.id.valueEditText);
        RadioGroup typeRadioGroup = findViewById(R.id.typeRadioGroup);
        Button calculateButton = findViewById(R.id.calculateButton);
        TextView resultTextView = findViewById(R.id.resultTextView);

        // Calculation logic
        calculateButton.setOnClickListener(view -> {
            String weightStr = weightEditText.getText().toString();
            String valueStr = valueEditText.getText().toString();

            if (weightStr.isEmpty() || valueStr.isEmpty() || typeRadioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double weight = Double.parseDouble(weightStr);
            double valuePerGram = Double.parseDouble(valueStr);
            int typeId = typeRadioGroup.getCheckedRadioButtonId();

            double uruf = (typeId == R.id.keepRadioButton) ? 85 : 200;
            double goldValue = weight * valuePerGram;
            double zakatPayable = (weight > uruf) ? (weight - uruf) * valuePerGram : 0;
            double totalZakat = zakatPayable * 0.025;

            resultTextView.setText(String.format("Gold Value: RM %.2f\nZakat Payable: RM %.2f\nTotal Zakat: RM %.2f",
                    goldValue, zakatPayable, totalZakat));
        });

        // Back button logic
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> finish());
    }
}
