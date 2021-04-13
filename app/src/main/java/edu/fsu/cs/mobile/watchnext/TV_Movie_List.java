package edu.fsu.cs.mobile.watchnext;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TV_Movie_List extends ArrayAdapter<String> implements AdapterView.OnItemClickListener  {

    //List of the watchlist

    List<String> tvMovieWatchList;

    Context context;

    int resource;
    String wName;


    public TV_Movie_List(@NonNull Context context, int resource, List<String> watchList,String watchlist_name) {

        super(context, resource,watchList);
        //Sets values
        this.context  = context;
        this.resource = resource;
        this.tvMovieWatchList = watchList;
        this.wName =watchlist_name;
    }

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(resource, null, false);

        String item = tvMovieWatchList.get(position);

        TextView textview = view.findViewById(R.id.tv_movie_name_text_view);
        ImageView poster = view.findViewById(R.id.imageView_poster);
        Contract myContract = new Contract();

        String imdbID = myContract.getImdbID(getContext(), wName,item);
        String posterlink ="";
        try {
            JSONObject imdbentry = IMDBapi.GetIMDBEntry(imdbID);
            posterlink = IMDBapi.getPoster(imdbentry);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        if(!posterlink.equals("")) {
            new DownloadImageTask((ImageView) poster)
                    .execute(posterlink);
        }
        textview.setText(item);

        return view;

    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}


