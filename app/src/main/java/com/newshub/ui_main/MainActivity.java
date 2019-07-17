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

import com.newshub.helper.AlertAdapter;
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
    /*private ArrayList<Menu> menuItems = new ArrayList<Menu> ( ) {
        {
            add (new Menu (CODE_BRAND, R.drawable.icons8_news_100, "Brands"));
            add (new Menu (CODE_CUSTOMER, R.drawable.icons8_customer_100, "Customer"));
            add (new Menu (CODE_LOCATION, R.drawable.icons8_place_marker_100, "Location"));
            add (new Menu (CODE_CUSTOMER_REPORT, R.drawable.icons8_business_report_100, "Customer Report"));
            add (new Menu (CODE_MY_PAYMENT, R.drawable.icons8_customer_100, "My Payment"));
            add (new Menu (CODE_CUSTOMER_PAYMENT, R.drawable.icons8_rupee_100, "Customer Payment"));
        }
    };*/
    /*private int imgThumbs[] = {R.drawable.icons8_news_100, R.drawable.icons8_customer_100, R.drawable.icons8_place_marker_100, R.drawable.icons8_business_report_100, R.drawable.icons8_rupee_100, R.drawable.icons8_rupee_100};
    private String txtTitles[] = {"Brands", "Customers", "Area", "Customer Report", "My payment", "Customer payment"};*/
    private boolean doubleBackPressed;
    AlertDialog.Builder builder;
    String[] brandsPopup = {"Add", "View"};
    String[] customerPopup = {"Add Customer", "View"};
    ArrayList<String> popUpList;
    Intent intent;

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

                        Bundle bundle=new Bundle ();
                        bundle.putStringArray ("Brands_Popup", brandsPopup);
                        bundle.putStringArray ("Customer_Popup", customerPopup);
                        PopupDialog popupDialog=new PopupDialog ();
                        popupDialog.setArguments (bundle);
                        popupDialog.show (getSupportFragmentManager (), "FRAGMENT_TAG");

                        /*boolean isClicked = AppDataManager.getInstance ( ).showAlert (MainActivity.this, brandsPopup);
                        if (isClicked == false) {
                            intent = getIntent ( );
                            startActivityForResult (intent, 1);
                            String clickedItem = intent.getStringExtra ("Clicked Item");
//                        Log.d ("Clicked", "Item :: "+clickedItem);

                            if (clickedItem.equals (Arrays.asList (brandsPopup).contains ("Add"))) {
                                intent = new Intent (MainActivity.this, BrandsAddActivity.class);
                                startActivity (intent);
                            } else if (clickedItem.equals (Arrays.asList (brandsPopup).contains ("View"))) {
                                intent = new Intent (MainActivity.this, BrandsViewActivity.class);
                                startActivity (intent);
                            }
                        }*/

                        /*if (clickedItem == null) {
                            Snackbar.make (findViewById (android.R.id.content), "Select", Snackbar.LENGTH_LONG).setAction ("Action", null).show ( );
                        } else {

                        }*/
                        /*Bundle bundle=new Bundle ();
                        bundle.putStringArray ("Brands", brandsPopup);
                        Fragment fragment=new Fragment ();
                        fragment.setArguments (bundle);


                        FragmentManager fragmentManager=getSupportFragmentManager ();
                        PopupDialog popupDialog=PopupDialog.newInstance ("Brands");
                        popupDialog.show (fragmentManager, "Brands Popup");*/

                        /*popupMenu.setOnMenuItemClickListener (new PopupMenu.OnMenuItemClickListener ( ) {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                int seletedItem = menuItem.getItemId ( );
                                switch (seletedItem) {
                                    case R.id.addPopup:
                                        Intent intentAdd = new Intent (MainActivity.this, BrandsAddActivity.class);
                                        startActivity (intentAdd);
                                        break;
                                    case R.id.viewPopup:
                                        Intent intentView = new Intent (MainActivity.this, BrandsViewActivity.class);
                                        startActivity (intentView);
                                        break;
                                    default:
                                        return false;
                                }
                                return true;
                            }
                        });
                        popupMenu.show ( );*/
                        break;

                    case CODE_CUSTOMER:

