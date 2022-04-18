package com.lau.foodify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import com.lau.foodify.databinding.MainActivityBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> items;
    ArrayAdapter<String> adapter;
    Intent intent;
    EditText search;
    ImageView search_icon;

    MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);


        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] food = {"Burger","Pizza","Fasoulya","Mjadra",
                "Cesear salad","Chicken parm","Pasta","Nutella","Crepe","Fish"};
        int[] flowerImages = {R.drawable.pizza,R.drawable.burger,R.drawable.pizza,R.drawable.burger,R.drawable.pizza,R.drawable.burger,
                R.drawable.pizza,R.drawable.burger,R.drawable.pizza,R.drawable.burger,};
        String[] weight = {"200 g", "10 Kg","1 Jar","10 pieces","1 can","100 g","10 boxes","100 g","10 min","10 min"};
        String[] location = {"Fridge","1st shelf","Fridge","1st shelf","Fridge","1st shelf","Fridge","1st shelf","Fridge","1st shelf"};
        String[] date = {"Expires in 2 days", "Expires in 1 year","Expires in 2 days", "Expires in 1 year","Expires in 2 days", "Expires in 1 year","Expires in 2 days", "Expires in 1 year","Expires in 2 days", "Expires in 1 year",};

        GridAdapterPantry gridAdapter = new GridAdapterPantry(this,food,weight,location,date,flowerImages);
        binding.list.setAdapter(gridAdapter);


        search = (EditText) findViewById(R.id.search_tab);
        search_icon = (ImageView) findViewById(R.id.search_icon);

    }

    public void tocookbook(View view){
        intent = new Intent(getApplicationContext(), cookbook.class);
        startActivity(intent);
    }
    public void tocart(View view){
        intent = new Intent(getApplicationContext(), Cart.class);
        startActivity(intent);
    }
    public void toadd(View view){
        intent = new Intent(getApplicationContext(), Add.class);
        startActivity(intent);
    }
    public void toprofile(View view){
        intent = new Intent(getApplicationContext(), Calendar.class);
        startActivity(intent);
    }

    public void search (View view){

        String item = search.getText().toString();

        // connect to api
        // reset adapter
    }

}