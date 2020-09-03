package com.example.project_313.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project_313.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private Button btnEmail;
    private FirebaseAuth mAuth;
    private Intent HomeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        HomeActivity = new Intent(this,com.example.project_313.Activities.HomeActivity.class);

        btnEmail = findViewById(R.id.btn_email);
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent emailActivity = new Intent(getApplicationContext(), EmailActivity.class);
                startActivity(emailActivity);
//                finish();

            }
        });
    }


    private void updateUI() {
        startActivity(HomeActivity);
        finish();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            // user already connected
            updateUI();

        }
    }
}