package com.example.parking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.autofill.Validator;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class CarParkActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mWelcomeText;
    private TextView mParkText;
    private ListView mParkListVIew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carpark);

        mWelcomeText = (TextView) findViewById(R.id.welcomeText);
        mParkText = (TextView) findViewById(R.id.parkText);
        mParkListVIew = (ListView) findViewById(R.id.parkListView);

        mParkListVIew.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        CarParksArrayAdapter
    }
}
