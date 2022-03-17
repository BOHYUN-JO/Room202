package com.example.room202app.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.room202app.dto.Article;
import com.example.room202app.volley.HttpGet;
import com.example.room202app.volley.HttpPost;

import java.util.ArrayList;

import lombok.Getter;

@Getter
public class BoardViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Article>> articles;
    private MutableLiveData<Long> totalPages;
    private MutableLiveData<Boolean> isSuccessGetArticles;
    private MutableLiveData<Boolean> isSuccessPostArticle;
    private Long page;
    private Context context = getApplication().getApplicationContext();

    public BoardViewModel(@NonNull Application application) {
        super(application);
        articles = new MutableLiveData<ArrayList<Article>>();
        totalPages = new MutableLiveData<Long>();
        isSuccessGetArticles = new MutableLiveData<>();
        isSuccessPostArticle=new MutableLiveData<>();
    }


    public void httpGetArticles(Long boardId, Long page) {
        this.page=page;
        HttpGet.getArticles(context, boardId, page, articles, totalPages, isSuccessGetArticles);
    }

    public void httpPostArticle(Article article)
    {
        HttpPost.postArticle(context, article, isSuccessPostArticle);
    }

    public MutableLiveData<ArrayList<Article>> getArticles() {
        return articles;
    }
}
