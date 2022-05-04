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

public class AddToCart extends AppCompatActivity {
//This class adds an item to the shopping cart

    EditText name, weight, price_txt;
    String iname,wei,price,url, user_id;
    Intent intent;
    String ip =  "192.168.0.101";
    //String ip = "192.168.16.103";
    SharedPreferences shared;

    public class PostRequest extends AsyncTask<String, Void, String> {
        // Post data to the cart table in the database
        @Override
        protected String doInBackground(String... params) {
            //The method take String parameters and send data to the received url.

            //Storing data in String objects
            String name = params[0];
            String weight = params[1];
            String price = params[2];
            String str_url = params[3];

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
                        +URLEncoder.encode("price", "UTF-8")+"="+URLEncoder.encode(price, "UTF-8")+"&"
                        +URLEncoder.encode("user_id", "UTF-8")+"="+URLEncoder.encode(user_id, "UTF-8");


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
        setContentView(R.layout.add_to_cart);

        shared = this.getSharedPreferences("com.lau.foodify", Context.MODE_PRIVATE);
        user_id = shared.getString("user_id",user_id);// getting the id of the logged in user to add it in the cart table

        name = (EditText) findViewById(R.id.name_sign);
        weight = (EditText) findViewById(R.id.w_text);
        price_txt = (EditText) findViewById(R.id.price_txt);

    }

    public void addCart(View view){
        // redirect to the add to cart api

        url ="http://"+ip+"/MobileFinalProject/BackEnd/insert_to_cart.php";

        iname = name.getText().toString();
        wei = weight.getText().toString();
        price = price_txt.getText().toString();

        if(iname.isEmpty() || wei.isEmpty() || price.isEmpty()){
            Toast.makeText(this,"Please fill all entries",Toast.LENGTH_SHORT).show();
        }else {
            PostRequest post = new PostRequest();
            post.execute(iname, wei, price, url);

            intent = new Intent(getApplicationContext(), Cart.class);
            startActivity(intent);
        }

    }
}