package com.anshali.demologinapp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.SpinnerAdapter;

import com.anshali.demologinapp.R;
import com.anshali.demologinapp.helper.PreferenceHelper;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                PreferenceHelper demopreferenceHelper= new PreferenceHelper(SplashScreenActivity.this);
                Intent intent;
                if(demopreferenceHelper.IsUserLoggedIn()) {
                    intent = new Intent(SplashScreenActivity.this,HomeActivity.class);
                } else {
                    intent = new Intent(SplashScreenActivity.this,SignInActivity.class);
                }
                startActivity(intent);
                finish();
            }
        },2000);
    }
}