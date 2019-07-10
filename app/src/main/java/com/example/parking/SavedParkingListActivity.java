package com.example.parking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.parking.adapters.FirebaseParkingViewHolder;
import com.example.parking.models.Parking;
import com.example.parking.ui.LoginActivity;
import com.example.parking.ui.ParkingListActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedParkingListActivity extends AppCompatActivity {

    private DatabaseReference parkingsReference;
    private FirebaseRecyclerAdapter<Parking, FirebaseParkingViewHolder> firebaseAdapter;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_list);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        parkingsReference = FirebaseDatabase
                .getInstance()
                .getReference("parkings")
                .child(uid);

        parkingsReference = FirebaseDatabase.getInstance().getReference("parkings");
        setUpFireBaseAdapter();
    }

    private void setUpFireBaseAdapter() {
        FirebaseRecyclerOptions<Parking> options = new FirebaseRecyclerOptions.Builder<Parking>()
                .setQuery(parkingsReference, Parking.class)
                .build();
        firebaseAdapter = new FirebaseRecyclerAdapter<Parking, FirebaseParkingViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseParkingViewHolder holder, int position, @NonNull Parking parking) {
                holder.bindParking(parking);
            }

            @NonNull
            @Override
            public FirebaseParkingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parking_list_item, parent, false);
                return new FirebaseParkingViewHolder(view);
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(firebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (firebaseAdapter != null){
            firebaseAdapter.stopListening();
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
        Intent intent = new Intent(SavedParkingListActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
