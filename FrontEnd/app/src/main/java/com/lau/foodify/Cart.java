package com.lau.foodify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lau.foodify.databinding.CartBinding;
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

public class Cart extends AppCompatActivity {

    double price;
    TextView total_price;
    CartBinding binding;
    String url;
    String[] food,weight,item_price;
    GridPantryCart gridAdapter;
    Intent intent;

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

                food = new String[jsonArray.length()];
                weight = new String[jsonArray.length()];
                item_price = new String[jsonArray.length()];

                for(int i=0; i<listdata.size();i++){
                    first = (JSONObject) jsonArray.get(i);
                    food[i] = first.getString("item_name");
                    weight[i] = first.getString("weight");
                    item_price[i]= first.getString("price");

                }


                Log.i("Result", Arrays.toString(food));

                int[] flowerImages = {R.drawable.pizza,R.drawable.burger,R.drawable.pizza};

                gridAdapter = new GridPantryCart(getApplicationContext(),food,weight,item_price,flowerImages);
                binding.shoppingList.setAdapter(gridAdapter);

                for(int i=0; i<item_price.length;i++){
                    price += Double.parseDouble(item_price[i]);
                }

                Log.i("price",""+price);
                total_price.setText(""+price);

            }catch(Exception e){
                Log.i("exeOnPost",e.getMessage());
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        url = "http://192.168.0.102/MobileFinalProject/BackEnd/get_from_cart.php";
        DownloadTask task = new DownloadTask();
        task.execute(url);

        binding = CartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    public void tocookbook(View view){
        intent = new Intent(getApplicationContext(), cookbook.class);
        startActivity(intent);
    }
    public void topantry(View view){
        intent = new Intent(getApplicationContext(), MainActivity.class);
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

    public void addCart(View view){

    }
}