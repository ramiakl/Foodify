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
import android.widget.ImageView;
import android.widget.Toast;

import com.lau.foodify.databinding.MainActivityBinding;

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
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
// This class displays all the items present in the panntry

    Intent intent;
    EditText delete;
    String url, user_id;
    String[] food,weight,location,date;
    GridAdapterPantry gridAdapter;
    String ip =  "192.168.0.101";
    //String ip = "192.168.16.103";

    SharedPreferences shared;

    MainActivityBinding binding;

    public class DownloadTask extends AsyncTask<String, Void, String> {
        // This class contains methods that enable url connection to an API to retrieve data stored in it.

        protected String doInBackground(String... urls) {
            //The method takes String parameter and gets a required data from an external URL API.
            String result = "";
            URL url;
            HttpURLConnection http; //Initializing the url connection object
            user_id = shared.getString("user_id","");

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
                JSONObject first = (JSONObject) jsonArray.get(0);
                String name = first.getString("item_name");

                //Checking whether the JSON array has some value or not
                if (jsonArray != null) {

                    //Iterating JSON array
                    for (int i=0;i<jsonArray.length();i++){

                        //Adding each element of JSON array into ArrayList
                        listdata.add(jsonArray.get(i));
                    }
                }
                //Iterating ArrayList to print each element

                food = new String[jsonArray.length()];
                weight = new String[jsonArray.length()];
                location = new String[jsonArray.length()];
                date = new String[jsonArray.length()];

                for(int i=0; i<listdata.size();i++){
                    first = (JSONObject) jsonArray.get(i);
                    food[i] = first.getString("item_name");
                    weight[i] = first.getString("weight");
                    location[i]= first.getString("location");
                    date[i]= "Exp: " + first.getString("date_of_expiration");
                }

                gridAdapter = new GridAdapterPantry(getApplicationContext(),food,weight,location,date);
                binding.list.setAdapter(gridAdapter);

        }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        shared = this.getSharedPreferences("com.lau.foodify", Context.MODE_PRIVATE);

        url = "http://"+ip+"/MobileFinalProject/BackEnd/get_from_pantry.php";
        DownloadTask task = new DownloadTask();
        task.execute(url);

        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        delete = (EditText) findViewById(R.id.delete_name);
    }

    public void tocookbook(View view){
        intent = new Intent(getApplicationContext(), Cookbook.class);
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

    public void addItem(View vew){
        intent = new Intent(getApplicationContext(), AddToPantry.class);
        startActivity(intent);
    }


    public class PostRequest extends AsyncTask<String, Void, String> {
    // used to delete a selected item

        @Override
        protected String doInBackground(String... params) {
            //The method take String parameters and send data to the received url.

            //Storing data in String objects
            String name = params[0];
            String str_url = params[1];

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
                String post_data = URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name, "UTF-8");
                br.write(post_data); //Writing and sending data.
                br.flush();
                br.close();
                out.close();

                InputStream is = urlConnection.getInputStream();

                urlConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    public void deleteApi(View view){

        String item_delete = delete.getText().toString();

        url = "http://"+ip+"/MobileFinalProject/BackEnd/delete_item_pantry.php";

        PostRequest post = new PostRequest();
        post.execute(item_delete,url);

        Toast.makeText(this,item_delete+" was deleted!",Toast.LENGTH_SHORT).show();

    }


}