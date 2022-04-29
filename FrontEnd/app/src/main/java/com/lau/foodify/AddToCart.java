package com.lau.foodify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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

    EditText name, weight, price_txt;
    String iname,wei,price,url,ip, user_id;
    Intent intent;

    public class PostRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            //The method take String parameters and send data to the received url.

            //Storing data in String objects
            String name = params[0];
            String weight = params[1];
            String price = params[2];
            String str_url = params[3];

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
                        +URLEncoder.encode("weight", "UTF-8")+"="+URLEncoder.encode(weight, "UTF-8")+"&"
                        +URLEncoder.encode("price", "UTF-8")+"="+URLEncoder.encode(price, "UTF-8")+"&"
                        +URLEncoder.encode("user_id", "UTF-8")+"="+URLEncoder.encode(user_id, "UTF-8");

                Log.i("String2",post_data);

                br.write(post_data); //Writing and sending data.
                br.flush();
                br.close();
                out.close();

                InputStream is = urlConnection.getInputStream();
                urlConnection.disconnect();
                Log.i("inputStream",is.toString());
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
        setContentView(R.layout.add_to_cart);

        name = (EditText) findViewById(R.id.name_sign);
        weight = (EditText) findViewById(R.id.w_text);
        price_txt = (EditText) findViewById(R.id.price_txt);

    }

    public void addCart(View view){

        url ="http://"+ip+"/MobileFinalProject/BackEnd/insert_to_cart.php";

        iname = name.getText().toString();
        wei = weight.getText().toString();
        price = price_txt.getText().toString();
        Log.i("Abel",iname+wei+price);

       PostRequest post = new PostRequest();
       post.execute(iname,wei,price,url);


       intent = new Intent(getApplicationContext(), Cart.class);
       intent.putExtra("user_id",user_id);
       startActivity(intent);
    }
}