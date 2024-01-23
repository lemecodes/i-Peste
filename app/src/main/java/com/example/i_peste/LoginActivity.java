package com.example.i_peste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check login credentials
                if (isValidLogin()) {
                    // If login is successful, launch AdminActivity
                    launchAdminActivity();
                } else {
                    // Show a toast message for invalid login
                    showToast("Wrong password");
                }
            }
        });
    }

    private boolean isValidLogin() {
        // For simplicity, hardcoding username and password for testing
        String expectedUsername = "admin";
        String expectedPassword = "admin";

        // Retrieve entered username and password from EditText fields
        EditText etUsername = findViewById(R.id.etUsername);
        EditText etPassword = findViewById(R.id.etPassword);
        String enteredUsername = etUsername.getText().toString();
        String enteredPassword = etPassword.getText().toString();

        // Check if entered credentials match the expected ones
        return expectedUsername.equals(enteredUsername) && expectedPassword.equals(enteredPassword);
    }

    private void launchAdminActivity() {
        // Launch AdminActivity when login is successful
        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
        startActivity(intent);
        finish(); // Optional: Close the LoginActivity so the user cannot go back to it using the back button
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}