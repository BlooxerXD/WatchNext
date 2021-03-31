package edu.fsu.cs.mobile.watchnext;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CursorAdapter;
import android.widget.Toast;

public class Contract {
    Cursor mCursor;
    CursorAdapter mCursorAdapter;

    String[] mProjection;
    String[] mListColumns;
    String mSelectionClause;
    String[] mSelectionArgs;
    String mOrderBy;

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

    public String[] getWatchlistNames(Context context){
        String [] array = new String[]{"List1","List2","List3"};

        return array;
    }

    protected static final class MainDatabaseHelper extends SQLiteOpenHelper {
        MainDatabaseHelper(Context context) {
            super(context, WatchlistContentProvider.DBNAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(WatchlistContentProvider.SQL_CREATE_WATCHLIST);

        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
    }
}
