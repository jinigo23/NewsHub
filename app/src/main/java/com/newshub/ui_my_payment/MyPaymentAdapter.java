package com.newshub.ui_my_payment;

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
import com.newshub.model.MyPayments;

import java.util.ArrayList;

class MyPaymentAdapter extends ArrayAdapter<MyPayments> {

    private Context context;
    private ArrayList<MyPayments> myPaymentsList;

    public MyPaymentAdapter(Context context, ArrayList<MyPayments> myPaymentsList) {
        super (context, 0, myPaymentsList);
        this.context = context;
        this.myPaymentsList = myPaymentsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        View myPaymentView = convertView;
        final MyPayments myPayments = getItem (position);
        if (myPaymentView == null) {
            myPaymentView = LayoutInflater.from (context).inflate (R.layout.my_payments_view_list, parent, false);
            holder = new ViewHolder ( );
            holder.index = (TextView) myPaymentView.findViewById (R.id.myPayIndexNo);
            holder.mypBrandName = (TextView) myPaymentView.findViewById (R.id.myPayNameList);
            holder.imgBtnmyPaymentEdit = (ImageButton) myPaymentView.findViewById (R.id.imgBtnMyPayEdit);
            myPaymentView.setTag (holder);
        } else {
            holder = (ViewHolder) myPaymentView.getTag ( );
        }
        for (int i = 0; i < myPaymentsList.size ( ); i++) {
            int item_no=position+1;
            holder.index.setText (String.valueOf (item_no));
            holder.mypBrandName.setText (myPayments.getBrand_Name ( ));
            holder.imgBtnmyPaymentEdit.setOnClickListener (new View.OnClickListener ( ) {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent (getContext ( ), MyPaymentsAddActivity.class);
                    intent.putExtra ("My_Payment_ID", myPayments.getIndex_no ( ));
                    intent.putExtra ("My_Payment_Brand_Name", myPayments.getBrand_Name ( ));
                    intent.putExtra ("My_Payment_Quantity", myPayments.getQuantity ( ));
                    intent.putExtra ("My_Payment_Paid_Date", myPayments.getPaid_Date ( ));
                    intent.putExtra ("My_Payment_Amount", myPayments.getAmount ( ));
                    getContext ( ).startActivity (intent);
                }
            });
        }
        return myPaymentView;
    }

    private class ViewHolder {
        private TextView index, mypBrandName;
        private ImageButton imgBtnmyPaymentEdit;
    }
}
