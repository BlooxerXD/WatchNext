package edu.fsu.cs.mobile.watchnext;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;

public class TvMovieMain extends AppCompatActivity {
    Contract myContract;
    ArrayList<String> list;
    TV_Movie_List adapter;
    ListView listView;
    String wName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_movie_main);
        myContract = new Contract();
        Intent intent = getIntent();
        list = new ArrayList<String>();


        wName = intent.getStringExtra(MainActivity.WATCHLIST_TAG);

        Intent intent2 = new Intent(TvMovieMain.this, MovieDisplayActivity.class);

        if(wName != null)
            intent2.putExtra("wlname",wName);

        if(wName == null)
            wName = MainActivity.v2;


        if(  myContract.getMovieNames(this,wName).size() <= 0 ) {
            //DISPLAY EMPTY LIST
        }
        else{
            for(int i = 0; i <  myContract.getMovieNames(this,wName).size(); i++){
                list.add( myContract.getMovieNames(this,wName).get(i));
            }
        }

        adapter = new TV_Movie_List(this, R.layout.tv_movie_list_item, list,wName);

        listView = (ListView) findViewById(R.id.list_view2);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String item = list.get(position);

                Intent intent1 = new Intent(TvMovieMain.this, MovieDisplayActivity.class);
                intent1.putExtra("title", item);
                intent1.putExtra(MainActivity.WATCHLIST_TAG,wName);

                startActivity(intent1);
            }
        });

        Button add;
        add = findViewById(R.id.Add_To_List_movie_tv);

        String finalWName = wName;

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TvMovieMain.this, TvMovieActivity.class);

                intent.putExtra("wName", finalWName);

                startActivity(intent);
            }
        });

    }
}