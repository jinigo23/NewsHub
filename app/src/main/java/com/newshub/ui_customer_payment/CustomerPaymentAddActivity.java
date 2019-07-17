package com.newshub.ui_customer_payment;

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

public class CustomerPaymentAddActivity extends AppCompatActivity {

    private EditText customer_Name, brand_Name, quantity, from_Date, to_Date, amount;
    DatePickerDialog pickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_customer_payment_add);

        customer_Name = (EditText) findViewById (R.id.cPaymentName);
        brand_Name = (EditText) findViewById (R.id.cPaymentBrand);
        quantity = (EditText) findViewById (R.id.cPaymentQuantity);
        from_Date = (EditText) findViewById (R.id.cFromDate);
        from_Date.setClickable (true);
        from_Date.setFocusable (false);
        to_Date = (EditText) findViewById (R.id.cToDate);
        to_Date.setClickable (true);
        to_Date.setFocusable (false);
        amount = (EditText) findViewById (R.id.cPaymentAmount);

        from_Date.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance ( );
                int cDay = calendar.get (Calendar.DAY_OF_MONTH);
                int cMonth = calendar.get (Calendar.MONTH);
                int cYear = calendar.get (Calendar.YEAR);
                pickerDialog = new DatePickerDialog (CustomerPaymentAddActivity.this, new DatePickerDialog.OnDateSetListener ( ) {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        from_Date.setText (day + "/" + (month + 1) + "/" + year);
                    }
                }, cYear, cMonth, cDay);
                pickerDialog.show ( );
            }
        });

        to_Date.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance ( );
                int cDay = calendar.get (Calendar.DAY_OF_MONTH);
                int cMonth = calendar.get (Calendar.MONTH);
                int cYear = calendar.get (Calendar.YEAR);
                pickerDialog = new DatePickerDialog (CustomerPaymentAddActivity.this, new DatePickerDialog.OnDateSetListener ( ) {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        to_Date.setText (day + "/" + (month + 1) + "/" + year);
                    }
                }, cYear, cMonth, cDay);
                pickerDialog.show ( );
            }
        });

        Intent intent = getIntent ( );
        int indexID = intent.getIntExtra ("Payments_ID", 0);
        String cName = intent.getStringExtra ("Customer_Name");
        String bName = intent.getStringExtra ("Brand_Name");
        int pQuantity = intent.getIntExtra ("Quantity", 0);
        String fDate = intent.getStringExtra ("From_Date");
        String tDate = intent.getStringExtra ("To_Date");
        float price = intent.getFloatExtra ("Amount", 0);

        if (indexID != 0) {
            customer_Name.setText (cName);
            brand_Name.setText (bName);
            quantity.setText (String.valueOf (pQuantity));
            from_Date.setText (fDate);
            to_Date.setText (tDate);
            amount.setText (String.valueOf (price));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed ( );
        finish ( );
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
                String brandName = brand_Name.getText ( ).toString ( ).trim ( );
                String cQuantity = quantity.getText ( ).toString ( ).trim ( );
                String fromDate = from_Date.getText ( ).toString ( ).trim ( );
                String toDate = to_Date.getText ( ).toString ( ).trim ( );
                String cAmount = amount.getText ( ).toString ( ).trim ( );


                SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/mm/yyyy");
                String date = dateFormat.format (new Date ( ));

                if (customerName.length ( ) == 0 && brandName.length ( ) == 0 && cQuantity.length ( ) == 0 && fromDate.length ( ) == 0 && toDate.length ( ) == 0 && cAmount.length ( ) == 0) {
                    Snackbar.make (findViewById (android.R.id.content), "All fields are required", Snackbar.LENGTH_LONG).setAction ("Action", null).show ( );
//                    Toast.makeText (getApplicationContext ( ), "All fields are requred", Toast.LENGTH_SHORT).show ( );
                } else {

                    Intent intentPayment = getIntent ( );
                    int indexID = intentPayment.getIntExtra ("Payments_ID", 0);

                    if (indexID == 0) {
                        int itemQuantity = Integer.valueOf (cQuantity);
                        float totalAmount = Float.valueOf (cAmount);

                        long isInserted = NewsDBHelper.getInstance (this).insertCustomerPayments (customerName, brandName, itemQuantity, fromDate, toDate, date, totalAmount);
                        if (isInserted >= 1) {
                            Snackbar.make (findViewById (android.R.id.content), "Customer payment added successfully", Snackbar.LENGTH_LONG).setAction ("Action", null).show ();
                            Log.d ("Customer Payment Detail", "Inserted items :: " + customerName + "\t" + brandName + "\t" + cAmount);
                            Intent intent = new Intent (CustomerPaymentAddActivity.this, CustomerPaymentViewActivity.class);
                            startActivity (intent);
                            finish ( );
                        } else {
                            Log.d ("Location Details", "Inserting error ");
                        }
                    } else {
                        int itemQuantity = Integer.valueOf (cQuantity);
                        float totalAmount = Float.valueOf (cAmount);
                        boolean isUpdated = NewsDBHelper.getInstance (this).updateCutomerPayment (indexID, customerName, brandName, itemQuantity, fromDate, toDate, date, totalAmount);
                        if (isUpdated == true) {
                            Snackbar.make (findViewById (android.R.id.content), "Updated successfully", Snackbar.LENGTH_LONG).setAction ("Action", null).show ();
                            finish ( );
                        }
                    }
                }
                break;

            case R.id.deleteBrand:
                Intent intentPayment = getIntent ( );
                int indexID = intentPayment.getIntExtra ("Payments_ID", 0);

                if (indexID != 0) {
                    NewsDBHelper.getInstance (this).deleteCustomerPayment (indexID);
                    Snackbar.make (findViewById (android.R.id.content), "Item deleted successfully", Snackbar.LENGTH_LONG).setAction ("Action", null).show ();
                    finish ( );
                } else {
                    Snackbar.make (findViewById (android.R.id.content), "No item found", Snackbar.LENGTH_LONG).setAction ("Action", null).show ();
                }
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected (item);
    }
}
