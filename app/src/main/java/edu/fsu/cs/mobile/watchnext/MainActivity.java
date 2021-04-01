package edu.fsu.cs.mobile.watchnext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         myContract = new Contract();

        watchListNames = new ArrayList<>();// myContract.getWatchlistNames(this);

//        if(myContract.getWatchlistNames(this).size() == 0 ){
//            watchListNames.add("Pickles");
//        }
//        else{

        //}
        listView = (ListView) findViewById(R.id.list_view);

         WatchList adapter = new WatchList(this,R.layout.watch_list_item,watchListNames);

        listView.setAdapter(adapter);
        add = (Button) findViewById(R.id.Add_To_List);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialoge();
                flag = true;
            }

    });
        if(flag) {
//            Uri mNewUri;
//            ContentValues mNewValues = new ContentValues();
//            mNewValues.put(WatchlistContentProvider.TW_COLUMN_WATCHLISTNAME, name);
//
//            Toast.makeText(getApplicationContext(), name + "hello", Toast.LENGTH_SHORT).show();
//
//            getContentResolver().insert(WatchlistContentProvider.CONTENT_URI, mNewValues);
//            Collections.copy(watchListNames, myContract.getWatchlistNames(getApplicationContext()));
//
//            WatchList adapt = new WatchList(getApplicationContext(), R.layout.watch_list_item, watchListNames);
//
//            adapt.notifyDataSetChanged();
//            listView.setAdapter(adapt);
//            flag = false;
        }
    }
    public void openDialoge (){

        watch_list_add_dia watchListAddDia = new watch_list_add_dia();
        watchListAddDia.show(getSupportFragmentManager(),"Add");

    }
}