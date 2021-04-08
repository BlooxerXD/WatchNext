package edu.fsu.cs.mobile.watchnext;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class MovieContentProvider extends ContentProvider {
    public final static String DBNAME = "WatchNext";
    public final static String TABLE_WATCHLIST = "movietable";


    private static final int WATCHLIST = 1;
    private static final int MOVIE = 2;
    private static final int WATCHLIST_ID = 3;
    private static final int MOVIE_ID = 4;


    public static final String TABLE_MOVIE = "movie";

    public static final String TM_COLUMN_TITLE = "title";
    public static final String TM_COLUMN_DESC = "description";
    public static final String TM_COLUMN_AVALI = "availability";
    public static final String TM_COLUMN_WATCHNAME = "watchlistname";
    public static final String TM_COLUMN_IMDB = "imdb";
    public static final String TM_COLUMN_NOTES = "notes";
    public static final String TM_COLUMN_TYPE = "type";



    public static final String MOVIE_STRING = "movie";
    public static final String TV_STRING = "tv";



    public static final String AUTHORITY = "edu.fsu.cs.mobile.watchnextprovider";
    public static final Uri CONTENT_URI = Uri.parse(
            "content://edu.fsu.cs.mobile.watchnextprovider/" + TABLE_MOVIE);

    private static final UriMatcher sUriMatcher;
    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, WatchlistContentProvider.TABLE_WATCHLIST,WATCHLIST);
        sUriMatcher.addURI(AUTHORITY, WatchlistContentProvider.TABLE_WATCHLIST+"/#", WATCHLIST_ID);
        sUriMatcher.addURI(AUTHORITY, MovieContentProvider.TABLE_MOVIE, MOVIE);
        sUriMatcher.addURI(AUTHORITY, MovieContentProvider.TABLE_MOVIE+"/#", MOVIE_ID);
    }
    private Contract.MainDatabaseHelper mOpenHelper;

    public static final String SQL_CREATE_MOVIE = "CREATE TABLE " +
            TABLE_MOVIE +
            "(" +
            TM_COLUMN_TITLE   + " PRIMARY KEY TEXT,"+
            TM_COLUMN_DESC    + " TEXT,"+
            TM_COLUMN_AVALI   + " TEXT,"+
            TM_COLUMN_WATCHNAME + " TEXT,"+
            TM_COLUMN_TYPE    + " TEXT,"+
            TM_COLUMN_IMDB    + " TEXT,"+
            TM_COLUMN_NOTES   + " TEXT)";



    @Override
    public boolean onCreate() {
        mOpenHelper = new Contract.MainDatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (sUriMatcher.match(uri)){
            case WATCHLIST:
                return mOpenHelper.getWritableDatabase().query(WatchlistContentProvider.TABLE_WATCHLIST,projection,selection,selectionArgs,null,null,sortOrder);

            case MOVIE:
                return mOpenHelper.getWritableDatabase().query(MovieContentProvider.TABLE_MOVIE,projection,selection,selectionArgs,null,null,sortOrder);
            default: throw new SQLException("Failed to query row into " + uri);
        }

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Uri _uri = null;
        switch (sUriMatcher.match(uri)){
            case WATCHLIST:
                long _ID1 = mOpenHelper.getWritableDatabase().insert(WatchlistContentProvider.TABLE_WATCHLIST, null, values);
                //---if added successfully---
                if (_ID1 > 0) {
                    _uri = ContentUris.withAppendedId(WatchlistContentProvider.CONTENT_URI, _ID1);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            case MOVIE:
                long _ID2 = mOpenHelper.getWritableDatabase().insert(TABLE_MOVIE, null, values);
                //---if added successfully---
                if (_ID2 > 0) {
                    _uri = ContentUris.withAppendedId(MovieContentProvider.CONTENT_URI, _ID2);
                    getContext().getContentResolver().notifyChange(_uri, null);
                }
                break;
            default: throw new SQLException("Failed to insert row into " + uri);
        }
        return _uri;

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        switch (sUriMatcher.match(uri)){
            case WATCHLIST:
                return mOpenHelper.getWritableDatabase().delete(WatchlistContentProvider.TABLE_WATCHLIST, selection, selectionArgs);

            case MOVIE:
                return mOpenHelper.getWritableDatabase().delete(MovieContentProvider.TABLE_MOVIE, selection, selectionArgs);

            default: throw new SQLException("Failed to delete row into " + uri);
        }


    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        switch (sUriMatcher.match(uri)){
            case WATCHLIST:
                return mOpenHelper.getWritableDatabase().update(WatchlistContentProvider.TABLE_WATCHLIST, values, selection, selectionArgs);

            case MOVIE:
                return mOpenHelper.getWritableDatabase().update(MovieContentProvider.TABLE_MOVIE, values, selection, selectionArgs);

            default: throw new SQLException("Failed to update row into " + uri);
        }
    }

}
