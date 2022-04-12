package com.lau.foodify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText email_entry, password_entry;
    String email , password;
    TextView error_password, error_email;

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