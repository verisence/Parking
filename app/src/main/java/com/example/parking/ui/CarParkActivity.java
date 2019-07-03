package com.example.parking.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parking.adapters.CarParksArrayAdapter;
import com.example.parking.R;

public class CarParkActivity extends AppCompatActivity {

    private TextView mWelcomeText;
    private TextView mParkText;
    private ListView mParkListVIew;
    private String[] parkName = {"Egeza", "Moiana Road", "Pochinki Park", "Alpha", "Erangel", "Brass Park", "Terran", "Sky Scale", "Deep House", "Tango", "Foxtrot", "Knox", "Villa", "Delta", "Downtown", "CBD", "Roadside"};
    private Integer[] capacity = {22,99,4,33,222,0,4,5,66,7,99,23,14,67,89,23,123};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carpark);

        mWelcomeText = (TextView) findViewById(R.id.welcomeText);
        mParkText = (TextView) findViewById(R.id.parkText);
        mParkListVIew = (ListView) findViewById(R.id.parkListView);

        Typeface proximaLight = Typeface.createFromAsset(getAssets(), "fonts/light.ttf");
        Typeface proximaMedium = Typeface.createFromAsset(getAssets(), "fonts/med.ttf");
        Typeface proximaBold = Typeface.createFromAsset(getAssets(), "fonts/bold.ttf");

        mWelcomeText.setTypeface(proximaLight);
        mParkText.setTypeface(proximaMedium);

        CarParksArrayAdapter adapter = new CarParksArrayAdapter(CarParkActivity.this, android.R.layout.simple_list_item_1, parkName, capacity);

        mParkListVIew.setAdapter(adapter);

        mParkListVIew.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String carPark = ((TextView)view).getText().toString();
                Toast.makeText(CarParkActivity.this, carPark, Toast.LENGTH_LONG).show();
            }
    });
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        mWelcomeText.setText("You are logged in as " + username);

    }
}
