package com.example.i_peste;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        // Retrieve data from SharedPreferences
        SharedPreferences preferences = getSharedPreferences("ReportData", MODE_PRIVATE);
        String capturedPhotoBase64 = preferences.getString("capturedPhoto", "");
        String classifiedResult = preferences.getString("classifiedResult", "");
        String additionalInformation = preferences.getString("additionalInformation", "");

        // Display data in the UI as needed
        displayCapturedPhoto(Base64.decode(capturedPhotoBase64, Base64.DEFAULT));
        displayClassificationResult(classifiedResult);
        //displayAdditionalInformation(additionalInformation);

        // Retrieve registration data from SharedPreferences
        SharedPreferences registrationPreferences = getSharedPreferences("RegistrationData", MODE_PRIVATE);
        String name = registrationPreferences.getString("name", "");
        String address = registrationPreferences.getString("address", "");
        String age = registrationPreferences.getString("age", "");
        String contact = registrationPreferences.getString("contact", "");

        // Display registration data
        displayRegistrationData(name, address, age, contact);
    }

    private void displayCapturedPhoto(byte[] byteArray) {
        try {
            if (byteArray != null && byteArray.length > 0) {
                // Convert the byte array back to a Bitmap
                Bitmap capturedPhoto = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

                // Find the ImageView in your layout and set the Bitmap
                ImageView imageView = findViewById(R.id.imageViewCapturedPhotoReport);
                imageView.setImageBitmap(capturedPhoto);
            } else {
                // Handle the case when the captured photo is null or empty
            }
        } catch (Exception e) {
            // Handle the exception
        }
    }

    private void displayClassificationResult(String result) {
        // Find the TextView in your layout and set the classification result
        TextView textViewResult = findViewById(R.id.textViewClassificationResultReport);
        textViewResult.setText("Classified as: " + result);
    }

 /*   private void displayAdditionalInformation(String information) {
        // Find the TextView in your layout and set additional information
        TextView informationTextView = findViewById(R.id.informationTextViewReport);
        informationTextView.setText(information);
    }*/

    private void displayRegistrationData(String name, String address, String age, String contact) {
        // Find the TextViews in your layout and set the registration data
        TextView textViewName = findViewById(R.id.textViewNameReport);
        TextView textViewAddress = findViewById(R.id.textViewAddressReport);
        TextView textViewAge = findViewById(R.id.textViewAgeReport);
        TextView textViewContact = findViewById(R.id.textViewContactReport);

        textViewName.setText("Captured by: " + name);
        textViewAddress.setText("Address: " + address);
        textViewAge.setText("Age: " + age);
        textViewContact.setText("Contact: " + contact);
    }
}