package edu.fsu.cs.mobile.watchnext;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Contract myContract;
    Button add;
    public static List<String> watchListNames;
    String name;
    public static ListView listView;
    boolean flag = false;
    WatchList adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myContract = new Contract();

        watchListNames = new ArrayList<String>();

        if(myContract.getWatchlistNames(this).size() > 0){
           // Collections.copy(watchListNames, myContract.getWatchlistNames(this));
            for( int i = 0; i < myContract.getWatchlistNames(this).size(); i++){
                watchListNames.add(myContract.getWatchlistNames(this).get(i));
            }
        }

        listView = (ListView) findViewById(R.id.list_view);

        add = (Button) findViewById(R.id.Add_To_List);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            openDialoge();
            }
    });
         adapter = new WatchList(this, R.layout.watch_list_item, watchListNames);
        listView.setAdapter(adapter);
    }

    public void openDialoge (){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        builder.setTitle("Add a Watch List");
        builder.setMessage("Please add the tile of your WatchList");
        builder.setView(input);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = input.getText().toString();
                /* Add item to the list */
                watchListNames.add(name);
                adapter.notifyDataSetChanged();

                /*input new list name into the database */

                ContentValues mNewValues = new ContentValues();
                mNewValues.put(WatchlistContentProvider.TW_COLUMN_WATCHLISTNAME,name);
                getContentResolver().insert(Uri.parse(String.valueOf(WatchlistContentProvider.CONTENT_URI)), mNewValues);



                Toast.makeText(MainActivity.this, "item added", Toast.LENGTH_SHORT).show();
            }
        }).create();

       builder.show();

    }
}