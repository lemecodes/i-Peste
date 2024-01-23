package com.example.i_peste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class UserHomePage extends AppCompatActivity {
    private static final String TAG = "UserHomePage";

    private static final String KEY_DATE = "date";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth auth;
    private Button button;
    private EditText editTextReport;
    private EditText editTextDescription;
    private TextView textView;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        textView = findViewById(R.id.user_details);
        user = auth.getCurrentUser();

        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), UserLogin.class);
            startActivity(intent);
            finish();
        } else {
            textView.setText(user.getEmail());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), UserLogin.class);
                startActivity(intent);
                finish();
            }
        });

        editTextReport = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
    }

    public void saveReport(View view) {
        String report = editTextReport.getText().toString();
        String description = editTextDescription.getText().toString();

        String userId;
        String userEmail;

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser != null) {
            userId = currentUser.getUid();
            userEmail = currentUser.getEmail();
        } else {
            // Handle the case where the user is not authenticated
            Toast.makeText(UserHomePage.this, "User not authenticated", Toast.LENGTH_SHORT).show();
            return; // Return to avoid further execution
        }

        Map<String, Object> note = new HashMap<>();
        note.put(KEY_DATE, getFormattedTimestamp());
        note.put(KEY_DESCRIPTION, description);
        note.put(KEY_EMAIL, userEmail);
        note.put(KEY_ID, userId);
        note.put(KEY_TITLE, report);

        CollectionReference reportsCollection = db.collection("Reports");
        reportsCollection.add(note)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(UserHomePage.this, "Report submitted", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserHomePage.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }

    private String getFormattedTimestamp() {
        Timestamp timestamp = Timestamp.now();
        Date date = timestamp.toDate();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.getDefault());
        return sdf.format(date);
    }
}