//                        AppDataManager.getInstance ( ).showAlert (MainActivity.this, customerPopup);

                        /*popupMenu.setOnMenuItemClickListener (new PopupMenu.OnMenuItemClickListener ( ) {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                switch (menuItem.getItemId ( )) {
                                    case R.id.addPopup:
                                        Intent intentAdd = new Intent (MainActivity.this, CustomerAddActivity.class);
                                        startActivity (intentAdd);
                                        break;

                                    case R.id.viewPopup:
                                        Intent intentView = new Intent (MainActivity.this, CustomerViewActivity.class);
                                        startActivity (intentView);
                                        break;

                                    default:
                                        return false;
                                }
                                return true;
                            }
                        });
                        popupMenu.show ( );*/
                        break;

                    case CODE_LOCATION:
                        popupMenu.setOnMenuItemClickListener (new PopupMenu.OnMenuItemClickListener ( ) {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                switch (menuItem.getItemId ( )) {
                                    case R.id.addPopup:
                                        Intent intentAdd = new Intent (MainActivity.this, LocationAddActivity.class);
                                        startActivity (intentAdd);
                                        break;

                                    case R.id.viewPopup:
                                        Intent intentView = new Intent (MainActivity.this, LocationViewActivity.class);
                                        startActivity (intentView);
                                        break;

                                    default:
                                        return false;
                                }
                                return true;
                            }
                        });
                        popupMenu.show ( );
                        break;

                    case CODE_CUSTOMER_REPORT:
                        Intent intentAdd = new Intent (MainActivity.this, CustomerReportActivity.class);
                        startActivity (intentAdd);
                        break;

                    case CODE_MY_PAYMENT:
                        popupMenu.setOnMenuItemClickListener (new PopupMenu.OnMenuItemClickListener ( ) {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                int selectedItem = menuItem.getItemId ( );
                                switch (selectedItem) {
                                    case R.id.addPopup:
                                        Intent intentAdd = new Intent (MainActivity.this, MyPaymentsAddActivity.class);
                                        startActivity (intentAdd);
                                        break;

                                    case R.id.viewPopup:
                                        Intent intentView = new Intent (MainActivity.this, MyPaymentsViewActivity.class);
                                        startActivity (intentView);
                                        break;
                                    default:
                                        return false;
                                }
                                return true;
                            }
                        });
                        popupMenu.show ( );
                        break;

                    case CODE_CUSTOMER_PAYMENT:
                        Intent intentView = new Intent (MainActivity.this, CustomerPaymentSearchActivity.class);
                        startActivity (intentView);
//                        popupMenu.setOnMenuItemClickListener (new PopupMenu.OnMenuItemClickListener ( ) {
//                            @Override
//                            public boolean onMenuItemClick(MenuItem menuItem) {
//                                switch (menuItem.getItemId ( )) {
//                                    case R.id.addPopup:
//                                        Intent intentAdd = new Intent (MainActivity.this, CustomerPaymentAddActivity.class);
//                                        startActivity (intentAdd);
//                                        break;
//
//                                    case R.id.viewPopup:
//                                        Intent intentView = new Intent (MainActivity.this, CustomerPaymentViewActivity.class);
//                                        startActivity (intentView);
//                                        break;
//
//                                    default:
//                                        return false;
//                                }
//                                return true;
//                            }
//                        });
//                        popupMenu.show ();
                        break;

                }
//                }
//                if (position == 4) {
//                    PopupMenu popupMenu4 = new PopupMenu (MainActivity.this, view);
//                    popupMenu4.getMenuInflater ( ).inflate (R.menu.brand_popup_menu, popupMenu4.getMenu ( ));
//                    popupMenu4.setOnMenuItemClickListener (new PopupMenu.OnMenuItemClickListener ( ) {
//                        @Override
//                        public boolean onMenuItemClick(MenuItem menuItem) {
//                            switch (menuItem.getItemId ( )) {
//                                case R.id.addPopup:
//                                    Intent intentAdd = new Intent (MainActivity.this, MyPaymentsAddActivity.class);
//                                    startActivity (intentAdd);
//                                    break;
//
//                                case R.id.viewPopup:
//                                    Intent intentView = new Intent (MainActivity.this, MyPaymentsViewActivity.class);
//                                    startActivity (intentView);
//                                    break;
//                            }
//
//                            return true;
//                        }
//                    });
//                    popupMenu4.show ();
//                }


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
        Toast.makeText (getApplicationContext ( ), "Back press again to exit", Toast.LENGTH_SHORT).show ( );

        new Handler ( ).postDelayed (new Runnable ( ) {
            @Override
            public void run() {
                doubleBackPressed = false;
            }
        }, 2000);
    }

    private void showDialog(String[] popUp) {
        builder = new AlertDialog.Builder (this);
        popUpList = new ArrayList<> (Arrays.asList (popUp));
        ArrayAdapter<String> adapter = new ArrayAdapter<> (MainActivity.this, android.R.layout.simple_list_item_1, popUpList);
        builder.setAdapter (adapter, new DialogInterface.OnClickListener ( ) {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        /*builder.setItems (popUp, new DialogInterface.OnClickListener ( ) {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });*/

        builder.show ( );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String clickedItem = intent.getStringExtra ("Clicked Item");
                Log.d ("Clicked", "Item :: " + clickedItem);
            }
        }
    }
}
