package com.example.parking.models;

public class Parking {
    private String name;
    private String vicinity;
//    private double rating;

    public Parking() {
    }

    public Parking(String name, String vicinity) {
        this.name = name;
        this.vicinity = vicinity;
//        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getVicinity() {
        return vicinity;
    }

//    public double getRating() {
//        return rating;
//    }
}
