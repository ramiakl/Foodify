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

    EditText email_entry, password_entry;
    String name , password, url;
    TextView error_password, error_email;
    Boolean exist;
    PostRequest post;

    public class PostRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            //The method take String parameters and send data to the received url.

            //Storing data in String objects
            String name = params[0];
            String password = params[1];
            String str_url = params[2];
            String result = "";

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

                Log.i("String", s);
                String[] second_part = s.split(":");
                String status = second_part[1];
                Log.i("status", "" +status.equalsIgnoreCase("\"YES\"}"));
                if (status.equalsIgnoreCase("\"YES\"}")) {
                    exist = true;
                    Log.i("exist:", " "+ exist);
                }

            }catch(Exception e){
                    Log.i("exeOnPost", e.getMessage());
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
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

        exist = false;
    }

    public void login (View view){

        name = email_entry.getText().toString();
        password = password_entry.getText().toString();
        url = "http://192.168.0.102/MobileFinalProject/BackEnd/login.php";
        post = new PostRequest();
        post.execute(name,password,url);

        if(name.isEmpty() && password.isEmpty()){
            //error_password.setVisibility(View.VISIBLE);
            //error_email.setVisibility(View.VISIBLE);
            Toast.makeText(this,"Please enter the password and the email correctly",Toast.LENGTH_LONG).show();
        }
        else if (name.equalsIgnoreCase("")){
            //error_email.setVisibility(View.VISIBLE);
            Toast.makeText(this,"Please enter the email correctly",Toast.LENGTH_LONG).show();
        }
        else if(password.isEmpty()){
            //error_password.setVisibility(View.VISIBLE);
            Toast.makeText(this,"Please enter the password correctly",Toast.LENGTH_LONG).show();
        }
        else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(exist){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this,"User does not exist please Sign up",Toast.LENGTH_LONG).show();
            }

        }
    }

    public void signup(View view){
        Intent intent = new Intent(getApplicationContext(), Signup.class);
        startActivity(intent);
    }
}