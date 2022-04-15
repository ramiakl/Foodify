package com.lau.foodify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.lau.foodify.databinding.CartBinding;
import com.lau.foodify.databinding.MainActivityBinding;

public class Cart extends AppCompatActivity {

    double price;
    TextView total_price;
    CartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        total_price = (TextView) findViewById(R.id.price);


        binding = CartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] food = {"Burger","Pizza","Fasoulya","Mjadra",
                "Cesear salad","Chicken parm","Pasta","Nutella","Crepe","Fish"};
        int[] flowerImages = {R.drawable.pizza,R.drawable.burger,R.drawable.pizza,R.drawable.burger,R.drawable.pizza,R.drawable.burger,
                R.drawable.pizza,R.drawable.burger,R.drawable.pizza,R.drawable.burger,};
        String[] weight = {"200 g", "10 Kg","1 Jar","10 pieces","1 can","100 g","10 boxes","100 g","10 min","10 min"};
        String[] location = {"","","","","","","","","",""};
        String[] date = {"Price: 10,000 LBP", "Price: 50,000 LBP", "Price: 10,000 LBP", "Price: 50,000 LBP","Price: 10,000 LBP", "Price: 50,000 LBP","Price: 10,000 LBP", "Price: 50,000 LBP","Price: 10,000 LBP", "Price: 50,000 LBP"};

        GridAdapterPantry cart = new GridAdapterPantry(this,food,weight,location,date,flowerImages);
        binding.shoppingList.setAdapter(cart);

    }
}