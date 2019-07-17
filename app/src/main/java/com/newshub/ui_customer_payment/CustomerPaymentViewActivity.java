package com.newshub.ui_customer_payment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.newshub.R;

public class CustomerPaymentViewActivity extends AppCompatActivity {

    private ListView paymentListView;
    private TextView customerName, lastPaidDate, noOfDays, brandNames, perItemQuantity, perBrandPrice, totalPrice;
    private Button btnCancel, btnPaid;
    private CheckedTextView selectBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_customer_payment_view);

//        paymentListView = (ListView) findViewById (R.id.customerPaymentListView);
//        paymentList = NewsDBHelper.getInstance (this).getCustomerPayments ( );
//        CustomerPaymentAdapter adapter = new CustomerPaymentAdapter (this, paymentList);
//        paymentListView.setAdapter (adapter);

        customerName=(TextView)findViewById (R.id.cpTXTCNAME);
        selectBrand=(CheckedTextView)findViewById (R.id.cpTXTSELECTBRAND);
        lastPaidDate=(TextView)findViewById (R.id.cpTXTLASTPAIDDATE);
        noOfDays=(TextView)findViewById (R.id.cpTXTNOOFDAYS);
        brandNames=(TextView)findViewById (R.id.cpTXTBILLBRANDNAME);
        perItemQuantity=(TextView)findViewById (R.id.cpTXTTOTALNO);
        perBrandPrice=(TextView)findViewById (R.id.cpTXTITEMPRICE);
        totalPrice=(TextView)findViewById (R.id.cpTXTTOTALPRICE);
        btnCancel=(Button) findViewById (R.id.btncpCancel);
        btnPaid=(Button) findViewById (R.id.btncpPaid);

//        Cursor cursor=NewsDBHelper.getInstance (this).getPhone ();


    }

    @Override
    protected void onRestart() {
        super.onRestart ( );
        finish ( );
        startActivity (getIntent ( ));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed ( );
        finish ( );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ( ).inflate (R.menu.brands_view_menu, menu);
        return super.onCreateOptionsMenu (menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId ( )) {
            case R.id.brands_add:
                Intent intent = new Intent (CustomerPaymentViewActivity.this, CustomerPaymentAddActivity.class);
                startActivity (intent);
        }
        return super.onOptionsItemSelected (item);
    }
}
