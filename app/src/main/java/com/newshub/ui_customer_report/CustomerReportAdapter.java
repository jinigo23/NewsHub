package com.newshub.ui_customer_report;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.newshub.R;

import java.util.ArrayList;

class CustomerReportAdapter extends ArrayAdapter<CustomerReport> {
    private final ArrayList<CustomerReport> reportList;
    private final Context context;

    public CustomerReportAdapter(@NonNull Context context, ArrayList<CustomerReport> reportList) {
        super (context, 0);
        this.context = context;
        this.reportList = reportList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View reportView = convertView;
        ViewHolder holder;

        if (reportView == null) {
            reportView = LayoutInflater.from (context).inflate (R.layout.customer_report_view_list, parent, false);
            holder = new ViewHolder ( );
            holder.no = (TextView) reportView.findViewById (R.id.crIndex);
            holder.customerName = (TextView) reportView.findViewById (R.id.crCName);
            holder.brandName = (TextView) reportView.findViewById (R.id.crBName);
            holder.joinedDate = (TextView) reportView.findViewById (R.id.crJDate);
            holder.location = (TextView) reportView.findViewById (R.id.crLocation);
            holder.quantity = (TextView) reportView.findViewById (R.id.crQuantity);
            holder.amount = (TextView) reportView.findViewById (R.id.crAmount);
            reportView.setTag (holder);
        } else {
            holder = (ViewHolder) reportView.getTag ( );
        }

        CustomerReport report = reportList.get (position);

        int index = position + 1;

        holder.no.setText (String.valueOf (index));
        holder.customerName.setText (report.getcName ( ));
        holder.brandName.setText (report.getbName ( ));
        holder.joinedDate.setText (report.getJoinedDate ( ));

        return reportView;
    }

    private class ViewHolder {
        public TextView no, customerName, brandName, joinedDate, location, quantity, amount;
    }
}
