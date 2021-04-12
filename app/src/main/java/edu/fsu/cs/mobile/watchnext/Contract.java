package edu.fsu.cs.mobile.watchnext;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Movie;
import android.util.Log;
import android.widget.CursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class Contract {

    public Contract(){

    }
    Cursor mCursor;
    CursorAdapter mCursorAdapter;

    String[] mProjection;
    String[] mListColumns;
    String mSelectionClause;
    String[] mSelectionArgs;
    String mOrderBy;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    /////////////// WATCHLIST SECTION
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public boolean watchlistnameExist(Context context, String watchlist_name){
        mProjection = new String[]{
                WatchlistContentProvider.TW_COLUMN_WATCHLISTNAME
        };


        mSelectionClause = WatchlistContentProvider.TW_COLUMN_WATCHLISTNAME +" = ? ";
        mSelectionArgs = new String[] { watchlist_name };

        mCursor = context.getContentResolver().query(
                WatchlistContentProvider.CONTENT_URI,
                mProjection,
                mSelectionClause,
                mSelectionArgs,
                null);


        return mCursor.getCount() != 0;
        // If name Exist return True
        //Else Return False

    }

    private String CleanString(String str){
        return str.trim();
    }



    public boolean addNewWatchlist(Context context, String watchlist_name){

        if(!watchlistnameExist(context, watchlist_name)){
            ContentValues values = new ContentValues();
            values.put(WatchlistContentProvider.TW_COLUMN_WATCHLISTNAME, CleanString(watchlist_name));
            context.getContentResolver().insert(WatchlistContentProvider.CONTENT_URI, values);
            Toast.makeText(context, "Watchlist Added!", Toast.LENGTH_SHORT).show();
            return true;
        }

        Toast.makeText(context, "Watchlist Added Fail!", Toast.LENGTH_SHORT).show();
        return false;
    }

    public ArrayList<String> getWatchlistNames(Context context){
        ArrayList<String> array = new ArrayList<String>();

        mProjection = new String[]{
                WatchlistContentProvider.TW_COLUMN_WATCHLISTNAME
        };


        mCursor = context.getContentResolver().query(
                WatchlistContentProvider.CONTENT_URI,
                mProjection,
                null,
                null,
                null);
        while(mCursor.moveToNext()){
            array.add(mCursor.getString(mCursor.getColumnIndex(WatchlistContentProvider.TW_COLUMN_WATCHLISTNAME))); //add the item
        }
        mCursor.close();
        return array;
    }

    public boolean deleteWatchList(Context context, String watchlist_name){

        if(watchlistnameExist(context, watchlist_name)) {
            mSelectionClause = WatchlistContentProvider.TW_COLUMN_WATCHLISTNAME + " = ? ";
            mSelectionArgs = new String[]{watchlist_name};
            context.getContentResolver().delete(WatchlistContentProvider.CONTENT_URI, mSelectionClause, mSelectionArgs);
            Toast.makeText(context, "Watchlist Deleted!", Toast.LENGTH_SHORT).show();
            return true;
        }
        Toast.makeText(context, "Watchlist Delete Failed!", Toast.LENGTH_SHORT).show();
        return false;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    /////////////// MOVIE/TV SECTION
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public boolean MovieExist(Context context, String watchlist_name, String title){
        mProjection = new String[]{
                MovieContentProvider.TM_COLUMN_TITLE
        };


        mSelectionClause = MovieContentProvider.TM_COLUMN_WATCHNAME +" = ? AND "+
                           MovieContentProvider.TM_COLUMN_TITLE +" = ?";

        mSelectionArgs = new String[] { watchlist_name, title };

        mCursor = context.getContentResolver().query(
                MovieContentProvider.CONTENT_URI,
                mProjection,
                mSelectionClause,
                mSelectionArgs,
                null);

        return mCursor.getCount() != 0;
        // If name Exist return True
        //Else Return False

    }

    public ArrayList<String> movieInfo(Context context,String watchlist_name,String title){
        ArrayList<String> array = new ArrayList<String>();
        mProjection = new String[]{
          MovieContentProvider.TM_COLUMN_TITLE,MovieContentProvider.TM_COLUMN_DESC,MovieContentProvider.TM_COLUMN_AVALI,MovieContentProvider.TM_COLUMN_IMDB
        };

        mSelectionClause = MovieContentProvider.TM_COLUMN_WATCHNAME +" = ? AND "+
                MovieContentProvider.TM_COLUMN_TITLE +" = ?";

        mSelectionArgs = new String[] { watchlist_name, title };

        mCursor = context.getContentResolver().query(
                MovieContentProvider.CONTENT_URI,
                mProjection,
                mSelectionClause,
                mSelectionArgs,
                null);

        while(mCursor.moveToNext()){
            array.add(mCursor.getString(mCursor.getColumnIndex(MovieContentProvider.TM_COLUMN_TITLE))); //add the item
            array.add(mCursor.getString(mCursor.getColumnIndex(MovieContentProvider.TM_COLUMN_DESC)));
            array.add(mCursor.getString(mCursor.getColumnIndex(MovieContentProvider.TM_COLUMN_AVALI)));
            array.add(mCursor.getString(mCursor.getColumnIndex(MovieContentProvider.TM_COLUMN_IMDB)));
        }


        return array;
    }

    public boolean addNewMovie(Context context,
                               String watchlist_name,
                               String title,
                               String desc,
                               String avalib,
                               String imdb,
                               String notes,
                               String type){
        if(!MovieExist(context,watchlist_name,title)){
            ContentValues values = new ContentValues();
            values.put(MovieContentProvider.TM_COLUMN_TITLE,CleanString(title));
            values.put(MovieContentProvider.TM_COLUMN_DESC,CleanString(desc));
            values.put(MovieContentProvider.TM_COLUMN_AVALI,CleanString(avalib));
            values.put(MovieContentProvider.TM_COLUMN_WATCHNAME,CleanString(watchlist_name));
            values.put(MovieContentProvider.TM_COLUMN_TYPE,CleanString(type));
            values.put(MovieContentProvider.TM_COLUMN_IMDB,CleanString(imdb));
            values.put(MovieContentProvider.TM_COLUMN_NOTES,CleanString(notes));

            context.getContentResolver().insert(MovieContentProvider.CONTENT_URI,values);
            Toast.makeText(context, "Movie Added to "+watchlist_name+"!", Toast.LENGTH_SHORT).show();


            return true;
        }else{
            return false;
        }



    }


    public ArrayList<String> getMovieNames(Context context, String watchlist_name){
        ArrayList<String> array = new ArrayList<String>();
        mSelectionClause = MovieContentProvider.TM_COLUMN_WATCHNAME + " = ? ";
        mSelectionArgs = new String[]{watchlist_name};

        mProjection = new String[]{
                MovieContentProvider.TM_COLUMN_TITLE
        };


        mCursor = context.getContentResolver().query(
                MovieContentProvider.CONTENT_URI,
                mProjection,
                mSelectionClause,
                mSelectionArgs,
                null);
        while(mCursor.moveToNext()){
            array.add(mCursor.getString(mCursor.getColumnIndex(MovieContentProvider.TM_COLUMN_TITLE))); //add the item

        }
        mCursor.close();

        return array;
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////
    /////////////// DB Helper SECTION
    ///////////////////////////////////////////////////////////////////////////////////////////////
    protected static final class MainDatabaseHelper extends SQLiteOpenHelper {
        MainDatabaseHelper(Context context) {
            super(context, WatchlistContentProvider.DBNAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(MovieContentProvider.SQL_CREATE_MOVIE);
            db.execSQL(WatchlistContentProvider.SQL_CREATE_WATCHLIST);

        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
    }
}
