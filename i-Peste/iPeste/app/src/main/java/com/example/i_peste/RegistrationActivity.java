package com.example.i_peste;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    // Declare EditText fields
    private EditText editTextName;
    private EditText editTextAddress;
    private EditText editTextAge;
    private EditText editTextContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize EditText fields
        editTextName = findViewById(R.id.editTextName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextAge = findViewById(R.id.editTextAge);
        editTextContact = findViewById(R.id.editTextContact);

        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle form submission
                if (validateForm()) {
                    // If the form is valid, store registration data in SharedPreferences
                    saveRegistrationData();

                    // Proceed to MainActivity
                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Finish the RegistrationActivity to prevent going back to it
                }
            }
        });
    }

    // Validate the form input (you can add more validation as needed)
    private boolean validateForm() {
        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();
        String contact = editTextContact.getText().toString().trim();

        if (name.isEmpty() || address.isEmpty() || age.isEmpty() || contact.isEmpty()) {
            // Display a Toast message indicating that the form is incomplete
            Toast.makeText(this, "Please fill up all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validate the contact number has exactly 11 digits
        if (contact.length() != 11) {
            // Display a Toast message indicating an invalid contact number
            Toast.makeText(this, "Contact number must be exactly 11 digits", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Additional validation logic can be added here if needed

        return true;
    }

    // Store registration data in SharedPreferences
    private void saveRegistrationData() {
        SharedPreferences preferences = getSharedPreferences("RegistrationData", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("name", editTextName.getText().toString());
        editor.putString("address", editTextAddress.getText().toString());
        editor.putString("age", editTextAge.getText().toString());
        editor.putString("contact", editTextContact.getText().toString());

        editor.apply();
    }
}