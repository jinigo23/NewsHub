package com.newshub.ui_customer_report;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.newshub.helper.NewsDBHelper;
import com.newshub.R;

import java.util.ArrayList;

public class CustomerReportActivity extends AppCompatActivity {

    private ListView customerReportView;
    private TextView txt;
    private ArrayList<CustomerReport> reportList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_customer_report);

        customerReportView = (ListView) findViewById (R.id.customerReportView);
        Cursor cursor = NewsDBHelper.getInstance (this).getCustomerReport ( );

        String customer_name = cursor.getString (1);
        String brand_name = cursor.getString (8);
        String joined_date = cursor.getString (2);
        String location = cursor.getString (5);
        String quantity = cursor.getString (9);
//            String amount=cursor.getString (15);


        reportList = new ArrayList<CustomerReport> ( );

//        while (cursor.moveToNext ()) {
//            reportList.add (cursor.getString (0)+"\t"+cursor.getString (1)+"\t"+cursor.getString (2)+"\t"+cursor.getString (3)+"\t"+cursor.getString (4)+"\t"+cursor.getString (5)+"\t"+cursor.getString (6)+"\t"+cursor.getString (7)+"\t"+cursor.getString (8)+"\t"+cursor.getString (9)+"\t"+cursor.getString (10)+"\t"+cursor.getString (11)+"\t"+cursor.getString (12));

//            reportList.add (report);

//        }

        if (cursor.moveToFirst ( )) {
            do {
                CustomerReport report = new CustomerReport (customer_name, brand_name, joined_date, location, Integer.parseInt (quantity));
                reportList.add (report);
            } while (cursor.moveToNext ( ));
        }
        Log.d ("Report List:", "List:: " + reportList);

        CustomerReportAdapter reportAdapter = new CustomerReportAdapter (this, reportList);
        customerReportView.setAdapter (reportAdapter);
        cursor.close ( );

//        ArrayAdapter adapter = new ArrayAdapter (this, android.R.layout.simple_list_item_1, reportList);
//        customerReportView.setAdapter (adapter);

//        txt=(TextView)findViewById (R.id.textView);
//        Cursor cursor = NewsDBHelper.getInstance (this).getCustomerReport ();
//        cursor.moveToFirst ();
//        txt.setText (cursor.getString (0)+"\t"+cursor.getString (1)+"\t"+cursor.getString (2)+"\t"+cursor.getString (3)+"\t"+cursor.getString (4)+"\t"+cursor.getString (5)+"\t"+cursor.getString (6)+"\t"+cursor.getString (7)+"\t"+cursor.getString (8)+"\t"+cursor.getString (9)+"\t"+cursor.getString (10)+"\t"+cursor.getString (11)+"\t"+cursor.getString (12));
    }
}
