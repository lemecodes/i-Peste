package com.example.i_peste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        Button user = findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login2.this, UserLogin.class);
                startActivity(intent);
            }
        });
        Button btnViewReport = findViewById(R.id.btnAdmin);
        btnViewReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isLoggedIn()) {

                    Intent intent = new Intent(Login2.this, LoginActivity.class);
                    startActivity(intent);
                } else {

                    Intent intent = new Intent(Login2.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean isLoggedIn() {

        return false;
    }
}