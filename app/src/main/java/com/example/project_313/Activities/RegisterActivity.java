package com.example.project_313.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.project_313.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.jar.Attributes;

public class RegisterActivity extends AppCompatActivity {
// https://www.youtube.com/watch?v=RavhhhFwh4U

    ImageView regUserPhoto;
    static int PReqCode = 1;
    static int REQUESTCODE = 1;
    Uri pickedImgUri ;

    private EditText userEmail,userPassword,userPassword2,userName;
    private ProgressBar loadingProgress;
    private Button regBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        // Image storage viewer
        userName = findViewById(R.id.regName);
        userEmail = findViewById(R.id.regMail);
        userPassword = findViewById(R.id.regPassword);
        userPassword2 = findViewById(R.id.regPassword2);
        loadingProgress = findViewById(R.id.progressBar);
        regBtn = findViewById(R.id.regBtn);
        loadingProgress.setVisibility(View.INVISIBLE);

        //Firebase
        mAuth = FirebaseAuth.getInstance();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                regBtn.setVisibility(View.INVISIBLE);
                loadingProgress.setVisibility(View.VISIBLE);
                final String email = userEmail.getText().toString();
                //TODO: Implement SECURITY MEASURES
                final String password = userPassword.getText().toString();
                final String password2 = userPassword2.getText().toString();
                final String name = userName.getText().toString();


                if ( email.isEmpty() || name.isEmpty() || password.isEmpty() ||
                        !password.equals(password2)) {

                    // Error message
                    showMessage("Please verify all fields");

                    regBtn.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);


                }
                else {
                    // success
                    CreateUserAccount(name,email,password);
                }





            }
        });

        loadingProgress.setVisibility(View.INVISIBLE);


        regUserPhoto = findViewById(R.id.regUserPic);

        regUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= 22) {

                    checkAndRequestPermission();
                }
                else
                {
                    openGallery();
                }


            }
        });
    }

    private void CreateUserAccount(final String name, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // user account successful
                            showMessage("Account Created");

                            // after we created user account we need to update profile pic and name
                            updateUserInfo(name, pickedImgUri, mAuth.getCurrentUser());

                        }
                        else {

                            // user unsuccessful
                            showMessage("Account Creation Failed" + task.getException().getMessage());
                            regBtn.setVisibility(View.VISIBLE);
                            loadingProgress.setVisibility(View.INVISIBLE);
                        }
                    }
                });


    }

    // update user photo and name
    private void updateUserInfo(final String name, Uri pickedImgUri, final FirebaseUser currentUser) {

        // upload user photo to database
        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("user.photo");
        final StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());
        imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                // image upload successfully

                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        // uri contain image url
                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .setPhotoUri(uri)
                                .build();

                        currentUser.updateProfile(profileUpdate)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        // user info register complete
                                        showMessage("Register Complete");
                                        updateUI();
                                    }

                                }
                            });

                    }
                });
            }
        });
    }

    private void updateUI() {

        Intent homeActivity = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(homeActivity);
        finish();

    }

    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
    }

    private void openGallery() {
        //TODO: User gallery for image picking on register
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESTCODE);


    }

    private void checkAndRequestPermission() {

        if (ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(RegisterActivity.this, "Permission required for User Image", Toast.LENGTH_SHORT).show();

            } else {
                ActivityCompat.requestPermissions(RegisterActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }


        }
        else
            openGallery();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESTCODE && data != null ) {
            // The user has successfully picked an image
            // Need to save reference to Uri

            pickedImgUri = data.getData() ;
            regUserPhoto.setImageURI(pickedImgUri);


        }

    }
}