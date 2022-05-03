package com.lau.foodify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.lau.foodify.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

public class Cookbook extends AppCompatActivity {
// This class displays the recipes available in the database

    ActivityMainBinding binding;
    String[] name, time;
    GridAdapter gridAdapter;
    String url;
    Intent intent;
    String chosen_recipe, user_id;
    int[] images;
    String ip =  "192.168.0.101";

    SharedPreferences shared;

    public class DownloadTask extends AsyncTask<String, Void, String> {
        // This class contains methods that enable url connection to an API to retrieve data stored in it.

        protected String doInBackground(String... urls) {
            //The method takes String parameter and gets a required data from an external URL API.
            String result = "";
            URL url;
            HttpURLConnection http; //Initializing the url connection object

            try {
                // Creating a new URL connection with PHP.
                url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                OutputStream out = urlConnection.getOutputStream(); //Initializing OutputStream Object.

                BufferedWriter br = new BufferedWriter(new OutputStreamWriter(out, "UTF-8")); //Initializing BufferedWriter Object

                // Setting the variables to be sent to the URL
                String post_data = URLEncoder.encode("user_id", "UTF-8")+"="+URLEncoder.encode(user_id, "UTF-8");

                br.write(post_data); //Writing and sending data.
                br.flush();
                br.close();
                out.close();

                InputStream is = urlConnection.getInputStream();

                urlConnection.disconnect();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is)); //Initializing BufferedReader Object to Read data.
                String line = reader.readLine(); //Get the data ad store it in a String.

                while (line != null) {
                    result += line;
                    line = reader.readLine(); //Concatenate each line
                }

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return result;
        }

        protected void onPostExecute(String s) {
            // This method converts the JSON Object received into a String.
            super.onPostExecute(s);
            try{

                JSONArray jsonArray = new JSONArray(s);

                ArrayList<Object> listdata = new ArrayList<Object>();

                //Checking whether the JSON array has some value or not
                if (jsonArray != null) {
                    //Iterating JSON array
                    for (int i=0;i<jsonArray.length();i++){
                        //Adding each element of JSON array into ArrayList
                        listdata.add(jsonArray.get(i));
                    }
                }

                name = new String[jsonArray.length()];
                time = new String[jsonArray.length()];
                images = new int[jsonArray.length()];

                JSONObject obj;
                for(int i=0; i<listdata.size();i++){
                    obj = (JSONObject) jsonArray.get(i);
                    name[i] = obj.getString("Recipe_name");
                    time[i] = obj.getString("cooktime");
                    images[i] = Integer.parseInt(obj.getString("image"));
                }

                gridAdapter = new GridAdapter(getApplicationContext(),name,time,images);
                binding.gridView.setAdapter(gridAdapter);

                binding.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {// if the item is clicked we display the selected recipe
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        chosen_recipe = gridAdapter.getItem(i);
                        Intent intent = new Intent(getApplicationContext(), Receipe.class);
                        intent.putExtra("Chosen",chosen_recipe);
                        startActivity(intent);
                    }
                });

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shared = this.getSharedPreferences("com.lau.foodify", Context.MODE_PRIVATE);
        user_id = shared.getString("user_id","");

        url = "http://"+ip+"/MobileFinalProject/BackEnd/get_from_cookbook.php";
        DownloadTask task = new DownloadTask();
        task.execute(url);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    public void topantry(View view){
        intent = new Intent(getApplicationContext(), MainActivity.class);
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

}