package com.newshub.ui_my_payment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.newshub.helper.NewsDBHelper;
import com.newshub.R;
import com.newshub.model.MyPayments;

import java.util.ArrayList;

public class MyPaymentsViewActivity extends AppCompatActivity {

    private ListView myPaymentViewList;
    private ArrayList<MyPayments> myPaymentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_my_payments_view);

        myPaymentViewList = (ListView)findViewById (R.id.myPaymentList);
        myPaymentList= NewsDBHelper.getInstance (this).getMyPaymentDetails ();
        MyPaymentAdapter adapter=new MyPaymentAdapter (this, myPaymentList);
        myPaymentViewList.setAdapter (adapter);
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
}
