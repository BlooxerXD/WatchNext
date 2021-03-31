package edu.fsu.cs.mobile.watchnext;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WatchlistContentProvider {
    public final static String DBNAME = "WatchNext";
    public final static String TABLE_WATCHLIST = "watchlisttable";
    public final static String TW_COLUMN_WATCHLISTNAME = "watchlistname";
    public final static String TW_COLUMN_WATCHLISTID = "watchlistid";

    public static final String AUTHORITY = "edu.fsu.cs.mobile.watchnextprovider";
    public static final Uri CONTENT_URI = Uri.parse("content://edu.fsu.cs.mobile.watchnextprovider/" + TABLE_WATCHLIST);

    public static final String SQL_CREATE_WATCHLIST= "CREATE TABLE " +
            TABLE_WATCHLIST +  // Table's name
            "(" +               // The columns in the table
            TW_COLUMN_WATCHLISTID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            TW_COLUMN_WATCHLISTNAME + " TEXT)";


}
