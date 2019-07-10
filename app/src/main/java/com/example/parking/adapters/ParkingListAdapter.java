package com.example.parking.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parking.R;
import com.example.parking.models.Parking;
//import com.example.placesproject.ParkingDetailActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ParkingListAdapter extends RecyclerView.Adapter<ParkingListAdapter.ParkingViewHolder> {
    private ArrayList<Parking> mParkings = new ArrayList<>();
    private Context mContext;

    public ParkingListAdapter(Context context, ArrayList<Parking> parkings) {
        mContext = context;
        mParkings = parkings;
    }

    @Override
    public ParkingListAdapter.ParkingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parking_list_item, parent, false);
        ParkingViewHolder viewHolder = new ParkingViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ParkingListAdapter.ParkingViewHolder holder, int position) {
        holder.bindParking(mParkings.get(position));
    }

    @Override
    public int getItemCount() {
        return mParkings.size();
    }

    public class ParkingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.parkingImageView) ImageView mParkingImageView;
        @BindView(R.id.parkingNameTextView) TextView mNameTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.ratingTextView) TextView mRatingTextView;
        private Context mContext;


        public ParkingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindParking(Parking parking) {
//            Picasso.get().load(parking.getImageUrl()).into(mParkingImageView);
            mNameTextView.setText(parking.getName());
            mCategoryTextView.setText(parking.getVicinity());
//            mRatingTextView.setText("Rating: " + parking.getRating() + "/5");
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
//            Intent intent = new Intent(mContext, ParkingDetailActivity.class);
//            intent.putExtra("position", itemPosition);
//            intent.putExtra("parkings", Parcels.wrap(mParkings));
//            mContext.startActivity(intent);
        }
    }
}