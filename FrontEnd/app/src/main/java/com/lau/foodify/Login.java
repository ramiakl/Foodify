package com.lau.foodify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    String email , password;
    TextView error_password, error_email;
    Boolean exist;

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

                if(line.equalsIgnoreCase("Completed")){
                    exist = true;
                }
                //Catching exceptions
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
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

        email = email_entry.getText().toString();
        password = password_entry.getText().toString();

        if(email.isEmpty() && password.isEmpty()){
            //error_password.setVisibility(View.VISIBLE);
            //error_email.setVisibility(View.VISIBLE);
            Toast.makeText(this,"Please enter the password and the email correctly",Toast.LENGTH_LONG).show();
        }
        else if (email.equalsIgnoreCase("")){
            //error_email.setVisibility(View.VISIBLE);
            Toast.makeText(this,"Please enter the email correctly",Toast.LENGTH_LONG).show();
        }
        else if(password.isEmpty()){
            //error_password.setVisibility(View.VISIBLE);
            Toast.makeText(this,"Please enter the password correctly",Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    public void signup(View view){
        Intent intent = new Intent(getApplicationContext(), Signup.class);
        startActivity(intent);
    }
}