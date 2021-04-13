package edu.fsu.cs.mobile.watchnext;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Contract myContract;
    Button add;
    public static final String WATCHLIST_TAG = "watchlistname";
    public static List<String> watchListNames;
    String name;
    public static  String v2;
    public static ListView listView;
    boolean flag = false;
    WatchList adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
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
       adapter.notifyDataSetChanged();
         listView.setAdapter(adapter);

      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              String item = watchListNames.get(position);

              v2= item;
              Intent intent3 = new Intent(MainActivity.this,MovieDisplayActivity.class);
              intent3.putExtra("w1",v2);

              Intent intent = new Intent(MainActivity.this, TvMovieMain.class);
              intent.putExtra(WATCHLIST_TAG,item);
              startActivity(intent);
          }
      });

    }

    public void openDialoge (){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        builder.setTitle("Add/Delete Watchlist");
        builder.setMessage("Please select the tile of your WatchList that you'd like to add/delete");
        builder.setView(input);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = input.getText().toString();
                /* Add item to the list */
                adapter.notifyDataSetChanged();
                /*input new list name into the database */
                if(myContract.addNewWatchlist(getApplicationContext(),name))
                    watchListNames.add(name);
            }
        }).create();

        builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = input.getText().toString();
                myContract.deleteWatchList(getApplicationContext(),name);

                for(int i = 0; i < watchListNames.size();i++){
                    if( watchListNames.get(i).toString().equals(name)){
                        watchListNames.remove(i);
                    }
                }

                adapter.notifyDataSetChanged();
            }
        }).create();

       builder.show();

    }
}