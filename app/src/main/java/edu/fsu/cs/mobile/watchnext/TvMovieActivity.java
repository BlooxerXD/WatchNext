package edu.fsu.cs.mobile.watchnext;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class TvMovieActivity extends AppCompatActivity {
    public static ListView listView;
    public static List<String> TvMovieListNames;
    TV_Movie_List adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_movie);


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
                String name = input.getText().toString();
                /* Add item to the list */
                TvMovieListNames.add(name);
                adapter.notifyDataSetChanged();

                /*input new list name into the database */

                ContentValues mNewValues = new ContentValues();
                mNewValues.put(WatchlistContentProvider.TW_COLUMN_WATCHLISTNAME,name);
                getContentResolver().insert(Uri.parse(String.valueOf(WatchlistContentProvider.CONTENT_URI)), mNewValues);



                Toast.makeText(TvMovieActivity.this, "item added", Toast.LENGTH_SHORT).show();
            }
        }).create();

        builder.show();

    }
}