package com.example.wordbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public SQLitedb sqldb;
    public static SQLiteDatabase database = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            sqldb = new SQLitedb(this);
            sqldb.openDatabase();
            sqldb.closeDatabase();
            database = SQLiteDatabase.openOrCreateDatabase(SQLitedb.DB_PATH + "/" + SQLitedb.DB_NAME, null);
        }else {
            sqldb = new SQLitedb(this);
            sqldb.openDatabase();
            sqldb.closeDatabase();
            database = SQLiteDatabase.openOrCreateDatabase(SQLitedb.DB_PATH + "/" + SQLitedb.DB_NAME, null);
        }
    }

}
