package edu.fsu.cs.mobile.watchnext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Contract myContract;

    List<String> watchListNames;

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         myContract = new Contract();
         watchListNames = new ArrayList<>();// myContract.getWatchlistNames(this);

        Collections.copy(watchListNames,myContract.getWatchlistNames(this));

         listView = (ListView) findViewById(R.id.list_view);

         //watchListNames.add("Pickles");

         WatchList adapter = new WatchList(this,R.layout.watch_list_item,watchListNames);

        listView.setAdapter(adapter);

    }
}