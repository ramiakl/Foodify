package com.lau.foodify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import com.lau.foodify.databinding.MainActivityBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    EditText search;
    ImageView search_icon;
    String url;
    String[] food,weight,location,date;

    MainActivityBinding binding;

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
                JSONObject first = (JSONObject) jsonArray.get(1);
                String name = first.getString("item_name");
                Log.i("Name",name);
                //Checking whether the JSON array has some value or not
                if (jsonArray != null) {

                    //Iterating JSON array
                    for (int i=0;i<jsonArray.length();i++){

                        //Adding each element of JSON array into ArrayList
                        listdata.add(jsonArray.get(i));
                    }
                }
                //Iterating ArrayList to print each element

                for(int i=0; i<listdata.size(); i++) {
                    //Printing each element of ArrayList
                    Log.i("Data", listdata.get(i).toString());
                }

                food = new String[jsonArray.length()];
                weight = new String[jsonArray.length()];
                location = new String[jsonArray.length()];
                date = new String[jsonArray.length()];

                for(int i=0; i<listdata.size();i++){
                    first = (JSONObject) jsonArray.get(i);
                    food[i] = first.getString("item_name");
                    weight[i] = first.getString("Weight");
                    location[i]= first.getString("location");
                    date[i]= first.getString("date_of_expiration");
                }
                Log.i("Result", Arrays.toString(food));

                int[] flowerImages = {R.drawable.pizza,R.drawable.burger,R.drawable.pizza};

                GridAdapterPantry gridAdapter = new GridAdapterPantry(getApplicationContext(),food,weight,location,date,flowerImages);
                binding.list.setAdapter(gridAdapter);

        }catch(Exception e){
                Log.i("exeOnPost",e.getMessage());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        url = "http://192.168.0.102/MobileFinalProject/BackEnd/get_from_pantry.php";
        DownloadTask task = new DownloadTask();
        task.execute(url);

        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        search = (EditText) findViewById(R.id.search_tab);
        search_icon = (ImageView) findViewById(R.id.search_icon);

    }

    public void tocookbook(View view){
        intent = new Intent(getApplicationContext(), cookbook.class);
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
    public void toprofile(View view){
        intent = new Intent(getApplicationContext(), Calendar.class);
        startActivity(intent);
    }

    public void search (View view){

        String item = search.getText().toString();

        // connect to api
        // reset adapter
    }

}