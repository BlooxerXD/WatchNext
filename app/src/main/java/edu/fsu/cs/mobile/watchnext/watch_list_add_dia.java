package edu.fsu.cs.mobile.watchnext;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Collections;

public class watch_list_add_dia extends AppCompatDialogFragment {

    public String name;
    public Contract myContract;
    Contract.MainDatabaseHelper help;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final EditText input = new EditText(getActivity());
        builder.setTitle("Add a Watch List");
        builder.setMessage("Please add the tile of your WatchList");
        builder.setView(input);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = input.getText().toString();
//                Uri mNewUri;
//                ContentValues mNewValues = new ContentValues();
//                mNewValues.put(WatchlistContentProvider.TW_COLUMN_WATCHLISTNAME, name);
//
//                Toast.makeText(getActivity(), name + "hello", Toast.LENGTH_SHORT).show();
//
//                getContext().getContentResolver().insert(WatchlistContentProvider.CONTENT_URI, mNewValues);
//                Collections.copy(MainActivity.watchListNames, myContract.getWatchlistNames(getActivity()));
//
//                WatchList adapt = new WatchList(getActivity(), R.layout.watch_list_item, watchListNames);
//
//                adapt.notifyDataSetChanged();
//                listView.setAdapter(adapt);
            }
        });

        return builder.show();
        //return super.onCreateDialog(savedInstanceState);


    }
}
