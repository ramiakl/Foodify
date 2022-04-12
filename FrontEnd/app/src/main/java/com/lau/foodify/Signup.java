package com.lau.foodify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Signup extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }

    public void signup (View view){

        intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    public void login (View view){

        intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
    }
}