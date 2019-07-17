package com.newshub.ui_brands;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.newshub.helper.NewsDBHelper;
import com.newshub.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BrandsAddActivity extends AppCompatActivity {

    private EditText brandName, startedDate, retailPrice, customerPrice, offerPercent, offerPrice;
    DatePickerDialog datePicker;
    NewsDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_brands_add);

        brandName = (EditText) findViewById (R.id.brandName);
        startedDate = (EditText) findViewById (R.id.startedDate);
        startedDate.setClickable (true);
        startedDate.setFocusable (false);
        retailPrice = (EditText) findViewById (R.id.retailPrice);
        customerPrice = (EditText) findViewById (R.id.customerPrice);
        offerPercent = (EditText) findViewById (R.id.offerPercent);
        offerPrice = (EditText) findViewById (R.id.offerPrice);

//        DatePicker for Started Date
        startedDate.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance ( );
                int mDay = calendar.get (Calendar.DAY_OF_MONTH);
                int mMonth = calendar.get (Calendar.MONTH);
                int mYear = calendar.get (Calendar.YEAR);
                datePicker = new DatePickerDialog (BrandsAddActivity.this, new DatePickerDialog.OnDateSetListener ( ) {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        startedDate.setText (day + "/" + (month + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePicker.show ( );
            }
        });

        Intent intent = getIntent ( );
        int brandsIndex = intent.getIntExtra ("Brand_ID", 0);
        if (brandsIndex != 0) {
            String brandsNAME = intent.getStringExtra ("Brand_Name");
            String sDate = intent.getStringExtra ("Started_Date");
            float rPrice = intent.getFloatExtra ("Retail_Price", 0);
            float cPrice = intent.getFloatExtra ("Customer_Price", 0);
            int oPercent = intent.getIntExtra ("Offer_Percent", 0);
            float oPrice = intent.getFloatExtra ("Offer_Price", 0);
            brandName.setText (brandsNAME);
            startedDate.setText (sDate);
            retailPrice.setText (String.valueOf (rPrice));
            customerPrice.setText (String.valueOf (cPrice));
            offerPercent.setText (String.valueOf (oPercent));
            offerPrice.setText (String.valueOf (oPrice));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed ( );
        this.finish ( );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ( ).inflate (R.menu.brands_add_menu, menu);
        return super.onCreateOptionsMenu (menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId ( )) {
            case R.id.saveBrand:
                String brand_Name = brandName.getText ( ).toString ( ).trim ( );
                String started_Date = startedDate.getText ( ).toString ( ).trim ( );
                String retail_Price = retailPrice.getText ( ).toString ( ).trim ( );
                String customer_Price = customerPrice.getText ( ).toString ( ).trim ( );
                String offer_Percent = offerPercent.getText ( ).toString ( ).trim ( );
                String offer_Price = offerPrice.getText ( ).toString ( ).trim ( );

                SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/mm/yyyy");
                String date = dateFormat.format (new Date ( ));

                if (brand_Name.length ( ) == 0 && started_Date.length ( ) == 0 && retail_Price.length ( ) == 0 && customer_Price.length ( ) == 0) {
                    Snackbar.make (findViewById (android.R.id.content), "All fields are required", Snackbar.LENGTH_LONG).setAction ("Action", null).show ();
//                    Toast.makeText (getApplicationContext ( ), "All fields are requred", Toast.LENGTH_SHORT).show ( );
                } else {

//                    int starting_date = Integer.valueOf (date);
                    float brand_retail_price = Float.valueOf (retail_Price);
                    float brand_cutomer_price = Float.valueOf (customer_Price);
                    int brand_offer_percent = Integer.valueOf (offer_Percent);
                    float brand_offer_price = Float.valueOf (offer_Price);

                    helper = new NewsDBHelper (this);

                    Intent intentID = getIntent ( );
                    int indexID = intentID.getIntExtra ("Brand_ID", 0);

                    if (indexID == 0) {
                        long isBrandInserted = NewsDBHelper.getInstance (this).insertBrandDetails (brand_Name, started_Date, date, brand_retail_price, brand_cutomer_price, brand_offer_percent, brand_offer_price);
                        if (isBrandInserted >= 0) {
                            Toast.makeText (getApplicationContext ( ), "Saved succesfully", Toast.LENGTH_SHORT).show ( );
                            Log.d ("Brand Details", "Inserted items :: " + brand_Name + "\t" + started_Date + "\t" + retail_Price);
                            Intent intent = new Intent (BrandsAddActivity.this, BrandsViewActivity.class);
                            startActivity (intent);
                            finish ( );
                        } else {
                            Log.d ("Brand Details", "Inserting error :: " + brand_Name + "\t" + started_Date + "\t" + retail_Price);
                        }
                    } else {
                        boolean isUpdated = NewsDBHelper.getInstance (this).updateBrandItem (indexID, brand_Name, started_Date, date, brand_retail_price, brand_cutomer_price, brand_offer_percent, brand_offer_price);
                        if (isUpdated) {
                            Snackbar.make (findViewById (android.R.id.content), "All fields are required", Snackbar.LENGTH_LONG).setAction ("Action", null).show ();
                            Log.d ("Updated", "Updated : " + brand_Name);
                            finish ( );
                        }
                    }
                }
                break;

            case R.id.deleteBrand:
                Intent intent = getIntent ( );
                long brand_ID = intent.getIntExtra ("Brand_ID", 0);
                if (brand_ID != 0) {
                    NewsDBHelper.getInstance (this).deleteBrandsItem (brand_ID);
                    Snackbar.make (findViewById (android.R.id.content), "Item deleted successfully", Snackbar.LENGTH_LONG).setAction ("Action", null).show ();
                    finish ( );
                } else {
                    Toast.makeText (getApplicationContext ( ), "No items found", Toast.LENGTH_SHORT).show ( );
                }
                break;
        }
        return super.onOptionsItemSelected (item);
    }
}
