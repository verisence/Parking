package com.example.parking.models;
import org.parceler.Parcel;

@Parcel
public class Parking {
    private String name;
    private String vicinity;
    private String pushId;

    public Parking() {
    }

    public Parking(String name, String vicinity) {
        this.name = name;
        this.vicinity = vicinity;
    }

    public String getName() {
        return name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public String getPushId(){
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
