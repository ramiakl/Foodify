package com.lau.foodify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class AddToPantry extends AppCompatActivity {

    EditText name, weight, location, doe;
    String iname,loc,wei,time,url, user_id;
    Intent intent;
    String ip =  "192.168.0.101";
    SharedPreferences shared;

    public class PostRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            //The method take String parameters and send data to the received url.

            //Storing data in String objects
            String name = params[0];
            String weight = params[1];
            String location = params[2];
            String doe = params[3];
            String str_url = params[4];

            //intent = getIntent();
            //user_id = intent.getStringExtra("user_id");

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
                        +URLEncoder.encode("weight", "UTF-8")+"="+URLEncoder.encode(weight, "UTF-8")+"&"
                        +URLEncoder.encode("location", "UTF-8")+"="+URLEncoder.encode(location, "UTF-8")+"&"
                        +URLEncoder.encode("doe", "UTF-8")+"="+URLEncoder.encode(doe, "UTF-8")+"&"
                        +URLEncoder.encode("user_id", "UTF-8")+"="+URLEncoder.encode(user_id, "UTF-8");

                Log.i("Post Data",post_data);

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
        setContentView(R.layout.add_to_pantry);

        shared = this.getSharedPreferences("com.lau.foodify", Context.MODE_PRIVATE);
        user_id = shared.getString("user_id","");

        name = (EditText) findViewById(R.id.name_sign);
        weight = (EditText) findViewById(R.id.w_text);
        location = (EditText) findViewById(R.id.location);
        doe = (EditText) findViewById(R.id.doe_txt);

    }

    public void addPantry(View view){

        url ="http://"+ip+"/MobileFinalProject/BackEnd/insert_to_pantry_api.php";

        iname = name.getText().toString();
        wei = weight.getText().toString();
        time = doe.getText().toString();
        loc = location.getText().toString();

        if(iname.isEmpty() || wei.isEmpty() || loc.isEmpty() || time.isEmpty()){
            Toast.makeText(this,"Please fill all entries",Toast.LENGTH_SHORT).show();
        }else {
            PostRequest post = new PostRequest();
            post.execute(iname, wei, loc, time, url);
        }

        intent = new Intent(getApplicationContext(), MainActivity.class);
        //intent.putExtra("user_id",user_id);
        startActivity(intent);
    }
}