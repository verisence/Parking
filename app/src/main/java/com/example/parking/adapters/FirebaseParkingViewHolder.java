package com.example.parking.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parking.Constants;
import com.example.parking.R;
import com.example.parking.models.Parking;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseParkingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;

    public FirebaseParkingViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindParking(Parking parking){
        ImageView parkingImageView = mView.findViewById(R.id.parkingImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.parkingNameTextView);
        TextView locationTextView = (TextView) mView.findViewById(R.id.locationTextView);


        nameTextView.setText(parking.getName());
        locationTextView.setText(parking.getVicinity());
    }

    @Override
    public void onClick(View v) {
        final ArrayList<Parking> parkings = new ArrayList<>();
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("parkings");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    parkings.add(snapshot.getValue(Parking.class));
                }
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, Parking.class);
                intent.putExtra("position", itemPosition);
                intent.putExtra("parkings", Parcels.wrap(parkings));
                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
