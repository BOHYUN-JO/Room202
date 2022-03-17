package com.example.room202app.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.room202app.dto.Article;
import com.example.room202app.dto.Comment;
import com.example.room202app.volley.HttpDelete;
import com.example.room202app.volley.HttpGet;
import com.example.room202app.volley.HttpPost;
import com.example.room202app.volley.HttpPut;

import java.util.ArrayList;

import lombok.Data;
import lombok.Getter;


@Getter
public class ArticleViewModel extends AndroidViewModel {
    private MutableLiveData<Article> article;
    private MutableLiveData<ArrayList<Comment>> comments;
    private MutableLiveData<Boolean> isSuccessGetArticleAndComments;
    private MutableLiveData<Boolean> isSuccessPutArticle;
    private MutableLiveData<Boolean> isSuccessDeleteArticle;
    private MutableLiveData<Boolean> isSuccessPostComment;
    private MutableLiveData<Boolean> isSuccessPutComment;
    private MutableLiveData<Boolean> isSuccessDeleteComment;
    private Context context = getApplication().getApplicationContext();

    public ArticleViewModel(@NonNull Application application) {
        super(application);
        article = new MutableLiveData<Article>();
        comments = new MutableLiveData<ArrayList<Comment>>();
        isSuccessGetArticleAndComments = new MutableLiveData<>();
        isSuccessPutArticle = new MutableLiveData<>();
        isSuccessDeleteArticle = new MutableLiveData<>();
        isSuccessPostComment = new MutableLiveData<>();
        isSuccessPutComment = new MutableLiveData<>();
        isSuccessDeleteComment = new MutableLiveData<>();
    }


    public void httpGetArticleAndComments(Long articleId){
        HttpGet.getArticleAndComments(context, articleId, article, comments, isSuccessGetArticleAndComments);
    }

    public void httpDeleteArticle(Long articleId){
        HttpDelete.deleteArticle(context, articleId, isSuccessDeleteArticle);
    }

    public void httpDeleteComment(Long articleId, Long commentId)
    {
        HttpDelete.deleteComment(context, articleId, commentId, isSuccessDeleteComment);
    }

    public void httpPutArticle(Article article) {
        HttpPut.putArticle(context, article, isSuccessPutArticle);
    }

    public void httpPostComment(Comment comment) {
        HttpPost.postComment(context, comment, isSuccessPostComment);
    }

    public void httpPutComment(Comment comment) {
        HttpPut.putComment(context, comment, isSuccessPutComment);
    }
}
