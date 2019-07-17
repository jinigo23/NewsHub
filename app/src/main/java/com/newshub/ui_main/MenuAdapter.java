package com.newshub.ui_main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.newshub.R;
import com.newshub.model.Menu;

import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<com.newshub.model.Menu> menuGrid;

    public MenuAdapter(Context context, ArrayList<Menu> menuGrid) {
        this.context = context;
        this.menuGrid = menuGrid;
    }

    @Override
    public int getCount() {
        return menuGrid.size ();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridView;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService (context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            gridView = layoutInflater.inflate (R.layout.menu_grid_items, null);
            Menu menu=menuGrid.get (position);
            ImageView gridImg = (ImageView) gridView.findViewById (R.id.gridImg);
            TextView gridTxt = (TextView) gridView.findViewById (R.id.gridTxt);
            gridImg.setImageResource (menu.getIcon ());
            gridTxt.setText (menu.getName ());
        } else {
            gridView = (View) convertView;
        }
        return gridView;
    }
}
