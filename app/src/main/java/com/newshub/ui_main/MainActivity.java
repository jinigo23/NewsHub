package com.newshub.ui_main;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.newshub.helper.AppDataManager;
import com.newshub.helper.NewsDBHelper;
import com.newshub.R;
import com.newshub.ui_brands.BrandsAddActivity;
import com.newshub.ui_brands.BrandsViewActivity;
import com.newshub.ui_customer_payment.CustomerPaymentSearchActivity;
import com.newshub.ui_customer_report.CustomerReportActivity;
import com.newshub.ui_customer.CustomerAddActivity;
import com.newshub.ui_customer.CustomerViewActivity;
import com.newshub.ui_location.LocationAddActivity;
import com.newshub.ui_location.LocationViewActivity;
import com.newshub.model.Menu;
import com.newshub.ui_my_payment.MyPaymentsAddActivity;
import com.newshub.ui_my_payment.MyPaymentsViewActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    final int CODE_BRAND = 0;
    final int CODE_CUSTOMER = 1;
    final int CODE_LOCATION = 2;
    final int CODE_CUSTOMER_REPORT = 3;
    final int CODE_MY_PAYMENT = 4;
    final int CODE_CUSTOMER_PAYMENT = 5;

    private GridView gridMenu;
    private boolean doubleBackPressed;

    String[] brandsPopup = {"Add Brand", "View Brands"};
    String[] customerPopup = {"Add Customer", "View Customers"};
    String[] locationPopup = {"Add Location", "View Locations"};
    String[] customerReportPopup = {"View Report"};
    String[] myPaymentPopup = {"Add Payment", "View Payments"};
    String[] customerPaymentPopup = {"Add Payment", "View Payments"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        NewsDBHelper.getInstance (this);
        Log.d ("Create table", "Created :: \t" + NewsDBHelper.getInstance (this));

        gridMenu = (GridView) findViewById (R.id.menuGrid);

        final ArrayList<Menu> gridList = AppDataManager.getInstance ( ).getGridList ( );
        Log.d ("Grid Items :: ", "List" + gridList);
        MenuAdapter adapter = new MenuAdapter (this, gridList);
        gridMenu.setAdapter (adapter);

        gridMenu.setOnItemClickListener (new AdapterView.OnItemClickListener ( ) {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                PopupMenu popupMenu = new PopupMenu (MainActivity.this, view);
                popupMenu.getMenuInflater ( ).inflate (R.menu.brand_popup_menu, popupMenu.getMenu ( ));

                Menu menu = gridList.get (position);
                switch (menu.getId ( )) {
                    case CODE_BRAND:

                        AppDataManager.getInstance ( ).showAlert (MainActivity.this, brandsPopup, new PopupListener ( ) {
                            @Override
                            public void choosenItem(int position) {
                                switch (position) {
                                    case 0:
                                        Intent intentAdd = new Intent (MainActivity.this, BrandsAddActivity.class);
                                        startActivity (intentAdd);
                                        break;
                                    case 1:
                                        Intent intentView = new Intent (MainActivity.this, BrandsViewActivity.class);
                                        startActivity (intentView);
                                        break;
                                }
                            }
                        });
                        break;

                    case CODE_CUSTOMER:

                        AppDataManager.getInstance ( ).showAlert (MainActivity.this, customerPopup, new PopupListener ( ) {
                            @Override
                            public void choosenItem(int position) {
                                switch (position) {
                                    case 0:
                                        Intent intentAdd = new Intent (MainActivity.this, CustomerAddActivity.class);
                                        startActivity (intentAdd);
                                        break;
                                    case 1:
                                        Intent intentView = new Intent (MainActivity.this, CustomerViewActivity.class);
                                        startActivity (intentView);
                                        break;
                                }
                            }
                        });
                        break;

                    case CODE_LOCATION:

                        AppDataManager.getInstance ( ).showAlert (MainActivity.this, locationPopup, new PopupListener ( ) {
                            @Override
                            public void choosenItem(int position) {
                                switch (position) {
                                    case 0:
                                        Intent intentAdd = new Intent (MainActivity.this, LocationAddActivity.class);
                                        startActivity (intentAdd);
                                        break;

                                    case 1:
                                        Intent intentView = new Intent (MainActivity.this, LocationViewActivity.class);
                                        startActivity (intentView);
                                        break;
                                }
                            }
                        });
                        break;

                    case CODE_CUSTOMER_REPORT:
                        Intent intentAdd = new Intent (MainActivity.this, CustomerReportActivity.class);
                        startActivity (intentAdd);
                        break;

                    case CODE_MY_PAYMENT:
                        AppDataManager.getInstance ( ).showAlert (MainActivity.this, myPaymentPopup, new PopupListener ( ) {
                            @Override
                            public void choosenItem(int position) {
                                switch (position) {
                                    case 0:
                                        Intent intentAdd = new Intent (MainActivity.this, MyPaymentsAddActivity.class);
                                        startActivity (intentAdd);
                                        break;

                                    case 1:
                                        Intent intentView = new Intent (MainActivity.this, MyPaymentsViewActivity.class);
                                        startActivity (intentView);
                                        break;
                                }
                            }
                        });
                        break;

                    case CODE_CUSTOMER_PAYMENT:
                        Intent intentView = new Intent (MainActivity.this, CustomerPaymentSearchActivity.class);
                        startActivity (intentView);
                        break;


/*                        popupMenu.setOnMenuItemClickListener (new PopupMenu.OnMenuItemClickListener ( ) {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                switch (menuItem.getItemId ( )) {
                                    case R.id.addPopup:
                                        Intent intentAdd = new Intent (MainActivity.this, CustomerPaymentAddActivity.class);
                                        startActivity (intentAdd);
                                        break;

                                    case R.id.viewPopup:
                                        Intent intentView = new Intent (MainActivity.this, CustomerPaymentViewActivity.class);
                                        startActivity (intentView);
                                        break;

                                    default:
                                        return false;
                                }
                                return true;
                            }
                        });
                        popupMenu.show ();*/
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackPressed) {
            super.onBackPressed ( );
            return;
        }
        this.doubleBackPressed = true;
        Snackbar.make (findViewById (android.R.id.content), "Back press again to exit", Snackbar.LENGTH_SHORT).setAction ("Action", null).show ( );

        new Handler ( ).postDelayed (new Runnable ( ) {
            @Override
            public void run() {
                doubleBackPressed = false;
            }
        }, 2000);
    }
}
