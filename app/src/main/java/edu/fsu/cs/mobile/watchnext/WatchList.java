package edu.fsu.cs.mobile.watchnext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class WatchList extends ArrayAdapter<String> implements View.OnClickListener {

    //List of the watchlist

    List<String> watchList;

    Context context;

    int resource;


    public WatchList(@NonNull Context context, int resource,List<String> watchList) {

        super(context, resource,watchList);
        //Sets values
        this.context  = context;
        this.resource = resource;
        this.watchList = watchList;
    }

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(resource, null, false);

        String item = watchList.get(position);

        TextView textview = view.findViewById(R.id.watch_list_item_view);
        textview.setText(item);

        return view;

    }


    @Override
    public void onClick(View v) {

    }

}
