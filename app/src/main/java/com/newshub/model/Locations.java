package com.newshub.model;

public class Locations {

    private int location_ID;
    private String created_Date;
    private String updated_Date;
    private String state;
    private String city;
    private int pincode;

    public int getLocation_ID() {
        return location_ID;
    }

    public void setUpdated_Date(String updated_Date) {
        this.updated_Date = updated_Date;
    }

    public String getCreated_Date() {
        return created_Date;
    }

    public void setCreated_Date(String created_Date) {
        this.created_Date = created_Date;
    }

    public String getUpdated_Date() {
        return updated_Date;
    }

    public void setLocation_ID(int location_ID) {
        this.location_ID = location_ID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }
}
