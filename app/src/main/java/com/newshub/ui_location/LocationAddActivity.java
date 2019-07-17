package com.newshub.ui_location;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.newshub.helper.NewsDBHelper;
import com.newshub.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LocationAddActivity extends AppCompatActivity {

    private EditText state, city, pincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_location_add);

        state = (EditText) findViewById (R.id.stateName);
        city = (EditText) findViewById (R.id.cityName);
        pincode = (EditText) findViewById (R.id.pincodeNo);

        Intent intent = getIntent ( );
        int indexID = intent.getIntExtra ("Location_ID", 0);
        String l_State = intent.getStringExtra ("State");
        String l_City = intent.getStringExtra ("City");
        int l_Pincode = intent.getIntExtra ("Pincode", 0);

        if (indexID != 0) {
            state.setText (l_State);
            city.setText (l_City);
            pincode.setText (String.valueOf (l_Pincode));
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
                String lState = state.getText ( ).toString ( ).trim ( );
                String lCity = city.getText ( ).toString ( ).trim ( );
                String lPincode = pincode.getText ( ).toString ( ).trim ( );

                SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/mm/yyyy");
                String date = dateFormat.format (new Date ( ));

                if (lState.length ( ) == 0 && lCity.length ( ) == 0 && lPincode.length ( ) == 0) {
                    Toast.makeText (getApplicationContext ( ), "All fields are requred", Toast.LENGTH_SHORT).show ( );
                } else {
                    Intent intentLoc = getIntent ( );
                    int indexID = intentLoc.getIntExtra ("Location_ID", 0);
                    if (indexID == 0) {
                        long isInserted = NewsDBHelper.getInstance (this).insertLocationDetails (date, lState, lCity, Integer.valueOf (lPincode));
                        if (isInserted >= 1) {
                            Toast.makeText (getApplicationContext ( ), "Saved succesfully", Toast.LENGTH_SHORT).show ( );
                            Log.d ("Customer Details", "Inserted items :: " + lState + "\t" + lCity + "\t" + Integer.valueOf (lPincode));
                            Intent intent = new Intent (LocationAddActivity.this, LocationViewActivity.class);
                            startActivity (intent);
                            finish ( );
                        } else {
                            Log.d ("Location Details", "Inserting error ");
                        }
                    } else {
                        boolean isUpdated = NewsDBHelper.getInstance (this).updateLocation (indexID, date, lState, lCity, Integer.valueOf (lPincode));
                        if (isUpdated == true) {
                            Toast.makeText (getApplicationContext ( ), "Updated succesfully", Toast.LENGTH_SHORT).show ( );
                            finish ( );
                        }
                    }
                }
                break;

            case R.id.deleteBrand:
                Intent intent = getIntent ( );
                int indexID = intent.getIntExtra ("Location_ID", 0);
                if (indexID != 0) {
                    NewsDBHelper.getInstance (this).deleteLocation (indexID);
                    Toast.makeText (getApplicationContext ( ), "Item deleted Successfully", Toast.LENGTH_SHORT).show ( );
                    finish ( );
                } else {
                    Toast.makeText (getApplicationContext ( ), "No items found", Toast.LENGTH_SHORT).show ( );
                }
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected (item);
    }
}
