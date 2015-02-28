package com.example.izmir.labb;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by Izmir on 2015-02-26.
 */
public class MyDialog extends DialogFragment {

private int picked =1;
    public static final String SORT_SELECT = "sort";
    final CharSequence[] list = {"Id","Title","Rating"};
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        SharedPreferences prefs = getActivity().getPreferences(Activity.MODE_PRIVATE);
        picked = getActivity().getPreferences(Activity.MODE_PRIVATE).getInt(SORT_SELECT, 1);



        builder.setTitle("Sort by:").setSingleChoiceItems(list, picked, new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {

/*
        switch (which){

            case 0:
                break;
            case 1:
                break;

            case 2:
                break;





        }

*/

        picked = which;

    }
}).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int id) {
        // User clicked OK, so save the mSelectedItems results somewhere
        // or return them to the component that opened the dialog
        SharedPreferences prefs = getActivity().getPreferences(Activity.MODE_PRIVATE);

        prefs.edit().putInt(SORT_SELECT, picked).commit();

((ItemListActivity)getActivity()).pickedSort(picked);



    }
});


        return builder.create();
    }


}
