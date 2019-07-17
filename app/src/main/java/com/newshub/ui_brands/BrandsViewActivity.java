package com.newshub.ui_brands;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.newshub.helper.NewsDBHelper;
import com.newshub.R;
import com.newshub.model.Brands;

import java.util.ArrayList;

public class BrandsViewActivity extends AppCompatActivity {

    private ListView brandViewList;
    ArrayList<Brands> brandsList;
    NewsDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_brands_view);
        setResult (Activity.RESULT_OK);

        brandViewList = (ListView) findViewById (R.id.brandView);

        brandsList = NewsDBHelper.getInstance (this).getBrandDetails ( );
        BrandsAdapter adapter = new BrandsAdapter (this, brandsList);
        brandViewList.setAdapter (adapter);
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
        this.finish ();
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
                Intent intent =new Intent (BrandsViewActivity.this, BrandsAddActivity.class);
                startActivity (intent);
        }
        return super.onOptionsItemSelected (item);
    }
}
