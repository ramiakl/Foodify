package com.lau.foodify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView list;
    ArrayList<String> items;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list);


        items = new ArrayList<String>(Arrays.asList("Mobile Computing", "Database Management Systems", "Software Engineering"));

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        list.setAdapter(adapter);

    }
}