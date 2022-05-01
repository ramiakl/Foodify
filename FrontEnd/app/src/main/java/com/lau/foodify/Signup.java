package com.lau.foodify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

public class Signup extends AppCompatActivity {
    Intent intent;
    EditText email_entry, password_entry, name_entry;
    String email , password, name, url;
    PostRequestSignUp post;
    String ip =  "192.168.0.101";

    public class PostRequestSignUp extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {
            //The method take String parameters and send data to the received url.

            //Storing data in String objects
            String name = params[0];
            String email = params[1];
            String password = params[2];
            String str_url = params[3];

            Log.i("Entries", name+password+email+str_url);
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
                String post_data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                        + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                Log.i("Data",post_data);
                br.write(post_data); //Writing and sending data.
                br.flush();
                br.close();
                out.close();

                InputStream is = urlConnection.getInputStream();
                intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);

                Log.i("inputStream",is.toString());
                urlConnection.disconnect();

                //Catching exceptions
            } catch (MalformedURLException e) {
                Log.i("exeDOin",e.getMessage());
            } catch (IOException e) {
                Log.i("exeIO",e.getMessage());
            }
            return null;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        email_entry = (EditText) findViewById(R.id.email_sign);
        password_entry = (EditText) findViewById(R.id.password_sign);
        name_entry = (EditText) findViewById(R.id.name_sign);

    }

    public void signup (View view){


        email = email_entry.getText().toString();
        password = password_entry.getText().toString();
        name = name_entry.getText().toString();

        if(email.isEmpty() && password.isEmpty() && name.isEmpty()){

            Toast.makeText(this,"Please enter the password, name and email correctly",Toast.LENGTH_LONG).show();
        }
        else if(email.isEmpty() && password.isEmpty()){

            Toast.makeText(this,"Please enter the password and email correctly",Toast.LENGTH_LONG).show();
        }
        else if(name.isEmpty() && password.isEmpty()){

            Toast.makeText(this,"Please enter the name and password correctly",Toast.LENGTH_LONG).show();
        }
        else if (email.equalsIgnoreCase("")){
            Toast.makeText(this,"Please enter the email correctly",Toast.LENGTH_LONG).show();
        }
        else if(password.isEmpty()){
            Toast.makeText(this,"Please enter the password correctly",Toast.LENGTH_LONG).show();
        }
        else if(name.isEmpty()){
            Toast.makeText(this,"Please enter the name correctly",Toast.LENGTH_LONG).show();
        }
        else {
            url = "http://"+ip+"/MobileFinalProject/BackEnd/signup_api.php";
            post = new PostRequestSignUp(); // Initialize a PostRequest object everytime the user clicks the button.
            post.execute(name,email,password,url);

        }
    }
    public void login (View view){

        intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
    }
}