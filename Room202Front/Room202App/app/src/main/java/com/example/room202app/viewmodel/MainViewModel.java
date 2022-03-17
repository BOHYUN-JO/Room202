package com.example.room202app.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.room202app.dto.Board;
import com.example.room202app.volley.HttpGet;

import java.util.ArrayList;

import lombok.Getter;

@Getter
public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Board>> boards;
    private MutableLiveData<Boolean> isSuccessGetBoards;
    private Context context = getApplication().getApplicationContext();

    public MainViewModel(@NonNull Application application) {
        super(application);
        boards = new MutableLiveData<ArrayList<Board>>();
        isSuccessGetBoards = new MutableLiveData<>();
    }

    public void httpGetBoards() {
        HttpGet.getBoards(context, boards, isSuccessGetBoards);
    }

}
