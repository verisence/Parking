package com.example.parking.ui;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.parking.adapters.ParkingPagerAdapter;
import com.example.parking.models.Parking;

import com.example.parking.R;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParkingDetailActivity extends AppCompatActivity {

    @BindView(R.id.viewPager) ViewPager mViewPager;
    private ParkingPagerAdapter adapterViewPager;
    ArrayList<Parking> mParkings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_detail);

        ButterKnife.bind(this);

        mParkings = Parcels.unwrap(getIntent().getParcelableExtra("parkings"));

        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new ParkingPagerAdapter(getSupportFragmentManager(), mParkings);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
