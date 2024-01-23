//package com.example.i_peste;
//
//
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class UserTypeActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_type);
//
//        Button farmerButton = findViewById(R.id.farmerButton);
//        Button technicianButton = findViewById(R.id.technicianButton);
//
//        farmerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Handle Farmer button click
//                Intent intent = new Intent(UserTypeActivity.this, .class);
//                startActivity(intent);
//            }
//        });
//
//        technicianButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Launch TechnicianLoginActivity for technician login
//                Intent intent = new Intent(UserTypeActivity.this, TechnicianLoginActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//}