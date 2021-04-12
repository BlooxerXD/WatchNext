package edu.fsu.cs.mobile.watchnext;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class TvMovieMain extends AppCompatActivity {
    Contract myContract;
    ArrayList<String> list;
    TV_Movie_List adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_movie_main);
        myContract = new Contract();
        Intent intent = getIntent();
        list = new ArrayList<String>();

        String wName = intent.getStringExtra("watchlistname");

        if(  myContract.getMovieNames(this,wName).size() <= 0 ) {
            //DISPLAY EMPTY LIST
        }
        else{
            for(int i = 0; i <  myContract.getMovieNames(this,wName).size(); i++){
                list.add( myContract.getMovieNames(this,wName).get(i));
            }
        }

        adapter = new TV_Movie_List(this, R.layout.tv_movie_list_item, list);

        listView = (ListView) findViewById(R.id.list_view2);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Button add;
        add = findViewById(R.id.Add_To_List_movie_tv);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TvMovieMain.this, TvMovieActivity.class);
                intent.putExtra("wName",wName);
                startActivity(intent);
            }
        });

    }
}