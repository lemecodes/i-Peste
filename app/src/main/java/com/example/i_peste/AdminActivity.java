package com.example.i_peste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

//        Button btnViewReport = findViewById(R.id.btnViewReport);
//        btnViewReport.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Check if the user is logged in
//                Intent intent = new Intent(AdminActivity.this, ReportActivity.class);
//                startActivity(intent);
//            }
//        });

        Button btnMaintenance = findViewById(R.id.btnMaintenance);
        btnMaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, MaintenanceActivity.class);
                startActivity(intent);
            }
        });

        Button btnViewReport = findViewById(R.id.btnViewReport);
        btnViewReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, ViewReports.class);
                startActivity(intent);
            }
        });
    }
}

