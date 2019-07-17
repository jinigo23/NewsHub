package com.newshub.model;

public class Customer {

    private int cutomer_ID;
    private String name;
    private long phone;
    private String joined_Date;
    private String created_Date;
    private String updated_Date;
    private String location;

    public int getCutomer_ID() {
        return cutomer_ID;
    }

    public void setCutomer_ID(int cutomer_ID) {
        this.cutomer_ID = cutomer_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getJoined_Date() {
        return joined_Date;
    }

    public void setJoined_Date(String joined_Date) {
        this.joined_Date = joined_Date;
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

    public void setUpdated_Date(String updated_Date) {
        this.updated_Date = updated_Date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
