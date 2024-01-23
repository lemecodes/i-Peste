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

        // Assuming you have EditText fields for username and password in your layout
        EditText etUsername = findViewById(R.id.editTextUsername);
        EditText etPassword = findViewById(R.id.editTextPassword);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve the entered username and password
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                // Check if the username and password are valid (hardcoded to "admin" for this example)
                if (isValidCredentials(username, password)) {
                    // If valid, set the login state (you might use SharedPreferences or another method)
                    setLoggedIn(true);

                    // Proceed to launch the ReportActivity
                    Intent intent = new Intent(LoginActivity.this, ReportActivity.class);
                    startActivity(intent);

                    // Finish the LoginActivity so the user can't navigate back to it without logging in again
                    finish();
                } else {
                    // If credentials are not valid, show an error message or handle accordingly
                    Toast.makeText(LoginActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidCredentials(String username, String password) {
        // Check if the entered credentials are "admin" for both username and password
        return username.equals("admin") && password.equals("admin");
    }

    private void setLoggedIn(boolean isLoggedIn) {
        // Implement your logic to set the login state
        // This could involve saving a user session, setting a flag, or other methods
        // Replace the following line with your actual login state setting logic
        // For example, you might use SharedPreferences to store the login state
        // SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
        // editor.putBoolean("isLoggedIn", isLoggedIn);
        // editor.apply();
    }
}