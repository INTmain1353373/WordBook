package com.example.wordbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RightFragment extends Fragment {
    private TextView word_name = null;
    private TextView word_pro = null;
    private TextView word_chinese = null;
    private Word word = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.right_fragment, container, false);
        word_name = (TextView) view.findViewById(R.id.word_name);
        word_pro = (TextView) view.findViewById(R.id.word_pro);
        word_chinese = (TextView) view.findViewById(R.id.word_chinese);
        //word_name.setText("lihang");
        word_name.setText("显示单词信息");
        return  view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void refresh(Word word){
        word_name.setText("单词：" + word.getWord());
        word_pro.setText("发音：" + word.getPronunciation());
        word_chinese.setText("含义：" + word.getMeaning());
    }



}
