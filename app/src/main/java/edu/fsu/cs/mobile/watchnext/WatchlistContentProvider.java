package edu.fsu.cs.mobile.watchnext;

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

public class WatchlistContentProvider extends ContentProvider {
    public final static String DBNAME = "NameDatabase";
    public final static String TABLE_WATCHLIST = "namestable";
    public final static String TW_COLUMN_WATCHLISTNAME = "watchlistname";
    public final static String TW_COLUMN_WATCHLISTID = "watchlistid";

    public static final String AUTHORITY = "edu.fsu.cs.mobile.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://edu.fsu.cs.mobile.provider/" + TABLE_WATCHLIST);


    private static UriMatcher sUriMatcher;

    private MyContentProvider.MainDatabaseHelper mOpenHelper;

    private static final String SQL_CREATE_WATCHLIST= "CREATE TABLE " +
            TABLE_WATCHLIST +  // Table's name
            "(" +               // The columns in the table
            " _ID INTEGER PRIMARY KEY, " +
            TW_COLUMN_WATCHLISTID + " TEXT," +
            TW_COLUMN_WATCHLISTNAME + " TEXT)";




    @Override
    public boolean onCreate() {
        mOpenHelper = new MyContentProvider.MainDatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return mOpenHelper.getReadableDatabase().query(TABLE_WATCHLIST,projection,selection,selectionArgs,null,null,sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {


        long id = mOpenHelper.getWritableDatabase().insert(TABLE_WATCHLIST, null, values);

        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().delete(TABLE_WATCHLIST, selection, selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return mOpenHelper.getWritableDatabase().update(TABLE_WATCHLIST, values, selection, selectionArgs);
    }

    protected static final class MainDatabaseHelper extends SQLiteOpenHelper {
        MainDatabaseHelper(Context context) {
            super(context, DBNAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_WATCHLIST);

        }

        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
    }
}
