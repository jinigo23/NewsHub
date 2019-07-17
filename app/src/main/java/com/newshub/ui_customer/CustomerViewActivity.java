package com.newshub.ui_customer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.newshub.helper.NewsDBHelper;
import com.newshub.R;
import com.newshub.model.Customer;

import java.util.ArrayList;

public class CustomerViewActivity extends AppCompatActivity {

    private ListView customerViewList;
    ArrayList<Customer> customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_customer_view);

        customerViewList=(ListView)findViewById (R.id.customerView);
        customerList= NewsDBHelper.getInstance (this).getCustomerDetails ();
        CustomerAdapter adapter=new CustomerAdapter(this, customerList);
        customerViewList.setAdapter (adapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart ( );
        finish ();
        startActivity (getIntent ());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed ( );
        finish ();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.brands_view_menu, menu);
        return super.onCreateOptionsMenu (menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId ()){
            case R.id.brands_add:
                Intent intent =new Intent (CustomerViewActivity.this, CustomerAddActivity.class);
                startActivity (intent);
        }
        return super.onOptionsItemSelected (item);
    }
}
