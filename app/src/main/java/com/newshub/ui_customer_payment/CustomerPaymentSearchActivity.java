package com.newshub.ui_customer_payment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.newshub.helper.NewsDBHelper;
import com.newshub.R;
import com.newshub.model.Customer;

import java.util.ArrayList;

public class CustomerPaymentSearchActivity extends AppCompatActivity {

    private AutoCompleteTextView search;
    private ArrayList<Long> customerList;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_customer_payment_search);
//        Toolbar toolbar = findViewById (R.id.toolbar);
//        setSupportActionBar (toolbar);

        FloatingActionButton fab = findViewById (R.id.fab);
        fab.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
//                Snackbar.make (view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction ("Action", null).show ( );
                Intent intent = new Intent (CustomerPaymentSearchActivity.this, CustomerPaymentAddActivity.class);
                startActivity (intent);
            }
        });

        search = (AutoCompleteTextView) findViewById (R.id.searchPhone);
        btnSearch = (Button) findViewById (R.id.btnSearch);


        btnSearch.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                /*Intent intent=new Intent (CustomerPaymentSearchActivity.this, CustomerPaymentViewActivity.class);
                intent.putExtra ("Phone", customer.getPhone ());
                startActivity (intent);*/

                String searchPhone = search.getText ( ).toString ( ).trim ( );
                if (searchPhone.length ( ) == 0) {
                    Snackbar.make (view, "Enter the Phone Number", Snackbar.LENGTH_LONG).setAction ("Action", null).show ( );
//                    Toast.makeText (getApplicationContext ( ), "Enter the Phone Number", Toast.LENGTH_SHORT).show ( );
                } else {
                    long searchNumber = Long.valueOf (searchPhone);
                    Customer customer = new Customer ( );
                    Cursor cursor = NewsDBHelper.getInstance (getApplicationContext ( )).getPhone (searchNumber);
                    customerList = new ArrayList<Long> ( );

                    long phone = cursor.getInt (3);

                    if (cursor.moveToFirst ( )) {
                        do {
                            customerList.add (phone);
                        }
                        while (cursor.moveToNext ( ));
                    }

                    ArrayAdapter<Long> adapter = new ArrayAdapter<Long> (getApplicationContext ( ), android.R.layout.select_dialog_item, customerList);
                    search.setThreshold (2);
                    search.setAdapter (adapter);
                }

            }
        });
    }

}
