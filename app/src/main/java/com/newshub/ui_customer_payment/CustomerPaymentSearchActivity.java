package com.newshub.ui_customer_payment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

import com.newshub.helper.NewsDBHelper;
import com.newshub.R;
import com.newshub.model.Customer;

import java.util.ArrayList;

public class CustomerPaymentSearchActivity extends AppCompatActivity {

    private AutoCompleteTextView search;
    private ArrayList<Customer> phoneList;
    Customer customer;
    private ListView customerPhone;
    ArrayAdapter<Long> customerAdapter;

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
                Snackbar.make (view, "Create customer Payment", Snackbar.LENGTH_LONG)
                        .setAction ("Action", null).show ( );
                Intent intent = new Intent (CustomerPaymentSearchActivity.this, CustomerPaymentAddActivity.class);
                startActivity (intent);
            }
        });

        search = (AutoCompleteTextView) findViewById (R.id.searchPhone);
        customerPhone=(ListView)findViewById (R.id.phoneList);

        phoneList = NewsDBHelper.getInstance (this).getCustomerDetails ();
        customerAdapter=new ArrayAdapter<Long> (this, android.R.layout.simple_list_item_1);
        for (int i=0;i<phoneList.size ();i++){
            customer=phoneList.get (i);
            long phone=customer.getPhone ();
            customerAdapter.add (phone);
        }
        customerPhone.setAdapter (customerAdapter);

        search.addTextChangedListener (new TextWatcher ( ) {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                CustomerPaymentSearchActivity.this.customerAdapter.getFilter ().filter (charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        customerPhone.setOnItemClickListener (new AdapterView.OnItemClickListener ( ) {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                customer=phoneList.get (position);
                Intent intent=new Intent (CustomerPaymentSearchActivity.this, CustomerPaymentViewActivity.class);
                intent.putExtra ("ID", customer.getCutomer_ID ());
                startActivity (intent);
                Snackbar.make (view, "Selected :: "+customer.getCutomer_ID (), Snackbar.LENGTH_LONG)
                        .setAction ("Action", null).show ( );

            }
        });
    }

}
