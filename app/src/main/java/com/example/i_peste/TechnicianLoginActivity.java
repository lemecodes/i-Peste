package com.example.i_peste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class TechnicianLoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the entered username and password
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // TODO: Implement your authentication logic here
                // For simplicity, let's assume username and password are correct
                if (username.equals("admin") && password.equals("admin")) {
                    // If authentication is successful, navigate to MainActivity
                    Intent intent = new Intent(TechnicianLoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Close the login activity
                } else {
                    // Show an error message or handle unsuccessful login
                    // For simplicity, let's just clear the password field
                    passwordEditText.setText("");
                }
            }
        });
    }
}