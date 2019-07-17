package com.newshub.ui_location;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.newshub.helper.NewsDBHelper;
import com.newshub.R;
import com.newshub.model.Locations;

import java.util.ArrayList;

public class LocationViewActivity extends AppCompatActivity {

    private ListView locationListView;
    private ArrayList<Locations> locationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_location_view);

        locationListView=(ListView)findViewById (R.id.locationsViewList);
        locationsList= NewsDBHelper.getInstance (this).getLocationDetails ();
        LocationsAdapter adapter=new LocationsAdapter (this, locationsList);
        locationListView.setAdapter (adapter);
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
                Intent intent =new Intent (LocationViewActivity.this, LocationAddActivity.class);
                startActivity (intent);
        }
        return super.onOptionsItemSelected (item);
    }
}
