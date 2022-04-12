package com.lau.foodify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Cart extends AppCompatActivity {

    double price;
    TextView total_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        total_price = (TextView) findViewById(R.id.price);

    }
}