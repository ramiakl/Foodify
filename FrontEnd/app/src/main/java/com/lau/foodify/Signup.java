package com.lau.foodify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    Intent intent;
    EditText email_entry, password_entry, name_entry;
    String email , password, name;
    TextView error_password, error_email;

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
            intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }
    }
    public void login (View view){

        intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
    }
}