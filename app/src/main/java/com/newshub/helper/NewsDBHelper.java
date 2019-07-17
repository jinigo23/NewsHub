package com.newshub.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.newshub.model.Brands;
import com.newshub.model.Customer;
import com.newshub.model.CustomerPayments;
import com.newshub.model.Locations;
import com.newshub.model.MyPayments;

import java.util.ArrayList;

public class NewsDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "NewsHub.db";
    private static final int DATABASE_VERSION = 1;

    //    Brands Table
    private static final String BRAND_TABLE = "Brands";
    private static final String BRAND_ID = "Brand_ID";
    private static final String BRAND_NAME = "Brand_Name";
    private static final String BRAND_STARTED_DATE = "Started_Date";
    private static final String BRAND_CREATED_DATE = "Created_Date";
    private static final String BRAND_UPDATED_DATE = "Updated_Date";
    private static final String BRAND_RETAIL_PRICE = "Retail_Price";
    private static final String BRAND_CUSTOMER_PRICE = "Customer_Price";
    private static final String BRAND_OFFER_PERCENT = "Offer_Percent";
    private static final String BRAND_OFFER_PRICE = "Offer_Price";

    //    Customer Table
    private static final String CUSTOMER_TABLE = "Customer";
    private static final String CUSTOMER_ID = "Customer_ID";
    private static final String CUSTOMER_NAME = "Customer_Name";
    private static final String CUSTOMER_PHONE = "Phone";
    private static final String CUSTOMER_JOINED_DATE = "Joined_Date";
    private static final String CUSTOMER_CREATED_DATE = "Created_Date";
    private static final String CUSTOMER_UPDATED_DATE = "Updated_Date";
    private static final String CUSTOMER_LOCATION = "Location";

    //    Location Table
    private static final String LOCATION_TABLE = "Location";
    private static final String LOCATION_ID = "Location_ID";
    private static final String LOCATION_CREATED_DATE = "Created_Date";
    private static final String LOCATION_UPDATED_DATE = "Updated_Date";
    private static final String LOCATION_STATE = "State";
    private static final String LOCATION_CITY = "City";
    private static final String LOCATION_PINCODE = "Pincode";

    //    My Payments Table
    private static final String MY_PAYMENTS_TABLE = "My_Payments";
    private static final String MY_PAYMENTS_ID = "My_Payments_ID";
    private static final String MY_PAYMENTS_BRAND_NAME = "Brand_Name";
    private static final String MY_PAYMENTS_QUANTITY = "Quantity";
    private static final String MY_PAYMENTS_PAID_DATE = "Paid_Date";
    private static final String MY_PAYMENTS_CREATED_DATE = "Created_Date";
    private static final String MY_PAYMENTS_UPDATED_DATE = "Updated_Date";
    private static final String MY_PAYMENTS_AMOUNT = "Amount";

    //    Customer Payments Table
    private static final String CUSTOMER_PAYMENT_TABLE = "Customer_Payments";
    private static final String CUSTOMER_PAYMENT_ID = "Customer_Payment_ID";
    private static final String CUSTOMER_PAYMENT_CUSTOMER_NAME = "Customer_Name";
    private static final String CUSTOMER_PAYMENT_BRAND_NAME = "Brand_Name";
    private static final String CUSTOMER_PAYMENT_QUANTITY = "Quantity";
    private static final String CUSTOMER_PAYMENT_FROM_DATE = "From_Date";
    private static final String CUSTOMER_PAYMENT_TO_DATE = "To_Date";
    private static final String CUSTOMER_PAYMENT_CREATED_DATE = "Created_Date";
    private static final String CUSTOMER_PAYMENT_UPDATED_DATE = "Updated_Date";
    private static final String CUSTOMER_PAYMENT_AMOUNT = "Amount";


    private static final String CREATE_BRAND_TABLE = "CREATE TABLE IF NOT EXISTS " + BRAND_TABLE + "(" + BRAND_ID + " integer primary key autoincrement, " + BRAND_NAME + " TEXT, " + BRAND_STARTED_DATE + " TEXT, " + BRAND_CREATED_DATE + " TEXT, " + BRAND_UPDATED_DATE + " TEXT, " + BRAND_RETAIL_PRICE + " REAL, " + BRAND_CUSTOMER_PRICE + " REAL, " + BRAND_OFFER_PERCENT + " INTEGER, " + BRAND_OFFER_PRICE + " REAL )";
    private static final String CREATE_CUSTOMER_TABLE = "CREATE TABLE IF NOT EXISTS " + CUSTOMER_TABLE + "(" + CUSTOMER_ID + " integer primary key autoincrement, " + CUSTOMER_NAME + " TEXT, " + CUSTOMER_PHONE + " REAL, " + CUSTOMER_JOINED_DATE + " TEXT, " + CUSTOMER_CREATED_DATE + " TEXT, " + CUSTOMER_UPDATED_DATE + " TEXT, " + CUSTOMER_LOCATION + " TEXT)";
    private static final String CREATE_LOCATION_TABLE = "CREATE TABLE IF NOT EXISTS " + LOCATION_TABLE + "(" + LOCATION_ID + " integer primary key autoincrement, " + LOCATION_CREATED_DATE + " TEXT, " + LOCATION_UPDATED_DATE + " TEXT, " + LOCATION_STATE + " TEXT, " + LOCATION_CITY + " TEXT, " + LOCATION_PINCODE + " INTEGER)";
    private static final String CREATE_MY_PAYMENTS_TABLE = "CREATE TABLE IF NOT EXISTS " + MY_PAYMENTS_TABLE + "(" + MY_PAYMENTS_ID + " integer primary key autoincrement, " + MY_PAYMENTS_BRAND_NAME + " TEXT, " + MY_PAYMENTS_QUANTITY + " TEXT, " + MY_PAYMENTS_PAID_DATE + " TEXT, " + MY_PAYMENTS_CREATED_DATE + " TEXT, " + MY_PAYMENTS_UPDATED_DATE + " TEXT, " + MY_PAYMENTS_AMOUNT + " TEXT)";
    private static final String CREATE_CUSTOMER_PAYMENTS_TABLE = "CREATE TABLE IF NOT EXISTS " + CUSTOMER_PAYMENT_TABLE + "(" + CUSTOMER_PAYMENT_ID + " integer primary key autoincrement, " + CUSTOMER_PAYMENT_CUSTOMER_NAME + " TEXT, " + CUSTOMER_PAYMENT_BRAND_NAME + " TEXT, " + CUSTOMER_PAYMENT_QUANTITY + " TEXT, " + CUSTOMER_PAYMENT_FROM_DATE + " TEXT, " + CUSTOMER_PAYMENT_TO_DATE + " TEXT, " + CUSTOMER_PAYMENT_CREATED_DATE + " TEXT, " + CUSTOMER_PAYMENT_UPDATED_DATE + " TEXT, " + CUSTOMER_PAYMENT_AMOUNT + " TEXT)";

    private static NewsDBHelper instanceHelper = null;

    public NewsDBHelper(@Nullable Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static NewsDBHelper getInstance(Context context) {
        if (instanceHelper == null) {
            instanceHelper = new NewsDBHelper (context);
        }
        return instanceHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL (CREATE_BRAND_TABLE);
        Log.d ("Table created", "Created \t" + CREATE_BRAND_TABLE);
        db.execSQL (CREATE_CUSTOMER_TABLE);
        db.execSQL (CREATE_LOCATION_TABLE);
        db.execSQL (CREATE_MY_PAYMENTS_TABLE);
        db.execSQL (CREATE_CUSTOMER_PAYMENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL ("DROP TABLE IF EXISTS " + BRAND_TABLE);
        db.execSQL ("DROP TABLE IF EXISTS " + CUSTOMER_TABLE);
        db.execSQL ("DROP TABLE IF EXISTS " + LOCATION_TABLE);
        db.execSQL ("DROP TABLE IF EXISTS " + MY_PAYMENTS_TABLE);
        onCreate (db);
    }

    //    Insert Brands Table
    public long insertBrandDetails(String brand_Name, String started_date, String created_date, float retail_price, float customer_price, int offer_percent, float offer_price) {
        SQLiteDatabase db = this.getWritableDatabase ( );
        ContentValues values = new ContentValues ( );
        values.put (BRAND_NAME, brand_Name);
        values.put (BRAND_STARTED_DATE, started_date);
        values.put (BRAND_CREATED_DATE, created_date);
        values.put (BRAND_RETAIL_PRICE, retail_price);
        values.put (BRAND_CUSTOMER_PRICE, customer_price);
        values.put (BRAND_OFFER_PERCENT, offer_percent);
        values.put (BRAND_OFFER_PRICE, offer_price);
        long id = db.insert (BRAND_TABLE, null, values);
        return id;
    }

    //        Get Brands Table List
    public ArrayList<Brands> getBrandDetails() {
        ArrayList<Brands> brandsArrayList = new ArrayList<Brands> ( );
        SQLiteDatabase db = this.getReadableDatabase ( );
//        String query = "SELECT * FROM " + BRAND_TABLE;
        Cursor cursor = db.rawQuery ("SELECT * FROM " + BRAND_TABLE, null);

        while (cursor.moveToNext ( )) {
            Brands brands = new Brands ( );
            brands.setBrand_index (cursor.getInt (cursor.getColumnIndex (BRAND_ID)));
            brands.setBrand_Name (cursor.getString (cursor.getColumnIndex (BRAND_NAME)));
            brands.setStarted_Date (cursor.getString (cursor.getColumnIndex (BRAND_STARTED_DATE)));
            brands.setCreated_Date (cursor.getString (cursor.getColumnIndex (BRAND_CREATED_DATE)));
            brands.setRetail_Price (cursor.getFloat (cursor.getColumnIndex (BRAND_RETAIL_PRICE)));
            brands.setCustomer_Price (cursor.getFloat (cursor.getColumnIndex (BRAND_CUSTOMER_PRICE)));
            brands.setOffer_percent (cursor.getInt (cursor.getColumnIndex (BRAND_OFFER_PERCENT)));
            brands.setOffer_Price (cursor.getFloat (cursor.getColumnIndex (BRAND_OFFER_PRICE)));
            brandsArrayList.add (brands);
        }
        return brandsArrayList;
    }

    //    Update Brands Table List
    public boolean updateBrandItem(int indexId, String brand_Name, String started_date, String updatedDate, float retail_price, float customer_price, int offer_percent, float offer_price) {
        SQLiteDatabase db = this.getWritableDatabase ( );
        ContentValues values = new ContentValues ( );
        Brands brands = new Brands ( );
        values.put (BRAND_NAME, brand_Name);
        values.put (BRAND_STARTED_DATE, started_date);
        values.put (BRAND_UPDATED_DATE, updatedDate);
        values.put (BRAND_RETAIL_PRICE, retail_price);
        values.put (BRAND_CUSTOMER_PRICE, customer_price);
        values.put (BRAND_OFFER_PERCENT, offer_percent);
        values.put (BRAND_OFFER_PRICE, offer_price);
        db.update (BRAND_TABLE, values, BRAND_ID + "=?", new String[]{Long.toString (indexId)});
        return true;
    }

    //    Delete Brands Table List
    public void deleteBrandsItem(long indexID) {
        SQLiteDatabase db = this.getWritableDatabase ( );
        db.delete (BRAND_TABLE, BRAND_ID + "=?", new String[]{Long.toString (indexID)});
    }

    //    Insert Customer Table
    public long insertCustomerDetails(String name, long phone, String joined_date, String created_date, String location) {
        SQLiteDatabase db = this.getWritableDatabase ( );
        ContentValues values = new ContentValues ( );
        values.put (CUSTOMER_NAME, name);
        values.put (CUSTOMER_PHONE, phone);
        values.put (CUSTOMER_JOINED_DATE, joined_date);
        values.put (CUSTOMER_CREATED_DATE, created_date);
        values.put (CUSTOMER_LOCATION, location);
        long indexID = db.insert (CUSTOMER_TABLE, null, values);
        return indexID;
    }

    //    Get Customer Table List
    public ArrayList<Customer> getCustomerDetails() {
        ArrayList<Customer> customerList = new ArrayList<> ( );
        SQLiteDatabase db = this.getReadableDatabase ( );
        String query = "SELECT * FROM " + CUSTOMER_TABLE;
        Cursor cursor = db.rawQuery (query, null);
        while (cursor.moveToNext ( )) {
            Customer customer = new Customer ( );
            customer.setCutomer_ID (cursor.getInt (cursor.getColumnIndex (CUSTOMER_ID)));
            customer.setName (cursor.getString (cursor.getColumnIndex (CUSTOMER_NAME)));
            customer.setPhone (cursor.getInt (cursor.getColumnIndex (CUSTOMER_PHONE)));
            customer.setJoined_Date (cursor.getString (cursor.getColumnIndex (CUSTOMER_JOINED_DATE)));
            customer.setCreated_Date (cursor.getString (cursor.getColumnIndex (CUSTOMER_CREATED_DATE)));
            customer.setUpdated_Date (cursor.getString (cursor.getColumnIndex (CUSTOMER_UPDATED_DATE)));
            customer.setLocation (cursor.getString (cursor.getColumnIndex (CUSTOMER_LOCATION)));
            customerList.add (customer);
        }
        return customerList;
    }

    //    Get Customer Phone number
    public Cursor getPhone(long phone) {
        SQLiteDatabase db = this.getReadableDatabase ( );
        Cursor cursor = db.query (CUSTOMER_TABLE, new String[]{CUSTOMER_ID, CUSTOMER_PHONE}, CUSTOMER_PHONE + " LIKE '?'", new String[]{Long.toString (phone)+"%"}, null, null, null, null);

        /*Cursor cursor = db.query ("SELECT " + CUSTOMER_PHONE + " FROM " + CUSTOMER_TABLE + " WHERE " + CUSTOMER_PHONE + " LIKE '%' ", null);
        if (cursor != null) {
            cursor.moveToFirst ( );
        }*/
        return cursor;
    }

    //    Update Customer Details
    public boolean updateCustomerDetails(int indexId, String customerName, long phone, String joinedDate, String updatedDate, String customerPlace) {
        SQLiteDatabase db = this.getWritableDatabase ( );
        ContentValues values = new ContentValues ( );
        values.put (CUSTOMER_NAME, customerName);
        values.put (CUSTOMER_PHONE, phone);
        values.put (CUSTOMER_JOINED_DATE, joinedDate);
        values.put (CUSTOMER_UPDATED_DATE, updatedDate);
        values.put (CUSTOMER_LOCATION, customerPlace);
        db.update (CUSTOMER_TABLE, values, CUSTOMER_ID + "=?", new String[]{String.valueOf (indexId)});
        return true;
    }

    //    Delete Customer Table List
    public void deleteCustomer(long indexID) {
        SQLiteDatabase db = this.getWritableDatabase ( );
        db.delete (CUSTOMER_TABLE, CUSTOMER_ID + "=?", new String[]{String.valueOf (indexID)});
    }

    //    Insert Location Table
    public long insertLocationDetails(String created_date, String state, String city, int pincode) {
        SQLiteDatabase db = this.getWritableDatabase ( );
        ContentValues values = new ContentValues ( );
        values.put (LOCATION_CREATED_DATE, created_date);
        values.put (LOCATION_STATE, state);
        values.put (LOCATION_CITY, city);
        values.put (LOCATION_PINCODE, pincode);
        long id = db.insert (LOCATION_TABLE, null, values);
        return id;
    }

    //    Get Location Table List
    public ArrayList<Locations> getLocationDetails() {
        ArrayList<Locations> locationsList = new ArrayList<Locations> ( );
        SQLiteDatabase db = this.getReadableDatabase ( );
        String query = "SELECT * FROM " + LOCATION_TABLE;
        Cursor cursor = db.rawQuery (query, null);
        while (cursor.moveToNext ( )) {
            Locations locations = new Locations ( );
            locations.setLocation_ID (cursor.getInt (cursor.getColumnIndex (LOCATION_ID)));
            locations.setCreated_Date (cursor.getString (cursor.getColumnIndex (LOCATION_CREATED_DATE)));
            locations.setUpdated_Date (cursor.getString (cursor.getColumnIndex (LOCATION_UPDATED_DATE)));
            locations.setState (cursor.getString (cursor.getColumnIndex (LOCATION_STATE)));
            locations.setCity (cursor.getString (cursor.getColumnIndex (LOCATION_CITY)));
            locations.setPincode (cursor.getInt (cursor.getColumnIndex (LOCATION_PINCODE)));
            locationsList.add (locations);
        }
        return locationsList;
    }

    //    Update Location Details
    public boolean updateLocation(int indexID, String updatedDate, String state, String city, int pincode) {
        SQLiteDatabase db = this.getWritableDatabase ( );
        ContentValues values = new ContentValues ( );
        values.put (LOCATION_UPDATED_DATE, updatedDate);
        values.put (LOCATION_STATE, state);
        values.put (LOCATION_CITY, city);
        values.put (LOCATION_PINCODE, pincode);
        db.update (LOCATION_TABLE, values, LOCATION_ID + "=?", new String[]{String.valueOf (indexID)});
        return true;
    }

    //    Delete Location Details
    public void deleteLocation(long indexID) {
        SQLiteDatabase db = this.getWritableDatabase ( );
        db.delete (LOCATION_TABLE, LOCATION_ID + "=?", new String[]{String.valueOf (indexID)});
    }

    //    Insert My Payments Table
    public long insertMyPayments(String brand_name, String quantity, String paid_date, String created_date, float amount) {
        SQLiteDatabase db = this.getWritableDatabase ( );
        ContentValues values = new ContentValues ( );
        values.put (MY_PAYMENTS_BRAND_NAME, brand_name);
        values.put (MY_PAYMENTS_QUANTITY, quantity);
        values.put (MY_PAYMENTS_PAID_DATE, paid_date);
        values.put (MY_PAYMENTS_CREATED_DATE, created_date);
        values.put (MY_PAYMENTS_AMOUNT, amount);
        long myID = db.insert (MY_PAYMENTS_TABLE, null, values);
        return myID;
    }

    //        Get My Payments Table List
    public ArrayList<MyPayments> getMyPaymentDetails() {
        ArrayList<MyPayments> myPaymentsArrayList = new ArrayList<> ( );
        SQLiteDatabase db = this.getReadableDatabase ( );
        String query = "SELECT * FROM " + MY_PAYMENTS_TABLE;
        Cursor cursor = db.rawQuery (query, null);
        while (cursor.moveToNext ( )) {
            MyPayments myPayments = new MyPayments ( );
            myPayments.setIndex_no (cursor.getInt (cursor.getColumnIndex (MY_PAYMENTS_ID)));
            myPayments.setBrand_Name (cursor.getString (cursor.getColumnIndex (MY_PAYMENTS_BRAND_NAME)));
            myPayments.setQuantity (cursor.getInt (cursor.getColumnIndex (MY_PAYMENTS_QUANTITY)));
            myPayments.setPaid_Date (cursor.getString (cursor.getColumnIndex (MY_PAYMENTS_PAID_DATE)));
            myPayments.setCreated_Date (cursor.getString (cursor.getColumnIndex (MY_PAYMENTS_CREATED_DATE)));
            myPayments.setUpdated_Date (cursor.getString (cursor.getColumnIndex (MY_PAYMENTS_UPDATED_DATE)));
            myPayments.setAmount (cursor.getFloat (cursor.getColumnIndex (MY_PAYMENTS_AMOUNT)));
            myPaymentsArrayList.add (myPayments);
        }
        return myPaymentsArrayList;
    }
    //    Update My Payments Table List

    public boolean updateMyPayments(int indexID, String brand_name, int quantity, String paid_date, String updated_date, float amount) {
        SQLiteDatabase db = this.getWritableDatabase ( );
        ContentValues values = new ContentValues ( );
        values.put (MY_PAYMENTS_BRAND_NAME, brand_name);
        values.put (MY_PAYMENTS_QUANTITY, quantity);
        values.put (MY_PAYMENTS_PAID_DATE, paid_date);
        values.put (MY_PAYMENTS_UPDATED_DATE, updated_date);
        values.put (MY_PAYMENTS_AMOUNT, amount);
        db.update (MY_PAYMENTS_TABLE, values, MY_PAYMENTS_ID + "=?", new String[]{String.valueOf (indexID)});
        return true;
    }

    //    Delete My Payments Table List
    public void deleteMyPayments(long indexID) {
        SQLiteDatabase db = this.getWritableDatabase ( );
        db.delete (MY_PAYMENTS_TABLE, MY_PAYMENTS_ID + "=?", new String[]{String.valueOf (indexID)});
    }

    //    Insert Customer Payments Table
    public long insertCustomerPayments(String customer_name, String brand_name, int quantity, String from_date, String to_date, String created_date, float amount) {
        SQLiteDatabase db = this.getWritableDatabase ( );
        ContentValues values = new ContentValues ( );
        values.put (CUSTOMER_PAYMENT_CUSTOMER_NAME, customer_name);
        values.put (CUSTOMER_PAYMENT_BRAND_NAME, brand_name);
        values.put (CUSTOMER_PAYMENT_QUANTITY, quantity);
        values.put (CUSTOMER_PAYMENT_FROM_DATE, from_date);
        values.put (CUSTOMER_PAYMENT_TO_DATE, to_date);
        values.put (CUSTOMER_PAYMENT_CREATED_DATE, created_date);
        values.put (CUSTOMER_PAYMENT_AMOUNT, amount);
        long id = db.insert (CUSTOMER_PAYMENT_TABLE, null, values);
        return id;
    }

    //    Get Customer Payments Table List
    public ArrayList<CustomerPayments> getCustomerPayments() {
        ArrayList<CustomerPayments> customerPaymentsList = new ArrayList<CustomerPayments> ( );
        SQLiteDatabase db = this.getReadableDatabase ( );
        String query = "SELECT * FROM " + CUSTOMER_PAYMENT_TABLE;
        Cursor cursor = db.rawQuery (query, null);
        while (cursor.moveToNext ( )) {
            CustomerPayments customerPayments = new CustomerPayments ( );
            customerPayments.setCustomer_Payment_ID (cursor.getInt (cursor.getColumnIndex (CUSTOMER_PAYMENT_ID)));
            customerPayments.setCustomer_name (cursor.getString (cursor.getColumnIndex (CUSTOMER_PAYMENT_CUSTOMER_NAME)));
            customerPayments.setBrand_name (cursor.getString (cursor.getColumnIndex (CUSTOMER_PAYMENT_BRAND_NAME)));
            customerPayments.setQuantity (cursor.getInt (cursor.getColumnIndex (CUSTOMER_PAYMENT_QUANTITY)));
            customerPayments.setFrom_date (cursor.getString (cursor.getColumnIndex (CUSTOMER_PAYMENT_FROM_DATE)));
            customerPayments.setTo_date (cursor.getString (cursor.getColumnIndex (CUSTOMER_PAYMENT_TO_DATE)));
            customerPayments.setCreated_date (cursor.getString (cursor.getColumnIndex (CUSTOMER_PAYMENT_CREATED_DATE)));
            customerPayments.setUpdated_date (cursor.getString (cursor.getColumnIndex (CUSTOMER_PAYMENT_UPDATED_DATE)));
            customerPayments.setAmount (cursor.getFloat (cursor.getColumnIndex (CUSTOMER_PAYMENT_AMOUNT)));
            customerPaymentsList.add (customerPayments);
        }
        return customerPaymentsList;
    }

    //    Update Customer Payments Table List
    public boolean updateCutomerPayment(int indexID, String customerName, String brandName, int quantity, String fromDate, String toDate, String updatedDate, float amount) {
        SQLiteDatabase db = this.getWritableDatabase ( );
        ContentValues values = new ContentValues ( );
        values.put (CUSTOMER_PAYMENT_CUSTOMER_NAME, customerName);
        values.put (CUSTOMER_PAYMENT_BRAND_NAME, brandName);
        values.put (CUSTOMER_PAYMENT_QUANTITY, quantity);
        values.put (CUSTOMER_PAYMENT_FROM_DATE, fromDate);
        values.put (CUSTOMER_PAYMENT_TO_DATE, toDate);
        values.put (CUSTOMER_PAYMENT_UPDATED_DATE, updatedDate);
        values.put (CUSTOMER_PAYMENT_AMOUNT, amount);
        db.update (CUSTOMER_PAYMENT_TABLE, values, CUSTOMER_PAYMENT_ID + "=?", new String[]{String.valueOf (indexID)});
        return true;
    }

    //    Delete Customer Payments Table List
    public void deleteCustomerPayment(long indexID) {
        SQLiteDatabase db = this.getWritableDatabase ( );
        db.delete (CUSTOMER_PAYMENT_TABLE, CUSTOMER_PAYMENT_ID + "=?", new String[]{String.valueOf (indexID)});
    }

    //    Fetch Customer Report
    public Cursor getCustomerReport() {
        SQLiteDatabase db = this.getReadableDatabase ( );
//        String query = "Select * From " + CUSTOMER_TABLE + " INNER JOIN " + CUSTOMER_PAYMENT_TABLE + " ON " + CUSTOMER_TABLE + "." + CUSTOMER_ID + "=" + CUSTOMER_PAYMENT_TABLE + "." + CUSTOMER_PAYMENT_ID;
//        Cursor cursor = db.rawQuery (query, null);

        Cursor cursor = db.query (CUSTOMER_TABLE, new String[]{CUSTOMER_NAME, CUSTOMER_JOINED_DATE, CUSTOMER_LOCATION}, CUSTOMER_ID + "=?", null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst ( );
        }
        return cursor;
    }

//    http://instinctcoder.com/android-studio-sqlite-search-searchview-actionbar/

//    Filter Customer Report
//    public
}
