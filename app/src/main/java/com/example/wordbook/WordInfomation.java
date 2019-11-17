package com.example.wordbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class WordInfomation extends AppCompatActivity {

    public static SQLiteDatabase database = null;
    private TextView word_name = null;
    private TextView word_pro = null;
    private TextView word_chinese = null;
    private Word word = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_infomation);
        word_name = (TextView) findViewById(R.id.word_name);
        word_pro = (TextView) findViewById(R.id.word_pro);
        word_chinese = (TextView) findViewById(R.id.word_chinese);
        Intent intent = getIntent();
        word = (Word) intent.getSerializableExtra("word");
        //Log.e("word = " , word.toString());

        word_name.setText("单词：" + word.getWord());
        word_pro.setText("发音：" + word.getPronunciation());
        word_chinese.setText("含义：" + word.getMeaning());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.update_item:
                AlertDialog.Builder update_dialog = new AlertDialog.Builder(WordInfomation.this);
                update_dialog.setTitle("修改单词");
                update_dialog.setCancelable(false);
                LayoutInflater inflater = getLayoutInflater();
                final View layout = inflater.inflate(R.layout.update_alertdialog,
                        null);
                update_dialog.setView(layout);

                final TextView word_name1 = (TextView) layout.findViewById(R.id.word_name);
                final EditText word_pro1 = (EditText) layout.findViewById(R.id.word_pro);
                final EditText word_chinese1 = (EditText) layout.findViewById(R.id.word_chinese);

                word_name1.setText(word.getWord());
                word_pro1.setText(word.getPronunciation());
                word_chinese1.setText(word.getMeaning());



                update_dialog.setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database = SQLiteDatabase.openOrCreateDatabase(SQLitedb.DB_PATH + "/" + SQLitedb.DB_NAME, null);
                        String sql = "update englishwords set pronunciation = ?, meaning = ? where word = ?";

                        database.execSQL(sql, new String[]{word_pro1.getText().toString(), word_chinese1.getText().toString(), word_name1.getText().toString()});

                        //word_name.setText(word_name1.getText().toString());
                        word_pro.setText("发音：" + word_pro1.getText().toString());
                        word_chinese.setText("含义：" + word_chinese1.getText().toString());

                    }
                });

                update_dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                update_dialog.show();
                break;



            case R.id.delete_item:
                AlertDialog.Builder delete_dialog = new AlertDialog.Builder(WordInfomation.this);
                delete_dialog.setTitle("删除提示框");
                delete_dialog.setCancelable(false);
                delete_dialog.setMessage("确认删除吗");
                delete_dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database = SQLiteDatabase.openOrCreateDatabase(SQLitedb.DB_PATH + "/" + SQLitedb.DB_NAME, null);
                        String sql = "delete from englishwords where word = ?";
                        //word_name = (EditText) findViewById(R.id.word_name);
                        Intent intent = getIntent();
                        word = (Word) intent.getSerializableExtra("word");
                        Log.e("delete",word.getWord());
                        database.execSQL(sql, new String[]{word.getWord()});
                        Intent intent0 = new Intent(WordInfomation.this, MainActivity.class);
                        startActivity(intent0);
                    }
                });
                delete_dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                delete_dialog.show();
            default:
                break;
        }
        return true;
    }
}
