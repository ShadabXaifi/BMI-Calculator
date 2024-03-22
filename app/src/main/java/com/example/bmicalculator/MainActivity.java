package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText weight_EditText;
    EditText heightFoot_EditText;
    EditText heightInch_EditText;
    Button submit_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        
        // Initialize EditText fields and Button
        weight_EditText = findViewById(R.id.weight);
        heightFoot_EditText = findViewById(R.id.h_foot);
        heightInch_EditText = findViewById(R.id.h_inch);
        submit_Button = findViewById(R.id.Submit);

        submit_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String weight = weight_EditText.getText().toString();
                String heightFoot = heightFoot_EditText.getText().toString();
                String heightInch = heightInch_EditText.getText().toString();

                // Check if any input field is empty
                if (weight.isEmpty() || heightFoot.isEmpty() || heightInch.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Fill All The Field", Toast.LENGTH_SHORT).show();
                    return;
                }

                double wt = Double.parseDouble(weight);
                int foot = Integer.parseInt(heightFoot);
                int inch = Integer.parseInt(heightInch);

                // Convert height to meters
                double meter = ((foot * 12) + inch) * 0.0254;

                // Check if height is valid (not zero)
                if (meter == 0) {
                    // Show error message or toast indicating invalid height
                    Toast.makeText(MainActivity.this, "Invalid height", Toast.LENGTH_SHORT).show();
                    return; // Exit onClick() method to prevent further execution
                }
                // Calculate BMI
                double bmi = wt / (meter * meter);

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("bmi", bmi); // Assuming 'bmi' is the calculated BMI value
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



    }
}