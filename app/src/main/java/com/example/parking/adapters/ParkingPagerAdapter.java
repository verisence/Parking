package com.example.parking.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.parking.models.Parking;
import com.example.parking.ui.ParkingDetailFragment;

import java.util.ArrayList;

public class ParkingPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Parking> mParkings;

    public ParkingPagerAdapter(FragmentManager fm, ArrayList<Parking> parkings){
        super(fm);
        mParkings = parkings;
    }

    @Override
    public Fragment getItem(int position){
        return ParkingDetailFragment.newInstance(mParkings.get(position));
    }

    @Override
    public int getCount(){
        return mParkings.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mParkings.get(position).getName();
    }
}