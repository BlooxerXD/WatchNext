package edu.fsu.cs.mobile.watchnext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MovieDisplayActivity extends AppCompatActivity {
    Contract myContract;
    String watchlistName,title;
    TextView d1,d2,d3,d4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_display);
        d1 = findViewById(R.id.display1);
        d2 = findViewById(R.id.display2);
        d3 = findViewById(R.id.display3);
        d4 = findViewById(R.id.display4);



        watchlistName = getIntent().getStringExtra("wlname");

        if(watchlistName == null){
            watchlistName = getIntent().getStringExtra("w1");
        }

        title = getIntent().getStringExtra("title");

        myContract = new Contract();

        ArrayList<String> list = new ArrayList<String>();

        for( int i = 0; i < myContract.movieInfo(this, MainActivity.v2,title).size();i++ ) {

            list.add(myContract.movieInfo(this, MainActivity.v2,title).get(i));

        }
        d1.setText(list.get(0));
        d2.setText(list.get(1));
        d3.setText(list.get(2));
        d4.setText(list.get(3));

        Toast.makeText(this, list.get(0), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, list.get(1), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, list.get(2), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, list.get(3), Toast.LENGTH_SHORT).show();



    }
}