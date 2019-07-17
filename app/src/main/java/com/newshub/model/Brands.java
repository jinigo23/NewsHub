package com.newshub.model;

public class Brands {

    private int brand_index;
    private String brand_Name;
    private String started_Date;
    private String created_Date;
    private String updated_Date;
    private float retail_Price;
    private float customer_Price;
    private int offer_percent;
    private float offer_Price;

    public int getBrand_index() {
        return brand_index;
    }

    public void setBrand_index(int brand_index) {
        this.brand_index = brand_index;
    }

    public String getBrand_Name() {
        return brand_Name;
    }

    public void setBrand_Name(String brand_Name) {
        this.brand_Name = brand_Name;
    }

    public String getStarted_Date() {
        return started_Date;
    }

    public void setStarted_Date(String started_Date) {
        this.started_Date = started_Date;
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

    public float getRetail_Price() {
        return retail_Price;
    }

    public void setRetail_Price(float retail_Price) {
        this.retail_Price = retail_Price;
    }

    public float getCustomer_Price() {
        return customer_Price;
    }

    public void setCustomer_Price(float customer_Price) {
        this.customer_Price = customer_Price;
    }

    public int getOffer_percent() {
        return offer_percent;
    }

    public void setOffer_percent(int offer_percent) {
        this.offer_percent = offer_percent;
    }

    public float getOffer_Price() {
        return offer_Price;
    }

    public void setOffer_Price(float offer_Price) {
        this.offer_Price = offer_Price;
    }
}
