package com.example.parking.ui;

import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parking.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.editLogEmail) EditText editEmail;
    @BindView(R.id.editLogPassW) EditText editPassW;
    @BindView(R.id.LoginButton) Button loginButton;
    @BindView(R.id.noAccount) TextView noAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        Typeface proximaLight = Typeface.createFromAsset(getAssets(), "fonts/light.ttf");
        Typeface proximaMedium = Typeface.createFromAsset(getAssets(), "fonts/med.ttf");
        Typeface proximaBold = Typeface.createFromAsset(getAssets(), "fonts/bold.ttf");

        loginButton.setTypeface(proximaMedium);
        noAccount.setTypeface(proximaBold);
        loginButton.setOnClickListener(this);
        noAccount.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
       if (view == loginButton){

               String username = editEmail.getText().toString();
               Intent intent = new Intent(LoginActivity.this, MapsActivity.class);

               intent.putExtra("username", username);
               startActivity(intent);
       }

       if (view == noAccount){
           Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
           startActivity(intent);
           finish();
       }

    }
}
