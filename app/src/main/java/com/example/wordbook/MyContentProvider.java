package com.example.wordbook;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MyContentProvider extends ContentProvider {
    private static SQLiteDatabase database = null;
    public static final String AUTHORITY = "com.example.wordbook.provider";
    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        //throw new UnsupportedOperationException("Not yet implemented");
        int deletedRows = 0;
        deletedRows = database.delete("englishwords", selection, selectionArgs);
        return deletedRows;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        long newId = database.insert("englishwords", null, values);
        Uri returnUri = Uri.parse("content://" + AUTHORITY + "englishwords/" + newId);
        return returnUri;
        //throw new UnsupportedOperationException("Not yet implemented");
    }



    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        database = SQLiteDatabase.openOrCreateDatabase(SQLitedb.DB_PATH + "/" + SQLitedb.DB_NAME, null);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        //throw new UnsupportedOperationException("Not yet implemented");
        Cursor cursor = null;
        cursor = database.query("englishwords", projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        //throw new UnsupportedOperationException("Not yet implemented");
        int updateRows = 0;
        updateRows = database.update("englishwords", values, selection, selectionArgs);

        return updateRows;

    }


}
