package com.newshub.ui_customer_payment;

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
import com.newshub.model.CustomerPayments;

import java.util.ArrayList;

public class CustomerPaymentAdapter extends ArrayAdapter<CustomerPayments> {
    private ArrayList<CustomerPayments> paymentsList;
    private Context context;

    public CustomerPaymentAdapter(@NonNull Context context, ArrayList<CustomerPayments> paymentsList) {
        super (context, 0, paymentsList);
        this.context = context;
        this.paymentsList = paymentsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View paymentView = convertView;
        ViewHolder holder;

        final CustomerPayments payments = paymentsList.get (position);

        if (paymentView == null) {
            paymentView = LayoutInflater.from (context).inflate (R.layout.customer_payment_list_view, parent, false);
            holder = new ViewHolder ( );
            holder.indexNo = (TextView) paymentView.findViewById (R.id.cPIndexNo);
            holder.paymentName = (TextView) paymentView.findViewById (R.id.cPNameList);
            holder.btnPaymentEdit = (ImageButton) paymentView.findViewById (R.id.imgBtnCPEdit);
            paymentView.setTag (holder);
        } else {
            holder = (ViewHolder) paymentView.getTag ( );
        }
        for (int i = 0; i < paymentsList.size ( ); i++) {
            int index = position + 1;
            holder.indexNo.setText (String.valueOf (index));
            holder.paymentName.setText (payments.getCustomer_name ( ));
            holder.btnPaymentEdit.setOnClickListener (new View.OnClickListener ( ) {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent (getContext ( ), CustomerPaymentAddActivity.class);
                    intent.putExtra ("Payments_ID", payments.getCustomer_Payment_ID ( ));
                    intent.putExtra ("Customer_Name", payments.getCustomer_name ( ));
                    intent.putExtra ("Brand_Name", payments.getBrand_name ( ));
                    intent.putExtra ("Quantity", payments.getQuantity ( ));
                    intent.putExtra ("From_Date", payments.getFrom_date ( ));
                    intent.putExtra ("To_Date", payments.getTo_date ( ));
                    intent.putExtra ("Amount", payments.getAmount ( ));
                    getContext ( ).startActivity (intent);
                }
            });
        }
        return paymentView;
    }

    private class ViewHolder {
        public TextView indexNo;
        public TextView paymentName;
        public ImageButton btnPaymentEdit;
    }
}
