package com.app.medicedelivery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


/**
 * This class has the activity_confirmation view
 * and the functionality of the buttons.
 */
public class ConfirmationActivity extends AppCompatActivity {
    Button btnGoHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirmation);
        btnGoHome = findViewById(R.id.btnGoHome);
        btnGoHome.setOnClickListener(v -> startActivity(new Intent(ConfirmationActivity.this,
                MainActivity.class)));

    }
}