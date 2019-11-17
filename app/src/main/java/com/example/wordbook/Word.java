package com.example.wordbook;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Word  implements Serializable {
    String word;
    String pronunciation;
    String meaning;

    public Word(){};

    public Word(String word, String pronunciation, String meaning){
        this.word = word;
        this.pronunciation = pronunciation;
        this.meaning = meaning;
    }

    public void setWord(String word){
        this.word = word;
    }


    public void setPronunciation(String pronunciation){
        this.pronunciation = pronunciation;
    }

    public void setMeaning(String meaning){
        this.meaning = meaning;
    }

    public String getWord(){
        return  word;
    }

    public String getPronunciation(){
        return pronunciation;
    }

    public String getMeaning(){
        return meaning;
    }

    @NonNull
    @Override
    public String toString() {
        return "word:" + this.word + " pronunciation:" + this.pronunciation + " meaning:" + meaning;
    }
}
