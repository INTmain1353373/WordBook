package com.example.wordbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SQLitedb {
    private final int BUFFER_SIZE = 400000;
    public static final String DB_NAME = "englishword.db";
    public static final String PACKAGE_NAME = "com.example.wordbook";
    public static final String DB_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + PACKAGE_NAME + "/databases";
    private SQLiteDatabase database;
    private Context context;

    public SQLitedb(Context context) {
        this.context = context;
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }

    public void openDatabase() {
        Log.e("create",DB_PATH + "/" + DB_NAME);
        this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
    }

    public SQLiteDatabase openDatabase(final String dbfile) {
        new Thread() {

            public void run() {
                Log.e("线程", "start");
                if ((new File(DB_PATH + "/" + DB_NAME)).exists() == false) {
                    File f = new File(DB_PATH);
                    if (!f.exists()) {
                        f.mkdir();
                        f.setWritable(true,false);
                        f.setReadable(true, false);
                        f.setExecutable(true,false);
                        Log.e("create folder","creae");
                    }
                    try {
                        InputStream is = context.getAssets().open(DB_NAME);
                        OutputStream os = new FileOutputStream(DB_PATH + "/" + DB_NAME);

                        byte[] buffer = new byte[10000000];
                        int length;
                        System.out.println("reading start!");
                        while ((length = is.read(buffer)) > 0) {
                            os.write(buffer, 0, length);
                            System.out.println(buffer);
                            Log.e("read", String.valueOf(length));
                        }
                        System.out.println("reading end!");
                        os.flush();
                        os.close();
                        is.close();
                    } catch (FileNotFoundException e) {
                        Log.e("Database", "File not found");
                        e.printStackTrace();
                    } catch (IOException e) {
                        Log.e("Database", "IO exception");
                        e.printStackTrace();
                    }
                }

            }

        }.start();
        Log.e("线程", "end");
        if (new File(dbfile).exists()) {
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
            return db;
        }
        return null;
    }

    public void closeDatabase(){
        if(this.database != null){
            this.database.close();
            this.database = null;
        }

    }

}
