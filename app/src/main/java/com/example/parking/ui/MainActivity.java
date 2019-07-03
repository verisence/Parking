package com.example.parking.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.parking.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //declare button and textView
    private Button mHomeGobutton;
    private TextView mHomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHomeGobutton = (Button) findViewById(R.id.homeGoButton);
        mHomeTextView = (TextView) findViewById(R.id.homeTextView);

        Typeface proximaLight = Typeface.createFromAsset(getAssets(), "fonts/light.ttf");
        Typeface proximaMedium = Typeface.createFromAsset(getAssets(), "fonts/med.ttf");
        Typeface proximaBold = Typeface.createFromAsset(getAssets(), "fonts/bold.ttf");

        mHomeGobutton.setTypeface(proximaMedium);
        mHomeTextView.setTypeface(proximaBold);


        mHomeGobutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mHomeGobutton){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
