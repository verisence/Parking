package com.example.parking.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parking.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    //declare variables
    private Button mLoginButton;
    private EditText mLoginUsername;
    private EditText mLoginPassword;
    private TextView mLoginText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //assign variables
        mLoginButton = (Button) findViewById(R.id.loginButton);
        mLoginUsername = (EditText) findViewById(R.id.loginUsername);
        mLoginPassword = (EditText) findViewById(R.id.loginPassword);
        mLoginText = (TextView) findViewById(R.id.loginText);

        Typeface proximaLight = Typeface.createFromAsset(getAssets(), "fonts/light.ttf");
        Typeface proximaMedium = Typeface.createFromAsset(getAssets(), "fonts/med.ttf");
        Typeface proximaBold = Typeface.createFromAsset(getAssets(), "fonts/bold.ttf");

        mLoginButton.setTypeface(proximaMedium);
        mLoginText.setTypeface(proximaBold);
        mLoginUsername.setTypeface(proximaLight);
        mLoginPassword.setTypeface(proximaLight);


        mLoginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
       if (view == mLoginButton){
           if (mLoginUsername.getText().toString().equals("")||mLoginPassword.getText().toString().equals("")){

               Toast.makeText(LoginActivity.this, "Please fill all the fields first", Toast.LENGTH_LONG).show();

           }else{
               String username = mLoginUsername.getText().toString();
               Intent intent = new Intent(LoginActivity.this, MapsActivity.class);

               intent.putExtra("username", username);
               startActivity(intent);

               Toast.makeText(LoginActivity.this, "Welcome " + username, Toast.LENGTH_LONG).show();
           }

       }
    }
}
