package com.newshub.ui_customer;

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

import com.newshub.helper.NewsDBHelper;
import com.newshub.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomerAddActivity extends AppCompatActivity {

    private EditText customer_Name, phone, joined_Date, customer_Place;
    DatePickerDialog datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_customer_add);

        customer_Name = (EditText) findViewById (R.id.customerName);
        phone = (EditText) findViewById (R.id.customerPhone);
        joined_Date = (EditText) findViewById (R.id.joinedDate);
        joined_Date.setClickable (true);
        customer_Place = (EditText) findViewById (R.id.customerPlace);

        joined_Date.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance ( );
                int mDay = calendar.get (Calendar.DAY_OF_MONTH);
                int mMonth = calendar.get (Calendar.MONTH);
                int mYear = calendar.get (Calendar.YEAR);
                datePicker = new DatePickerDialog (CustomerAddActivity.this, new DatePickerDialog.OnDateSetListener ( ) {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        joined_Date.setText (day + "/" + (month + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePicker.show ( );
            }
        });

        Intent intent = getIntent ( );
        int customer_ID = intent.getIntExtra ("Customer_ID", 0);
        String cName = intent.getStringExtra ("Customer_Name");
        long cPhone = intent.getLongExtra ("Phone", 0);
        String cusPhone=String.valueOf (cPhone);
        String jDate = intent.getStringExtra ("Joined_Date");
        String cPlace = intent.getStringExtra ("Customer_Place");

        Log.d ("Fetched Details", "Fetched items :: " + cName + "\t" + cusPhone + "\t" + jDate + "\t" + cPlace);

        if (customer_ID != 0) {
            customer_Name.setText (cName);
            phone.setText (cusPhone);
            joined_Date.setText (jDate);
            customer_Place.setText (cPlace);
        }
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
                String customerName = customer_Name.getText ( ).toString ( ).trim ( );
                String customerPhone = phone.getText ( ).toString ( ).trim ( );
                String joinedDate = joined_Date.getText ( ).toString ( );
                String customerPlace = customer_Place.getText ( ).toString ( ).trim ( );

                SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/mm/yyyy");
                String date = dateFormat.format (new Date ( ));

                if (customerName.length ( ) == 0 && customerPhone.length ( ) == 10 && joinedDate.length ( ) == 0 && customerPlace.length ( ) == 0) {
                    Snackbar.make (findViewById (android.R.id.content), "All fields are required", Snackbar.LENGTH_LONG).setAction ("Action", null).show ();
                } else if (customerPhone.length ( ) < 10){
                    Snackbar.make (findViewById (android.R.id.content), "10 digits required in the phone field", Snackbar.LENGTH_LONG).setAction ("Action", null).show ();
//                    Toast.makeText (getApplicationContext ( ), "10 digits required in the phone field", Toast.LENGTH_SHORT).show ( );
                } else {
                    Intent intentCustomer = getIntent ( );
                    int customer_ID = intentCustomer.getIntExtra ("Customer_ID", 0);

                    if (customer_ID == 0) {
                        long isInserted = NewsDBHelper.getInstance (this).insertCustomerDetails (customerName, Long.parseLong (customerPhone), joinedDate, date, customerPlace);
                        if (isInserted >= 1) {
                            Snackbar.make (findViewById (android.R.id.content), "Added successfully", Snackbar.LENGTH_LONG).setAction ("Action", null).show ();
                            Log.d ("Customer Details", "Inserted items :: " + customerName + "\t" + customerPhone + "\t" + joinedDate + "\t" + customerPlace);
                            Intent intent = new Intent (CustomerAddActivity.this, CustomerViewActivity.class);
                            startActivity (intent);
                            finish ( );
                        } else {
                            Log.d ("Brand Details", "Inserting error ");
                        }
                    } else {
                        boolean isUpdated = NewsDBHelper.getInstance (this).updateCustomerDetails (customer_ID, customerName, Long.parseLong (customerPhone), joinedDate, date, customerPlace);
                        if (isUpdated == true) {
                            Snackbar.make (findViewById (android.R.id.content), "Updated successfully", Snackbar.LENGTH_LONG).setAction ("Action", null).show ();
                            Log.d ("Updated", "Updated : " + customerName);
                            finish ( );
                        }
                    }
                }
                break;

            case R.id.deleteBrand:
                Intent intent = getIntent ( );
                long myID = intent.getIntExtra ("Customer_ID", 0);
                if (myID != 0) {
                    NewsDBHelper.getInstance (this).deleteCustomer (myID);
                    Snackbar.make (findViewById (android.R.id.content), "Item deleted successfully", Snackbar.LENGTH_LONG).setAction ("Action", null).show ();
                    finish ( );
                } else {
                    Snackbar.make (findViewById (android.R.id.content), "No item found", Snackbar.LENGTH_LONG).setAction ("Action", null).show ();
                }
                break;
        }
        return super.onOptionsItemSelected (item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed ( );
        finish ( );
    }
}
