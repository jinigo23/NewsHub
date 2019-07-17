package com.newshub.ui_my_payment;

import android.app.DatePickerDialog;
import android.content.Intent;
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

public class MyPaymentsAddActivity extends AppCompatActivity {

    private EditText brand_name, quantity, paid_date, amount;
    DatePickerDialog datePicker;
    NewsDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_my_payments_add);

        brand_name = (EditText) findViewById (R.id.myPaymentBrandName);
        quantity = (EditText) findViewById (R.id.myPaymentQuantity);
        paid_date = (EditText) findViewById (R.id.myPaymentPaidDate);
        paid_date.setClickable (true);
//        paid_date.setFocusable (false);
        amount = (EditText) findViewById (R.id.myPaymentAmount);

        paid_date.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance ( );
                int mDay = calendar.get (Calendar.DAY_OF_MONTH);
                int mMonth = calendar.get (Calendar.MONTH);
                int mYear = calendar.get (Calendar.YEAR);
                datePicker = new DatePickerDialog (MyPaymentsAddActivity.this, new DatePickerDialog.OnDateSetListener ( ) {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        paid_date.setText (day + "/" + (month + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePicker.show ( );
            }
        });

        Intent intent = getIntent ( );
        int mypIndex = intent.getIntExtra ("My_Payment_ID", 0);
        String mypBrandName = intent.getStringExtra ("My_Payment_Brand_Name");
        int mypQuantity = intent.getIntExtra ("My_Payment_Quantity", 0);
        String mypPaidDate = intent.getStringExtra ("My_Payment_Paid_Date");
        float mypAmount = intent.getFloatExtra ("My_Payment_Amount", 1);

        if (mypIndex != 0) {
            brand_name.setText (mypBrandName);
            quantity.setText (String.valueOf (mypQuantity));
            paid_date.setText (mypPaidDate);
            amount.setText (String.valueOf (mypAmount));
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
                String my_brand_Name = brand_name.getText ( ).toString ( ).trim ( );
                String my_quantity = quantity.getText ( ).toString ( ).trim ( );
                String my_paid_date = paid_date.getText ( ).toString ( ).trim ( );
                String my_amount = amount.getText ( ).toString ( );

                SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/mm/yyyy");
                String date = dateFormat.format (new Date ( ));

                if (my_brand_Name.length ( ) == 0 && my_quantity.length ( ) == 0 && my_paid_date.length ( ) == 0 && my_amount.length ( ) == 0) {
                    Toast.makeText (getApplicationContext ( ), "All fields are required", Toast.LENGTH_SHORT).show ( );
                } else {
                    int item_quantity = Integer.parseInt (my_quantity);
                    float price = Float.parseFloat (my_amount);
                    helper = new NewsDBHelper (this);

                    Intent intentIndex = getIntent ( );
                    int indexId = intentIndex.getIntExtra ("My_Payment_ID", 0);
                    if (indexId == 0) {
                        long isMyPaymentInserted = NewsDBHelper.getInstance (this).insertMyPayments (my_brand_Name, my_quantity, my_paid_date, date, price);
                        if (isMyPaymentInserted >= 0) {
                            Toast.makeText (getApplicationContext ( ), "Saved succesfully", Toast.LENGTH_SHORT).show ( );
                            Intent intent = new Intent (MyPaymentsAddActivity.this, MyPaymentsViewActivity.class);
                            startActivity (intent);
                            finish ( );
                        } else {
                            Log.d ("Brand Details", "Inserting error :: " + my_brand_Name + "\t" + my_paid_date);
                        }
                    } else {
                        boolean isUpdated = NewsDBHelper.getInstance (this).updateMyPayments (indexId, my_brand_Name, item_quantity, my_paid_date, date, price);
                        if (isUpdated) {
                            Toast.makeText (getApplicationContext ( ), "Updated succesfully", Toast.LENGTH_SHORT).show ( );
                            Log.d ("Updated", "Updated : " + my_brand_Name);
                            finish ( );
                        }
                    }

                }
                break;

            case R.id.deleteBrand:
                if (brand_name != null) {
                    Intent intent = getIntent ( );
                    long myID = intent.getIntExtra ("My_Payment_ID", 0);
                    NewsDBHelper.getInstance (this).deleteMyPayments (myID);
                    Toast.makeText (getApplicationContext ( ), "Item deleted Successfully", Toast.LENGTH_SHORT).show ( );
                    finish ( );
                } else {
                    Toast.makeText (getApplicationContext ( ), "No items found", Toast.LENGTH_SHORT).show ( );
                }
                break;
        }
        return super.onOptionsItemSelected (item);
    }
}
