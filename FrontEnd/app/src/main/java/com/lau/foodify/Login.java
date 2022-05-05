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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

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

public class Login extends AppCompatActivity {
// This class is responsible of logging in the user

    EditText email_entry, password_entry;
    String name , password, url, user_id;
    PostRequest post;
    String result = "";
    //String ip =  "192.168.0.101";
    String ip = "172.20.10.5";

    SharedPreferences shared;

    public class PostRequest extends AsyncTask<String, Void, String> {
        // Post the name and password to the api to check if it is avaialable
        @Override
        protected String doInBackground(String... params) {
            //The method take String parameters and send data to the received url.

            //Storing data in String objects
            String name = params[0];
            String password = params[1];
            String str_url = params[2];

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
                        +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");

                br.write(post_data); //Writing and sending data.
                br.flush();
                br.close();
                out.close();

                InputStream is = urlConnection.getInputStream();

                urlConnection.disconnect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(is)); //Initializing BufferedReader Object to Read data.
                String line = reader.readLine(); //Get the data ad store it in a String.

                while( line  != null){
                    result += line;
                    line = reader.readLine(); //Concatenate each line
                }

                //Catching exceptions
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {

                if (s.equalsIgnoreCase("NO")) {
                    Toast.makeText(getApplicationContext(), "User does not exist please sign up", Toast.LENGTH_SHORT).show();
                }
                else{// go to the next page and save the user id in the local database
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    JSONArray jsonArray = new JSONArray(s);
                    JSONObject obj = (JSONObject) jsonArray.get(0);
                    user_id = obj.getString("user_id");
                    shared.edit().putString("user_id",user_id).commit();
                    startActivity(intent);
                }

            }catch(Exception e){
                    e.printStackTrace();
            }
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email_entry = (EditText) findViewById(R.id.email_log);
        password_entry = (EditText) findViewById(R.id.password_log);

        TextView error_password = (TextView) findViewById(R.id.error_password);
        TextView error_email = (TextView) findViewById(R.id.error_email);

        error_email.setVisibility(View.GONE);
        error_password.setVisibility(View.GONE);

        shared = this.getSharedPreferences("com.lau.foodify", Context.MODE_PRIVATE);
    }

    public void login (View view){
    // when the login is pressed it checks all the inputs

        name = email_entry.getText().toString();
        password = password_entry.getText().toString();


        if(name.isEmpty() && password.isEmpty()){
            Toast.makeText(this,"Please enter the password and the email correctly",Toast.LENGTH_LONG).show();
        }
        else if (name.equalsIgnoreCase("")){
            Toast.makeText(this,"Please enter the email correctly",Toast.LENGTH_LONG).show();
        }
        else if(password.isEmpty()){
            Toast.makeText(this,"Please enter the password correctly",Toast.LENGTH_LONG).show();
        }
        else {
                url = "http://"+ip+"/MobileFinalProject/BackEnd/login.php";
                post = new PostRequest();
                post.execute(name,password,url);
        }
    }

    public void signup(View view){
        Intent intent = new Intent(getApplicationContext(), Signup.class);
        startActivity(intent);
    }
}