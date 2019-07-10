package com.example.parking.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parking.Constants;
import com.example.parking.R;
import com.example.parking.SavedParkingListActivity;
import com.example.parking.SavedParkingListActivity_ViewBinding;
import com.example.parking.models.Parking;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class ParkingDetailFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.parkingImageView)
    ImageView mImageLabel;
    @BindView(R.id.parkingNameTextView)
    TextView mNameLabel;
    @BindView(R.id.cuisineTextView)
    TextView mCategoriesLabel;
    @BindView(R.id.websiteTextView)
    TextView mWebsiteLabel;
    @BindView(R.id.addressTextView)
    TextView mAddressLabel;
    @BindView(R.id.saveParkingButton)
    TextView mSaveParkingButton;
    @BindView(R.id.savedParkingButton)
    TextView mSavedParkingButton;

    private Parking mParking;

    public static ParkingDetailFragment newInstance(Parking parking) {
        // Required empty public constructor
        ParkingDetailFragment parkingDetailFragment = new ParkingDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("parking", Parcels.wrap(parking));
        parkingDetailFragment.setArguments(args);
        return parkingDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParking = Parcels.unwrap(getArguments().getParcelable("parking"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parking_detail, container, false);
        ButterKnife.bind(this, view);
        mNameLabel.setText(mParking.getName());
        mAddressLabel.setText(mParking.getVicinity());
        mWebsiteLabel.setOnClickListener(this);
        mSaveParkingButton.setOnClickListener(this);
        mSavedParkingButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        if (v == mSaveParkingButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference parkingRef = FirebaseDatabase
                    .getInstance()
                    .getReference("parkings")
                    .child(uid);

            DatabaseReference pushRef = parkingRef.push();
            String pushId = pushRef.getKey();
            mParking.setPushId(pushId);
            pushRef.setValue(mParking);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }

        if (v==mSavedParkingButton){
            Intent intent = new Intent(getActivity(), SavedParkingListActivity.class);
            startActivity(intent);
        }

    }
}
