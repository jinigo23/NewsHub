package com.newshub.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.newshub.R;
import com.newshub.model.Menu;
import com.newshub.ui_main.PopupListener;

import java.util.ArrayList;
import java.util.Arrays;

public class AppDataManager {

    private Context context;
    private ArrayList<String> alertList;

    private static final AppDataManager ourInstance = new AppDataManager ( );

    public static AppDataManager getInstance() {
        return ourInstance;
    }

    private ArrayList<Menu> gridItems = new ArrayList<Menu> ( );

    final int CODE_BRAND = 0;
    final int CODE_CUSTOMER = 1;
    final int CODE_LOCATION = 2;
    final int CODE_CUSTOMER_REPORT = 3;
    final int CODE_MY_PAYMENT = 4;
    final int CODE_CUSTOMER_PAYMENT = 5;

    private AppDataManager() {
        gridItems.add (new Menu (CODE_BRAND, R.drawable.icons8_news_100, "Brands"));
        gridItems.add (new Menu (CODE_CUSTOMER, R.drawable.icons8_customer_100, "Customers"));
        gridItems.add (new Menu (CODE_LOCATION, R.drawable.icons8_place_marker_100, "Locations"));
        gridItems.add (new Menu (CODE_CUSTOMER_REPORT, R.drawable.icons8_business_report_100, "Customer Report"));
        gridItems.add (new Menu (CODE_MY_PAYMENT, R.drawable.icons8_rupee_100, "My Payment"));
        gridItems.add (new Menu (CODE_CUSTOMER_PAYMENT, R.drawable.icons8_rupee_100, "Customer Payment"));
    }

    public ArrayList<Menu> getGridList() {
        return gridItems;
    }

    public void showAlert(final Context context, final String[] popup, final PopupListener listener) {
        View view = LayoutInflater.from (context).inflate (R.layout.popup_window_list, null);
        final ListView itemList = (ListView) view.findViewById (R.id.dialogList);
        AlertDialog.Builder builder = new AlertDialog.Builder (context);
        alertList = new ArrayList<> (Arrays.asList (popup));
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (context, android.R.layout.simple_list_item_1, alertList);
        itemList.setAdapter (adapter);
        builder.setView (view);
        final AlertDialog dialog = builder.show ( );
        itemList.setOnItemClickListener (new AdapterView.OnItemClickListener ( ) {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Activity activity = (Activity) context;
                String clickedItem = itemList.getItemAtPosition (position).toString ( );
                Intent intent = new Intent ( );
                intent.putExtra ("Clicked Item", clickedItem);
                activity.setResult (Activity.RESULT_OK, intent);
                Log.d ("Clicked", "Item :: " + clickedItem);
                Toast.makeText (context, "Clicked \t" + clickedItem, Toast.LENGTH_LONG).show ( );
                listener.choosenItem (position);
                dialog.dismiss ( );
            }
        });
    }
}
