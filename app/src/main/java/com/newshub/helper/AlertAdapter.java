package com.newshub.helper;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.newshub.R;

import java.util.ArrayList;
import java.util.Arrays;

public class AlertAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final ArrayList<String> popupList;

    public AlertAdapter(@NonNull Context context, ArrayList<String> popupList) {
        super (context, 0, popupList);
        this.context=context;
        this.popupList=popupList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View alertView=convertView;
        ViewHolder holder;

        if (alertView == null) {
            alertView= LayoutInflater.from (context).inflate (R.layout.popup_window_view, parent, false);
            holder=new ViewHolder ();
            holder.popupName=(TextView)alertView.findViewById (R.id.popupName);
            alertView.setTag (holder);
        }else {
            holder=(ViewHolder)alertView.getTag ();
        }
        String alert=popupList.get (position);
        for (int i=0;i<popupList.size ();i++){
            holder.popupName.setText (alert);
        }
        return alertView;
    }

    private class ViewHolder {
        public TextView popupName;
    }
}
