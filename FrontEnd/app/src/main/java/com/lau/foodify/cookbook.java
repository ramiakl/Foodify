package com.lau.foodify;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class cookbook extends AppCompatActivity {

    ListView list;
    ArrayList<String> items;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cookbook);

        list = (ListView) findViewById(R.id.list);

        SpannableStringBuilder builder = new SpannableStringBuilder();

        String red = "Expiring in 2 days";
        SpannableString redSpannable = new SpannableString(red);
        redSpannable.setSpan(new ForegroundColorSpan(Color.RED), 0, red.length(), 0);

        items = new ArrayList<String>(Arrays.asList("\nGround beef\t\t\t\t1 kg \n\n" + redSpannable + "\t\t\t\tFridge\n", "\nGround beef\t\t\t\t1 kg \n\nExpiring in 2 days\t\t\t\tFridge\n", "\nGround beef\t\t\t\t1 kg \n\nExpiring in 2 days\t\t\t\tFridge\n", "\nGround beef\t\t\t\t1 kg \n\nExpiring in 2 days\t\t\t\tFridge\n"));

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        list.setAdapter(adapter);
    }
}