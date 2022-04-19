package com.lau.foodify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.lau.foodify.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class cookbook extends AppCompatActivity {

    ActivityMainBinding binding;
    String[] name, time;
    GridAdapter gridAdapter;
    String url;

    public class DownloadTask extends AsyncTask<String, Void, String> {
        // This class contains methods that enable url connection to an API to retrieve data stored in it.

        protected String doInBackground(String... urls) {
            //The method takes String parameter and gets a required data from an external URL API.
            String result = "";
            URL url;
            HttpURLConnection http; //Initializing the url connection object

            try {
                url = new URL(urls[0]);
                http = (HttpURLConnection) url.openConnection(); //Declaring the Url connection object

                InputStream inputStream = http.getInputStream(); //initializing InputStream Object to pass data.

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)); //Initializing BufferedReader Object to Read data.
                String line = reader.readLine(); //Get the data ad store it in a String.

                while (line != null) {
                    result += line;
                    line = reader.readLine(); //Concatenate each line
                }

            } catch (Exception e) {
                Log.i("exeDOin", e.getMessage());
                return null;
            }
            return result;
        }

        protected void onPostExecute(String s) {
            // This method converts the JSON Object received into a String.
            super.onPostExecute(s);
            try{

                Log.i("String", s);
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

                JSONObject obj;
                for(int i=0; i<listdata.size();i++){
                    obj = (JSONObject) jsonArray.get(i);
                    name[i] = obj.getString("Recipe_name");
                    time[i] = obj.getString("cook_time");
                }


                int[] flowerImages = {R.drawable.pizza,R.drawable.burger,R.drawable.pizza};

                gridAdapter = new GridAdapter(getApplicationContext(),name,time,flowerImages);
                binding.gridView.setAdapter(gridAdapter);

            }catch(Exception e){
                Log.i("exeOnPost",e.getMessage());
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        url = "http://192.168.0.102/MobileFinalProject/BackEnd/get_from_cookbook.php";
        DownloadTask task = new DownloadTask();
        task.execute(url);


    }
}