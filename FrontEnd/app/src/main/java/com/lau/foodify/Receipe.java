//Once the user Select a receipe in the cookbook this page will display the results
// of the receipe he selected

package com.lau.foodify;

import androidx.annotation.DoNotInline;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Date;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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

public class Receipe extends AppCompatActivity {
//This class display the receipe that the user chooses

    TextView rec, duration, cal, ingredients, instructions ;
    String name, time, calories,ing ,inst, url, user_id;
    //String ip =  "192.168.0.101";// the ip adress for the api
    String ip = "172.20.10.5";

    SharedPreferences shared;

    public class DownloadTask extends AsyncTask<String, Void, String> {
        // This class contains methods that enable url connection to an API to retrieve data stored in it.

        protected String doInBackground(String... urls) {
            //The method takes String parameter and gets a required data from an external URL API.
            String result = "";
            String name = urls[0];
            String str_url = urls[1];
            HttpURLConnection http; //Initializing the url connection object

            try {
                URL url = new URL(str_url);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                OutputStream out = urlConnection.getOutputStream(); //Initializing OutputStream Object.

                BufferedWriter br = new BufferedWriter(new OutputStreamWriter(out, "UTF-8")); //Initializing BufferedWriter Object

                // Setting the variables to be sent to the URL
                String post_data = URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name, "UTF-8")+"&"
                        +URLEncoder.encode("user_id", "UTF-8")+"="+URLEncoder.encode(user_id, "UTF-8");

                br.write(post_data); //Writing and sending data.
                br.flush();
                br.close();
                out.close();

                InputStream is = urlConnection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(is)); //Initializing BufferedReader Object to Read data.

                String line = reader.readLine(); //Get the data ad store it in a String.
                Log.i("line",line);
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
                JSONObject first = (JSONObject) jsonArray.get(0);// there is only one receipe therefore we take the only one at 0

                name = first.getString("Recipe_name");
                calories = first.getString("calories");
                ing= first.getString("Ingredients");
                time = first.getString("cooktime");
                inst = first.getString("Instructions");

                // displaying the results
                duration.setText(time);
                cal.setText(calories);
                ingredients.setText(ing);
                instructions.setText(inst);

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipe);

        shared = this.getSharedPreferences("com.lau.foodify", Context.MODE_PRIVATE);
        user_id = shared.getString("user_id","");// take the id of the logged in user

        Intent x = getIntent();
        String receipe = x.getStringExtra("Chosen");// take the receipe that was selected in the cookbook

        rec = (TextView) findViewById(R.id.rec_name);
        duration = (TextView) findViewById(R.id.duration);
        cal = (TextView) findViewById(R.id.calo);
        ingredients = (TextView) findViewById(R.id.ingredient);
        instructions = (TextView) findViewById(R.id.instruction);

        rec.setText(receipe);

        url = "http://"+ip+"/MobileFinalProject/BackEnd/get_receipe.php";

        DownloadTask task = new DownloadTask();
        task.execute(receipe,url);

    }

    public void back(View view){
        // return to the cookbook
        Intent intent = new Intent(getApplicationContext(), Cookbook.class);
        startActivity(intent);

    }
}