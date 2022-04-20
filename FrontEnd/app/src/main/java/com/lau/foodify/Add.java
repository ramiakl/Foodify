package com.lau.foodify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Add extends AppCompatActivity {

    String name,calories,cooktime,ingredients,instructions,url;
    EditText recipe, kcal, time, ing, inst;


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
            String str_url = params[5];


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
                        +URLEncoder.encode("time", "UTF-8")+"="+URLEncoder.encode(time, "UTF-8");

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

        ingredients = "";
    }

    public void addIng(View view){

        ingredients += "\n"+ing.getText().toString();
        ing.setText("");
        Log.i("Ingredients",ingredients);
    }

    public void addRec(View view){

        url ="http://192.168.0.102/MobileFinalProject/BackEnd/insert_to_cookBook.php";

        name = recipe.getText().toString();
        calories = kcal.getText().toString();
        cooktime = time.getText().toString();
        instructions = inst.getText().toString();

        PostRequest post = new PostRequest();
        post.execute(name,instructions,ingredients,calories,cooktime,url);

    }
}