package com.example.parking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    //declare variables
    private Button mLoginButton;
    private EditText mLoginUsername;
    private EditText mLoginPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //assign variables
        mLoginButton = (Button) findViewById(R.id.loginButton);
        mLoginUsername = (EditText) findViewById(R.id.loginUsername);
        mLoginPassword = (EditText) findViewById(R.id.loginPassword);

        mLoginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
       if (view == mLoginButton){
           if (mLoginUsername.getText().toString().equals("")||mLoginPassword.getText().toString().equals("")){

               Toast.makeText(LoginActivity.this, "Please fill all the fields first", Toast.LENGTH_LONG).show();

           }else{
               String username = mLoginUsername.getText().toString();
               Intent intent = new Intent(LoginActivity.this, CarParkActivity.class);

               intent.putExtra("username", username);
               startActivity(intent);

               Toast.makeText(LoginActivity.this, "Welcome " + username, Toast.LENGTH_LONG).show();
           }

       }
    }
}
