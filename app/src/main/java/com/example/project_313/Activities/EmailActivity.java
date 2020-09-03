package com.example.project_313.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_313.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

// Facebook


public class EmailActivity extends AppCompatActivity {

    private EditText userMail,userPassword;
    private Button btnLogin;
    private ProgressBar loginProgress;
    private FirebaseAuth mAuth;
    private Intent HomeActivity;
    private TextView errorMsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        getSupportActionBar().hide();

        userMail = findViewById(R.id.logName);
        userPassword = findViewById(R.id.logPassword);
        btnLogin = findViewById(R.id.log_btn);
        loginProgress = findViewById(R.id.progressBar2);
        mAuth = FirebaseAuth.getInstance();
        errorMsg = findViewById(R.id.logError);
        HomeActivity = new Intent(this,com.example.project_313.Activities.HomeActivity.class);

        errorMsg.setVisibility(View.INVISIBLE);
        loginProgress.setVisibility(View.INVISIBLE);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorMsg.setVisibility(View.INVISIBLE);
                loginProgress.setVisibility(View.VISIBLE);
                btnLogin.setVisibility(View.INVISIBLE);

                final String mail = userMail.getText().toString();

                //TODO: Implement SECURITY MEASURES
                final String password = userPassword.getText().toString();

                if (mail.isEmpty() || password.isEmpty()) {
                    errorMsg.setVisibility(View.VISIBLE);
                    errorMsg.setText("Please Verify all fields");
                    btnLogin.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
                }
                else {
                    signin(mail,password);
                }

            }
        });
    }

    private void signin(String mail, String password) {

        mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    loginProgress.setVisibility(View.INVISIBLE);
                    btnLogin.setVisibility(View.VISIBLE);
                    updateUI();
                }
                else {
                    errorMsg.setVisibility(View.VISIBLE);
                    errorMsg.setText((task.getException().getMessage()));
//                    showMessage(task.getException().getMessage());
                    btnLogin.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
                }

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