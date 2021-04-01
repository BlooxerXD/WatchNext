package edu.fsu.cs.mobile.watchnext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TV_Movie_List extends ArrayAdapter<String> implements AdapterView.OnItemClickListener  {

    //List of the watchlist

    List<String> tvMovieWatchList;

    Context context;

    int resource;


    public TV_Movie_List(@NonNull Context context, int resource, List<String> watchList) {

        super(context, resource,watchList);
        //Sets values
        this.context  = context;
        this.resource = resource;
        this.tvMovieWatchList = watchList;
    }

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(resource, null, false);

        String item = tvMovieWatchList.get(position);

        TextView textview = view.findViewById(R.id.watch_list_item_view);
        textview.setText(item);

        return view;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}


