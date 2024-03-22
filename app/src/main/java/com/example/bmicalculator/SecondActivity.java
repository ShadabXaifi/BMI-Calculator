package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    LinearLayout llmain;
    TextView display_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        display_text = findViewById(R.id.display_text);
        llmain = findViewById(R.id.main);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            double bmi = bundle.getDouble("bmi");
            // Do something with the BMI value

            if (bmi < 18.5) {
                display_text.setText(R.string.underweight);
                llmain.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
            } else if (bmi >= 18.5 && bmi < 24.9) {
                display_text.setText(R.string.healthy);
                llmain.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
            } else if (bmi >= 24.9 && bmi < 29.9) {
                display_text.setText(R.string.overweight);
                llmain.setBackgroundColor(ContextCompat.getColor(this, R.color.liteRed));
            } else if (bmi >= 30) {
                display_text.setText(R.string.obesity);
                llmain.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
            }

             }
        }
    }