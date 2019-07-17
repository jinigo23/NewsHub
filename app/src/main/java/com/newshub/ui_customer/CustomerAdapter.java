package com.newshub.ui_customer;

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
import com.newshub.model.Customer;

import java.util.ArrayList;

class CustomerAdapter extends ArrayAdapter<Customer> {

    private ArrayList<Customer> customerList;
    private Context context;

    public CustomerAdapter(@NonNull Context context, ArrayList<Customer> customerList) {
        super (context, 0, customerList);
        this.context = context;
        this.customerList = customerList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View customerView = convertView;
        ViewHolder holder;

        final Customer customer = customerList.get (position);

        if (customerView == null) {
            customerView = LayoutInflater.from (context).inflate (R.layout.customer_view_list, parent, false);
            holder = new ViewHolder ( );
            holder.indexNo = (TextView) customerView.findViewById (R.id.customerIndexNo);
            holder.customerName = (TextView) customerView.findViewById (R.id.customerNameList);
            holder.btnEditCustomer = (ImageButton) customerView.findViewById (R.id.imgCBtnEdit);
            customerView.setTag (holder);
        } else {
            holder = (ViewHolder) customerView.getTag ( );
        }

        for (int i = 0; i < customerList.size ( ); i++) {
            int index_No = position + 1;
            holder.indexNo.setText (String.valueOf (index_No));
            holder.customerName.setText (customer.getName ( ));
            holder.btnEditCustomer.setOnClickListener (new View.OnClickListener ( ) {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent (getContext ( ), CustomerAddActivity.class);
                    intent.putExtra ("Customer_ID", customer.getCutomer_ID ( ));
                    intent.putExtra ("Customer_Name", customer.getName ( ));
                    intent.putExtra ("Phone", customer.getPhone ());
                    intent.putExtra ("Joined_Date", customer.getJoined_Date ( ));
                    intent.putExtra ("Customer_Place", customer.getLocation ( ));
                    getContext ( ).startActivity (intent);
                }
            });
        }
        return customerView;
    }

    private class ViewHolder {
        private ImageButton btnEditCustomer;
        private TextView indexNo, customerName;
    }
}
