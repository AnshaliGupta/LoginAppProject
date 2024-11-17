package com.anshali.demologinapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.anshali.demologinapp.R;
import com.anshali.demologinapp.helper.PreferenceHelper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText signUpName, signUpEmail, signUpPassword, signUpConfirmPassword;
    private MaterialButton signUpButton;
    private TextView termsAndConditions, signInView;
    private MaterialCheckBox checkTermsConditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        PreferenceHelper preferenceHelper = PreferenceHelper.getInstance(this);

        initViews();

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = String.valueOf(signUpName.getText());
                String email = String.valueOf(signUpEmail.getText());
                String password = String.valueOf(signUpPassword.getText());
                String confirmPassword = String.valueOf(signUpConfirmPassword.getText());
                Boolean checkBoxTerms = Boolean.valueOf(checkTermsConditions.isChecked());
                if(validData(name, email, password, confirmPassword, checkBoxTerms)) {
                    /* preferenceHelper.saveString("User_Name", name);
                    preferenceHelper.saveString("User_Email", email);
                    preferenceHelper.saveString("User_Password", password); */
                    preferenceHelper.saveSignUp(name, email, password);
                    Intent signUpIntent = new Intent(SignUpActivity.this, HomeActivity.class);
                    startActivity(signUpIntent);
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this, "Incorrect SignUp Details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        termsAndConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent termsIntent = new Intent(SignUpActivity.this, TermsAndConditionsActivity.class);
                startActivity(termsIntent);
            }
        });

        signInView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(signInIntent);
                finish();
            }
        });
    }

    private void initViews() {
        signUpName = findViewById(R.id.signup_name);
        signUpEmail = findViewById(R.id.signup_email);
        signUpPassword = findViewById(R.id.signup_password);
        signUpConfirmPassword = findViewById(R.id.signup_confirm_password);
        signUpButton = findViewById(R.id.signup_button);
        signInView = findViewById(R.id.signin_link);
        termsAndConditions = findViewById(R.id.terms_conditions);
        checkTermsConditions = findViewById(R.id.checkbox_terms_conditions);
    }

    private boolean validData(String name, String email, String password, String confirmPassword, Boolean checkBoxTermsConditions) {
        if(name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!checkBoxTermsConditions) {
            checkTermsConditions.setError("You must agree on terms and conditions!");
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signUpEmail.setError("Incorrect Email Address");
            return false;
        }
        if(!password.equals(confirmPassword)) {
            signUpConfirmPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }
}