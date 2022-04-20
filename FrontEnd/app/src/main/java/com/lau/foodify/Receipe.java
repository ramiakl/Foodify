package com.lau.foodify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Receipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipe);

        Intent x = getIntent();
        String receipe = x.getStringExtra("Chosen");
        TextView txt = (TextView) findViewById(R.id.test);
        txt.setText(receipe);
    }
}