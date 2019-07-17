package com.newshub.ui_brands;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.newshub.R;
import com.newshub.model.Brands;

import java.util.ArrayList;

public class BrandsAdapter extends ArrayAdapter<Brands> {

    private Context context;
    private ArrayList<Brands> brandsList;

    public BrandsAdapter(Context context, ArrayList<Brands> brandsList) {
        super (context, 0, brandsList);
        this.context = context;
        this.brandsList = brandsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View brandsView = convertView;
        ViewHolder holder;

        final Brands brands = brandsList.get (position);

        if (brandsView == null) {
            brandsView = LayoutInflater.from (context).inflate (R.layout.brands_view_list, parent, false);
            holder = new ViewHolder ( );
            holder.no = (TextView) brandsView.findViewById (R.id.brandIndexNo);
            holder.brandItemName = (TextView) brandsView.findViewById (R.id.brandNameList);
            holder.btnEdit = (ImageButton) brandsView.findViewById (R.id.imgBtnEdit);
            brandsView.setTag (holder);
        } else {
            holder = (ViewHolder) brandsView.getTag ( );
        }

        for (int i = 0; i < brandsList.size ( ); i++) {
            int list_no = position + 1;
            holder.no.setText (String.valueOf (list_no));
            holder.brandItemName.setText (brands.getBrand_Name ( ));
            holder.btnEdit.setOnClickListener (new View.OnClickListener ( ) {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent (getContext ( ), BrandsAddActivity.class);
                    intent.putExtra ("Brand_ID", brands.getBrand_index ( ));
                    intent.putExtra ("Brand_Name", brands.getBrand_Name ( ));
                    Log.d ("Brand Name :: ", "Brand Name:: " + brands.getBrand_Name ( ));
                    intent.putExtra ("Started_Date", brands.getStarted_Date ( ));
                    intent.putExtra ("Created_Date", brands.getCreated_Date ( ));
                    intent.putExtra ("Retail_Price", brands.getRetail_Price ( ));
                    intent.putExtra ("Customer_Price", brands.getCustomer_Price ( ));
                    intent.putExtra ("Offer_Percent", brands.getOffer_percent ( ));
                    intent.putExtra ("Offer_Price", brands.getOffer_Price ( ));
                    getContext ( ).startActivity (intent);
                }
            });
        }
        return brandsView;
    }

    private class ViewHolder {
        private TextView no, brandItemName;
        private ImageButton btnEdit;
    }
}
