package com.example.parking.ui;

import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.parking.R;
import com.google.firebase.auth.FirebaseAuth;

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
            Intent intent = new Intent(MainActivity.this, MapActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
