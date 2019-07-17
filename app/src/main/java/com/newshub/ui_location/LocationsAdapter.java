package com.newshub.ui_location;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.newshub.R;
import com.newshub.model.Locations;

import java.util.ArrayList;

public class LocationsAdapter extends ArrayAdapter<Locations> {
    private final Context context;
    private final ArrayList<Locations> locationsList;

    public LocationsAdapter(@NonNull Context context, ArrayList<Locations> locationsList) {
        super (context, 0, locationsList);
        this.context = context;
        this.locationsList = locationsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View locationsView = convertView;
        ViewHolder holder;

        final Locations locations = locationsList.get (position);

        if (locationsView == null) {
            locationsView = LayoutInflater.from (context).inflate (R.layout.locations_view_list, parent, false);
            holder = new ViewHolder ( );
            holder.indexNo = (TextView) locationsView.findViewById (R.id.locationIndexNo);
            holder.locationName = (TextView) locationsView.findViewById (R.id.locationNameList);
            holder.imgBtnLocationEdit = (ImageButton) locationsView.findViewById (R.id.imgBtnLEdit);
            locationsView.setTag (holder);
        } else {
            holder = (ViewHolder) locationsView.getTag ( );
        }

        for (int i = 0; i < locationsList.size ( ); i++) {
            int indexID = position + 1;
            holder.indexNo.setText (String.valueOf (indexID));
            holder.locationName.setText (String.valueOf (locations.getPincode ( )));
            holder.imgBtnLocationEdit.setOnClickListener (new View.OnClickListener ( ) {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent (getContext ( ), LocationAddActivity.class);
                    intent.putExtra ("Location_ID", locations.getLocation_ID ( ));
                    intent.putExtra ("State", locations.getState ( ));
                    intent.putExtra ("City", locations.getCity ( ));
                    intent.putExtra ("Pincode", locations.getPincode ( ));
                    getContext ( ).startActivity (intent);
                }
            });
        }
        return locationsView;
    }

    private class ViewHolder {
        public TextView indexNo;
        public TextView locationName;
        public ImageButton imgBtnLocationEdit;
    }
}
