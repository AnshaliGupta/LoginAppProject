package com.anshali.demologinapp.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anshali.demologinapp.R;
import com.anshali.demologinapp.helper.PreferenceHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SignInActivity extends AppCompatActivity {

    private TextInputEditText demoEmailEditText, demoPasswordEditText;
    private MaterialButton demoLoginButton;
    private TextView demoSignUpView;
    //private String  USER_EMAIL="anshaligupta@gmail.com";
    //private String USER_PASSWORD="123456";
    private PreferenceHelper preferenceHelper;
    private ImageView signInWithGoogle, signInWithFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize PreferenceHelper
        preferenceHelper = PreferenceHelper.getInstance(this);

        if(preferenceHelper.IsUserLoggedIn()) {
            Intent homeIntent = new Intent(this, HomeActivity.class);
            startActivity(homeIntent);
            finish();
        } else {
            setContentView(R.layout.activity_sign);
        }

        initViews();

        demoLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = String.valueOf(demoEmailEditText.getText());
                String password = String.valueOf(demoPasswordEditText.getText());
                String savedEmail = preferenceHelper.getString(PreferenceHelper.EMAIL);
                String savedPassword = preferenceHelper.getString(PreferenceHelper.PWD);

                if(validData(email, password)) {
                    if(email.equals(savedEmail) && password.equals(savedPassword)) {
                        preferenceHelper.saveLogin(email, password);
                        Intent loginIntent=new Intent(SignInActivity.this,HomeActivity.class);
                        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(loginIntent);
                        finishAffinity(); //finish all previous activities
                    } else {
                        Toast.makeText(SignInActivity.this, "Incorrect Login Details", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signInWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent googleintent = new Intent(SignInActivity.this, WithGoogleActivity.class);
                startActivity(googleintent);
            }
        });

        signInWithFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookintent = new Intent(SignInActivity.this, WithFacebookActivity.class);
                startActivity(facebookintent);
            }
        });

        demoSignUpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent demosignupintent=new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(demosignupintent);
            }
        });
    }

    private boolean validData(String email, String password) {
        if(email.isEmpty()) {
            demoEmailEditText.setError("Email is empty");
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            demoEmailEditText.setError("Incorrect Email");
            return false;
        }
        if(password.isEmpty()) {
            demoPasswordEditText.setError("Password is empty");
            return false;
        }
        return true;
    }
    private void initViews() {
        demoEmailEditText = findViewById(R.id.demo_email_input);
        demoPasswordEditText = findViewById(R.id.demo_password_input);
        demoLoginButton = findViewById(R.id.demo_login_button);
        demoSignUpView = findViewById(R.id.demo_sign_up_link);
        signInWithGoogle = findViewById(R.id.sign_in_google);
        signInWithFacebook = findViewById(R.id.sign_in_facebook);
    }
}