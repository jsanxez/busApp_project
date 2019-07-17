package com.fragmental7.bussapp;

public class User {

    private String name;
    private String lastname;
    private String destination;
    private int phone;
    private Double latitude;
    private Double longitude;

    public User() {}

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getDestination() {
        return destination;
    }

    public int getPhone() {
        return phone;
    }
    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %d, %.2f, %.2f", name, lastname, destination, phone, latitude, longitude);
    }
}


