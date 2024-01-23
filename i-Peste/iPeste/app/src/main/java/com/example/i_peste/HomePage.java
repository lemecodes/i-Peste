package com.example.i_peste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Button btnGetStarted = findViewById(R.id.btnGetStarted);
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch RegistrationActivity when "Get Started" is clicked
                Intent intent = new Intent(HomePage.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        Button btnViewReport = findViewById(R.id.btnViewReport);
        btnViewReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if the user is logged in
                if (isLoggedIn()) {
                    // User is logged in, launch ReportActivity
                    Intent intent = new Intent(HomePage.this, ReportActivity.class);
                    startActivity(intent);
                } else {
                    // User is not logged in, launch LoginActivity
                    Intent intent = new Intent(HomePage.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isLoggedIn() {

        return false;
    }
}