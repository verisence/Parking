package com.example.parking.services;


import android.util.Log;

import com.example.parking.Constants;
import com.example.parking.ui.MapActivity;
import com.example.parking.models.Parking;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import okhttp3.Callback;

public class MapsService {

    public static LatLng location = MapActivity.getLatLng();

    public static void findParking(Callback callback){
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.PLACES_BASE_URL).newBuilder();
//        urlBuilder.addQueryParameter("location",  Double.toString(location.latitude)+","+Double.toString(location.longitude))
        urlBuilder.addQueryParameter("location",  Double.toString(location.latitude)+","+Double.toString(location.longitude))
                .addQueryParameter("radius", "1000")
                .addQueryParameter("type", "parking")
                .addQueryParameter("key", "AIzaSyD96XfxRqNjkyuEv0LTCczwb-mTe8qCpV8");

        String url = urlBuilder.build().toString();

        Log.d("my tag", "findParking: url"+url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Parking> processResults(Response response){
        ArrayList<Parking> parkings = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            JSONObject placesJSON = new JSONObject(jsonData);
            JSONArray resultsJSON = placesJSON.getJSONArray("results");

            if (response.isSuccessful()){
                for (int i=0; i<resultsJSON.length(); i++){
                    JSONObject parkingJSON = resultsJSON.getJSONObject(i);
                    String name = parkingJSON.getString("name");
                    String vicinity = parkingJSON.getString("vicinity");
//                    double rating = parkingJSON.getDouble("rating");

                    Parking parking= new Parking(name, vicinity);
                    parkings.add(parking);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parkings;
    }
}