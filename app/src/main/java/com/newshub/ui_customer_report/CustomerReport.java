package com.newshub.ui_customer_report;

public class CustomerReport {

    private String cName;
    private String bName;
    private String joinedDate;
    private String location;
    private int quantity;
    private float amount;


    public CustomerReport(String cName, String bName, String joinedDate, String location, int quantity) {
        this.cName = cName;
        this.bName = bName;
        this.joinedDate = joinedDate;
        this.location = location;
        this.quantity = quantity;
//        this.amount = amount;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(String joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
