package com.example.wordbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public SQLitedb sqldb;
    public static SQLiteDatabase database = null;
    private EditText editText = null;
    private ListView listView = null;
    Word word = null;
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
        }

            database = SQLiteDatabase.openOrCreateDatabase(SQLitedb.DB_PATH + "/" + SQLitedb.DB_NAME, null);

            editText  = (EditText) findViewById(R.id.Search_text);
            listView = (ListView) findViewById(R.id.wordList);

            String sql = "select * from englishwords where word = 'a'";
            //database.execSQL(sql);
            String[] data = null;
            Word word = null;
            String[] li = null;
            Cursor cursor = database.rawQuery(sql, data);
            while (cursor.moveToNext()){
                word = new Word(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                Log.e("word", word.toString());
                li = new String[1];
                li[0] = word.getWord();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, li);
            listView.setAdapter(adapter);

            listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

    }

}
