package com.example.parking;

import android.content.Context;
import android.widget.ArrayAdapter;

public class CarParksArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mParkName;
    private Integer[] mCapacity;

    public CarParksArrayAdapter(Context mContext, int resource, String[] mParkName, Integer[] mCapacity) {
        super(mContext, resource);

        this.mContext = mContext;
        this.mParkName = mParkName;
        this.mCapacity = mCapacity;

    }

    @Override
    public Object getItem(int position) {
        String parkName = mParkName[position];
        Integer space = mCapacity[position];
        return String.format("%s \nHas %s available spaces", parkName, space);
    }

    @Override
    public int getCount() {
        return mParkName.length;
    }
}
