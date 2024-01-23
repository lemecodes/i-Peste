package com.example.i_peste;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.i_peste.ml.Try1;
import com.example.i_peste.ml.Try4;


import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_REQUEST_CODE = 1;
    private static final int GALLERY_PICK_REQUEST_CODE = 2;

    private TextView result, confidence, confidenceText, classifiedText;
    private ImageView imageView;
    private Button picture, galleryButton, viewInformationButton;
    private int imageSize = 224;
    private String[] classes = {"Mole cricket", "Leaf folder", "Case worm", "Yellow stem borer", "Brown planthopper", "Zigzag leafhopper", "Dragon fly", "Orb spider", "Wolf spider", "Ripple bug"};

    private Bitmap capturedPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        result = findViewById(R.id.result);
        confidence = findViewById(R.id.confidence);
        confidenceText = findViewById(R.id.confidencesText);
        classifiedText = findViewById(R.id.classified); // Added classifiedText
        imageView = findViewById(R.id.imageView);
        picture = findViewById(R.id.button);
        galleryButton = findViewById(R.id.galleryButton);
        viewInformationButton = findViewById(R.id.viewInformationButton);
        viewInformationButton.setVisibility(View.GONE);

        // Initially hide confidence, confidenceText, classifiedText, and viewInformationButton
        confidence.setVisibility(View.GONE);
        confidenceText.setVisibility(View.GONE);
        classifiedText.setVisibility(View.GONE);
        viewInformationButton.setVisibility(View.GONE);

        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkCameraPermission()) {
                    openCamera();
                } else {
                    requestCameraPermission();
                }
            }
        });

        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        viewInformationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String capturedItem = result.getText().toString();
                if (!capturedItem.isEmpty() && !capturedItem.equals("Please Capture Again")) {
                    Intent informationIntent = new Intent(MainActivity.this, InformationActivity.class);
                    informationIntent.putExtra("capturedItem", capturedItem);

                    // Pass the captured photo and classification result to InformationActivity
                    if (capturedPhoto != null) {
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        capturedPhoto.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        informationIntent.putExtra("capturedPhoto", byteArray);
                        informationIntent.putExtra("classifiedResult", capturedItem);

                        // Pass additional information for Pen
                        if ("Mole cricket".equals(capturedItem)) {
                            informationIntent.putExtra("", "");
                            informationIntent.putExtra("", "");
                            informationIntent.putExtra("", "");
                        } else if ("Leaf folder".equals(capturedItem)) {
                            informationIntent.putExtra("", "");
                            informationIntent.putExtra("", "");
                        } else if ("Case worm".equals(capturedItem)) {
                            informationIntent.putExtra("", "");
                            informationIntent.putExtra("", "");
                        } else if ("Yellow stem borer".equals(capturedItem)) {
                            informationIntent.putExtra("", "");
                            informationIntent.putExtra("", "");
                        } else if ("Brown planthopper".equals(capturedItem)) {
                            informationIntent.putExtra("", "");
                            informationIntent.putExtra("", "");
                        } else if ("Zigzag leafhopper".equals(capturedItem)) {
                            informationIntent.putExtra("", "");
                            informationIntent.putExtra("", "");
                        } else if ("Dragonfly".equals(capturedItem)) {
                            informationIntent.putExtra("", "");
                            informationIntent.putExtra("", "");
                        } else if ("Orb spider".equals(capturedItem)) {
                            informationIntent.putExtra("", "");
                            informationIntent.putExtra("", "");
                        } else if ("Wolf spider".equals(capturedItem)) {
                            informationIntent.putExtra("", "");
                            informationIntent.putExtra("", "");
                        } else if ("Ripple bug".equals(capturedItem)) {
                            informationIntent.putExtra("", "");
                            informationIntent.putExtra("", "");

                        }
                    }

                    startActivity(informationIntent);
                }
            }
        });
    }

    private boolean checkCameraPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_CAPTURE_REQUEST_CODE);
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_PICK_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Log.d("MainActivity", "Camera permission denied");
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA_CAPTURE_REQUEST_CODE && resultCode == RESULT_OK) {
            capturedPhoto = (Bitmap) data.getExtras().get("data");
            int dimension = Math.min(capturedPhoto.getWidth(), capturedPhoto.getHeight());
            capturedPhoto = ThumbnailUtils.extractThumbnail(capturedPhoto, dimension, dimension);
            imageView.setImageBitmap(capturedPhoto);

            capturedPhoto = Bitmap.createScaledBitmap(capturedPhoto, imageSize, imageSize, false);
            classifyImage(capturedPhoto);
        } else if (requestCode == GALLERY_PICK_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                int dimension = Math.min(bitmap.getWidth(), bitmap.getHeight());
                bitmap = ThumbnailUtils.extractThumbnail(bitmap, dimension, dimension);
                imageView.setImageBitmap(bitmap);

                bitmap = Bitmap.createScaledBitmap(bitmap, imageSize, imageSize, false);
                classifyImage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void classifyImage(Bitmap image) {
        try {
            Try4 model = Try4.newInstance(getApplicationContext());

            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int[] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
            int pixel = 0;
            for (int i = 0; i < imageSize; i++) {
                for (int j = 0; j < imageSize; j++) {
                    int val = intValues[pixel++];
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 255.f));
                }
            }

            inputFeature0.loadBuffer(byteBuffer);

            Try4.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidences = outputFeature0.getFloatArray();

            // Find the maximum confidence value
            float maxConfidence = 0;
            for (float confidence : confidences) {
                if (confidence > maxConfidence) {
                    maxConfidence = confidence;
                }
            }

            if (maxConfidence < 0.7f) {
                result.setText("Please Capture Again");
                // Hide confidence, confidenceText, classifiedText, and viewInformationButton
                confidence.setVisibility(View.GONE);
                confidenceText.setVisibility(View.GONE);
                classifiedText.setVisibility(View.GONE);
                viewInformationButton.setVisibility(View.GONE);
            } else {
                // Create a list of Confidence objects to store both confidence values and corresponding class indices
                List<Confidence> confidenceList = new ArrayList<>();
                for (int i = 0; i < confidences.length; i++) {
                    confidenceList.add(new Confidence(i, confidences[i]));
                }

                // Sort the list in descending order based on confidence values
                Collections.sort(confidenceList, Collections.reverseOrder());

                // Display the top 3 confidences and corresponding classes
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < Math.min(3, confidenceList.size()); i++) {
                    Confidence confidenceItem = confidenceList.get(i);
                    s.append(String.format("%s: %.1f%%\n", classes[confidenceItem.getIndex()], confidenceItem.getValue() * 100));
                }

                // Show the result and confidence for the class with the highest confidence
                result.setText(classes[confidenceList.get(0).getIndex()]);
                confidence.setText(s.toString());

                // Show confidence, confidenceText, classifiedText, and viewInformationButton
                confidence.setVisibility(View.VISIBLE);
                confidenceText.setVisibility(View.VISIBLE);
                classifiedText.setVisibility(View.VISIBLE);
                viewInformationButton.setVisibility(View.VISIBLE);
            }

            // Store the captured photo for later use
            capturedPhoto = image;

            model.close();
        } catch (IOException e) {
            // Handle the exception
            Log.e("MainActivity", "Error processing image: " + e.getMessage());
        }
    }

        // Confidence class to store both confidence values and corresponding class indices
        private static class Confidence implements Comparable<Confidence> {
            private final int index;
            private final float value;

            public Confidence(int index, float value) {
                this.index = index;
                this.value = value;
            }

            public int getIndex() {
                return index;
            }

            public float getValue() {
                return value;
            }

            @Override
            public int compareTo(Confidence other) {
                // Compare based on confidence values
                return Float.compare(this.value, other.value);
            }
        }
    }
