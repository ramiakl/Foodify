package com.lau.foodify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.lau.foodify.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class cookbook extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] flowerName = {"Burger","Pizza","Fasoulya","Mjadra",
                "Cesear salad","Chicken parm","Pasta","Nutella","Crepe","Fish"};
        int[] flowerImages = {R.drawable.pizza,R.drawable.burger,R.drawable.pizza,R.drawable.burger,R.drawable.pizza,R.drawable.burger,
                R.drawable.pizza,R.drawable.burger,R.drawable.pizza,R.drawable.burger,};
        String[] time = {"10 min", "10 min","10 min","10 min","10 min","10 min","10 min","10 min","10 min","10 min"};

        GridAdapter gridAdapter = new GridAdapter(this,flowerName,time,flowerImages);
        binding.gridView.setAdapter(gridAdapter);


    }
}