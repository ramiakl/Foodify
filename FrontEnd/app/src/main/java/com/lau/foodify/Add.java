package com.lau.foodify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

// Add a recipe to the database

public class Add extends AppCompatActivity {

    String name,calories,cooktime,ingredients,instructions,url, user_id;
    EditText recipe, kcal, time, ing, inst;
    Intent intent;
    Spinner spinner;
    int image;
    //String ip =  "192.168.0.101";
    String ip = "172.20.10.5";

    SharedPreferences shared;


    public class PostRequest extends AsyncTask<String, Void, String> {
        // Post the data of the receipe to add it to the database
        @Override
        protected String doInBackground(String... params) {
            //The method take String parameters and send data to the received url.

            //Storing data in String objects
            String name = params[0];
            String instructions = params[1];
            String ingredients = params[2];
            String calories = params[3];
            String time = params[4];
            String image = params[5];
            String str_url = params[6];

            user_id = shared.getString("user_id", "");// getting the id of the logged in user
            try {
                // Creating a new URL connection with PHP.
                URL url = new URL(str_url);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                OutputStream out = urlConnection.getOutputStream(); //Initializing OutputStream Object.

                BufferedWriter br = new BufferedWriter(new OutputStreamWriter(out, "UTF-8")); //Initializing BufferedWriter Object

                // Setting the variables to be sent to the URL
                String post_data = URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name, "UTF-8")+"&"
                        +URLEncoder.encode("instructions", "UTF-8")+"="+URLEncoder.encode(instructions, "UTF-8")+"&"
                        +URLEncoder.encode("ingredients", "UTF-8")+"="+URLEncoder.encode(ingredients, "UTF-8")+"&"
                        +URLEncoder.encode("calories", "UTF-8")+"="+URLEncoder.encode(calories, "UTF-8")+"&"
                        +URLEncoder.encode("time", "UTF-8")+"="+URLEncoder.encode(time, "UTF-8")+"&"
                        +URLEncoder.encode("user_id", "UTF-8")+"="+URLEncoder.encode(user_id, "UTF-8")+"&"
                        +URLEncoder.encode("image", "UTF-8")+"="+URLEncoder.encode(image, "UTF-8");

                br.write(post_data); //Writing and sending data.
                br.flush();
                br.close();
                out.close();

                InputStream is = urlConnection.getInputStream();
                urlConnection.disconnect();

                //Catching exceptions
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        shared = this.getSharedPreferences("com.lau.foodify", Context.MODE_PRIVATE);

        recipe = (EditText) findViewById(R.id.rec_name);
        kcal = (EditText) findViewById(R.id.calories);
        time = (EditText) findViewById(R.id.cooking_time);
        ing = (EditText) findViewById(R.id.ing);
        inst = (EditText) findViewById(R.id.instru);
        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayList<String> list = new ArrayList<String>();
        list.add("Salad");
        list.add("Soup");
        list.add("Sandwich");
        list.add("Platter");

        //Assigning an adapter and the list as dropdown of the types of receipes for the image in the cookbook
        ArrayAdapter<String> my_adapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item, list);
        spinner.setAdapter(my_adapter);

        ingredients = "";
    }

    public void addIng(View view){
        // Concatinating the ingredients

        ingredients += "\n"+ing.getText().toString();
        ing.setText("");
    }

    public void addRec(View view){
        // redirect to the api that post in the cookbook

        url ="http://"+ip+"/MobileFinalProject/BackEnd/insert_to_cookBook.php";

        name = recipe.getText().toString();
        calories = kcal.getText().toString();
        cooktime = time.getText().toString();
        instructions = inst.getText().toString();

        String type = spinner.getSelectedItem().toString();

        switch (type){//based on the type of the recipe we display an image

            case "Salad":
                image = R.drawable.salad;
                break;

            case "Sandwich":
                image = R.drawable.sandwich;
                break;

            case "Soup":
                image = R.drawable.soup;
                break;

            case "Platter":
                image = R.drawable.platter;
                break;
        }


        if(name.isEmpty() || calories.isEmpty() || cooktime.isEmpty() || instructions.isEmpty()){
            Toast.makeText(this,"Please fill all entries",Toast.LENGTH_SHORT).show();
        }
        else {
            PostRequest post = new PostRequest();
            post.execute(name, instructions, ingredients, calories, cooktime,""+image,url);

            intent = new Intent(getApplicationContext(), Cookbook.class);
            startActivity(intent);
        }

    }
}