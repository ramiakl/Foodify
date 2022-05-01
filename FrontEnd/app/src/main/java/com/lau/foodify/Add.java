package com.lau.foodify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class Add extends AppCompatActivity {

    String name,calories,cooktime,ingredients,instructions,url,ip, user_id;
    EditText recipe, kcal, time, ing, inst;
    Intent intent;
    Spinner spinner;
    int image;


    public class PostRequest extends AsyncTask<String, Void, String> {

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
            intent = getIntent();
            user_id = intent.getStringExtra("user_id");

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

                Log.i("String",post_data);

                br.write(post_data); //Writing and sending data.
                br.flush();
                br.close();
                out.close();

                InputStream is = urlConnection.getInputStream();
                Log.i("Stream",is.toString());
                urlConnection.disconnect();

                //Catching exceptions
            } catch (MalformedURLException e) {
                Log.i("exeOnPost",e.getMessage());
            } catch (IOException e) {
                Log.i("exeOnPost",e.getMessage());
            }
            return null;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

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

        //Assigning an adapter and the list as dropdown
        ArrayAdapter<String> my_adapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item, list);
        spinner.setAdapter(my_adapter);

        ingredients = "";
    }

    public void addIng(View view){

        ingredients += "\n"+ing.getText().toString();
        ing.setText("");
        Log.i("Ingredients",ingredients);
    }

    public void addRec(View view){

        url ="http://"+ip+"/MobileFinalProject/BackEnd/insert_to_cookBook.php";

        name = recipe.getText().toString();
        calories = kcal.getText().toString();
        cooktime = time.getText().toString();
        instructions = inst.getText().toString();

        String type = spinner.getSelectedItem().toString();

        switch (type){
            case "Salad": image = R.drawable.salad;

            case "Sandwich": image = R.drawable.sandwich;

            case "Soup": image = R.drawable.soup;

            case "Platter": image = R.drawable.platter;
        }

        if(name.isEmpty() || calories.isEmpty() || cooktime.isEmpty() || instructions.isEmpty()){
            Toast.makeText(this,"Please fill all entries",Toast.LENGTH_SHORT).show();
        }
        else {
            PostRequest post = new PostRequest();
            post.execute(name, instructions, ingredients, calories, cooktime,""+image,url);

            intent = new Intent(getApplicationContext(), Cookbook.class);
            intent.putExtra("user_id",user_id);
            startActivity(intent);
        }

    }
}