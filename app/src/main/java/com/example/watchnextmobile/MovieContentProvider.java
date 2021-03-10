package com.example.watchnextmobile;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MovieContentProvider extends ContentProvider {
    public final static String DBNAME = "NameDatabase";
    public final static String TABLE_WATCHLIST = "namestable";




    public static final String TABLE_MOVIE = "movie";
    public static final String TM_COLUMN_TITLE = "title";
    public static final String TM_COLUMN_DESC = "description";
    public static final String TM_COLUMN_AVALI = "availability";
    public static final String TM_COLUMN_WATCHID = "id";
    public static final String TM_COLUMN_IMDB = "imdb";
    public static final String TM_COLUMN_NOTES = "notes";



    public static final String AUTHORITY = "edu.fsu.cs.mobile.provider";
    public static final Uri CONTENT_URI = Uri.parse(
            "content://edu.fsu.cs.mobile.provider/" + TABLE_MOVIE);

    private static UriMatcher sUriMatcher;

    private MyContentProvider.MainDatabaseHelper mOpenHelper;

    private static final String SQL_CREATE_MOVIE = "CREATE TABLE " +
            TABLE_MOVIE +
            "(" +
            " _ID INTEGER PRIMARY KEY, " +
            TM_COLUMN_TITLE   + " TEXT,"+
            TM_COLUMN_DESC    + " TEXT,"+
            TM_COLUMN_AVALI   + " TEXT,"+
            TM_COLUMN_WATCHID + " TEXT,"+
            TM_COLUMN_IMDB    + " TEXT,"+
            TM_COLUMN_NOTES   + " TEXT)";



    @Override
    public boolean onCreate() {
        mOpenHelper = new MyContentProvider.MainDatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return mOpenHelper.getReadableDatabase().query(TABLE_MOVIE,projection,selection,selectionArgs,null,null,sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {


        long id = mOpenHelper.getWritableDatabase().insert(TABLE_MOVIE, null, values);

        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().delete(TABLE_MOVIE, selection, selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().update(TABLE_MOVIE, values, selection, selectionArgs);
    }

    protected static final class MainDatabaseHelper extends SQLiteOpenHelper {
        MainDatabaseHelper(Context context) {
            super(context, DBNAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_MOVIE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
    }
}
