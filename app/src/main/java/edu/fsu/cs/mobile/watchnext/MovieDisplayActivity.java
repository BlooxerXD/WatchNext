package edu.fsu.cs.mobile.watchnext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MovieDisplayActivity extends AppCompatActivity {
    Contract myContract;
    String watchlistName,title,imdbID;
    TextView title_tv, plot_tv, avalib_tv, type_tv,director,rating;
    RatingBar ratingBar;
    String score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_display);

        ArrayList<String> list = new ArrayList<String>();

        ratingBar = findViewById(R.id.ratingBar);

        title_tv = findViewById(R.id.m_title);
        plot_tv = findViewById(R.id.m_plot);
        avalib_tv = findViewById(R.id.m_avalib);
        type_tv = findViewById(R.id.m_type);
        director = findViewById(R.id.m_directorText);
        rating = findViewById(R.id.m_pg1);



        watchlistName = getIntent().getStringExtra(MainActivity.WATCHLIST_TAG);

        myContract = new Contract();

        title = getIntent().getStringExtra("title");

        imdbID = myContract.getImdbID(this, watchlistName,title);

        avalib_tv.setText(myContract.movieInfo(this,watchlistName,title).get(1));




                JSONObject imdbEntry = new JSONObject();

        try {
            imdbEntry = IMDBapi.GetIMDBEntry(imdbID);
        } catch (IOException e) {
            e.printStackTrace();
        }

        title_tv.setText(title);

        try {
            director.setText(IMDBapi.getDirector(imdbEntry));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            rating.setText(IMDBapi.getRating(imdbEntry));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            score = (IMDBapi.getScore(imdbEntry));

            if(!score.equals("N/A")){
            float transfer = Float.parseFloat(score);
            float finalScore = (float) (( transfer *.2 ) / 5);
            ratingBar.setRating(finalScore);
            }
            else{
                ratingBar.setRating(0);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ratingBar.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        ratingBar.setFocusable(false);

        try {
            type_tv.setText(IMDBapi.getType(imdbEntry));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            plot_tv.setText(IMDBapi.getPlot(imdbEntry));


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                myContract = new Contract();

                myContract.deleteMovie(this,watchlistName,title);

                Intent intent = new Intent(MovieDisplayActivity.this, TvMovieMain.class);
                startActivity(intent);

                return true;
        }

        return  true;



    }

}