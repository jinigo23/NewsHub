/*
package com.newshub.ui_main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.newshub.R;
import com.newshub.helper.AlertAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class PopupDialog extends DialogFragment {

    private ArrayList<String> popupList;

    public PopupDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        String[] popups=getArguments ().getStringArray ("Brands_Popup");
        popupList=new ArrayList<> (Arrays.asList (popups));
        return inflater.inflate (R.layout.popup_window_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        final ListView dialogList=view.findViewById (R.id.dialogList);
//        AlertAdapter adapter=new AlertAdapter (getContext (), )
        AlertDialog.Builder builder = new AlertDialog.Builder (getContext ());
        ArrayAdapter<String> adapter=new ArrayAdapter<String> (getContext (), R.layout.popup_window_view, popupList);
        dialogList.setAdapter (adapter);
        builder.setAdapter (adapter, new DialogInterface.OnClickListener ( ) {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String snack=dialogList.getItemAtPosition (i).toString ();
                Snackbar.make (view.findViewById (android.R.id.content), "Clicked :: "+snack, Snackbar.LENGTH_LONG).setAction ("Action", null).show ();

            }
        });
        builder.setView (view);
        builder.show ();
    }

    */
/*public PopupDialog() {
    }

    public static PopupDialog newInstance(String title) {
        PopupDialog popupDialog=new PopupDialog ();
        Bundle bundle=new Bundle ();
        bundle.putStringArray ("title", new String[]{title});
        popupDialog.setArguments (bundle);
        return popupDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater=LayoutInflater.from (getActivity ());
        View view = layoutInflater.inflate (R.layout.popup_window_view, null);
//        ListView dialogList=view.findViewById (R.id.dialogList);


        AlertDialog.Builder builder = new AlertDialog.Builder (getActivity ( ))
                .setView (view);
        String[] itemList=getArguments ().getStringArray ("Brands");
        ArrayList<String> list=new ArrayList<> (Arrays.asList (itemList));
        ArrayAdapter<String> adapter=new ArrayAdapter<String> (getContext (), android.R.layout.simple_list_item_1, list);
        builder.setAdapter (adapter, new DialogInterface.OnClickListener ( ) {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        return builder.create ();
    }*//*

}
*/
