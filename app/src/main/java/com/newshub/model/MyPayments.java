package com.newshub.model;

public class MyPayments {

    private int index_no;
    private String brand_Name;
    private int quantity;
    private String paid_Date;
    private String created_Date;
    private String updated_Date;
    private float amount;

    public int getIndex_no() {
        return index_no;
    }

    public void setIndex_no(int index_no) {
        this.index_no = index_no;
    }

    public String getBrand_Name() {
        return brand_Name;
    }

    public void setBrand_Name(String brand_Name) {
        this.brand_Name = brand_Name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPaid_Date() {
        return paid_Date;
    }

    public void setPaid_Date(String paid_Date) {
        this.paid_Date = paid_Date;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

}
