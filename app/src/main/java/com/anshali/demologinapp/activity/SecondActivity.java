package com.anshali.demologinapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.anshali.demologinapp.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String name;

        //to pass data from one activity to another
        Intent intent = getIntent();
        if(intent != null) {
            name = ((Intent) intent).getStringExtra("full_name");
        }
    }
}