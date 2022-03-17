package com.example.room202app.volley;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.room202app.dto.Article;
import com.example.room202app.dto.Comment;

import org.json.JSONObject;

public class HttpPut {

    public static void putArticle(Context context, Article article, MutableLiveData<Boolean> isSuccessPutArticle){

        String articlePath = VolleySingleton.default_path + "/articles";

        NoBodyRequest noBodyRequest = new NoBodyRequest(Request.Method.PUT, articlePath,
                new JSONObject(article.getArticleMapPut()), new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                isSuccessPutArticle.setValue(true);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                isSuccessPutArticle.setValue(false);
            }
        });


        // Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance(context).addToRequestQueue(noBodyRequest);
    }

    public static void putComment(Context context, Comment comment, MutableLiveData<Boolean> isSuccessPutComment) {
        String commentPath = VolleySingleton.default_path + "/articles/"+comment.getArticleId()+"/comments";

        NoBodyRequest noBodyRequest = new NoBodyRequest(Request.Method.PUT, commentPath,
                new JSONObject(comment.getCommentMapPut()), new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                isSuccessPutComment.setValue(true);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                isSuccessPutComment.setValue(false);
            }
        });


        // Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance(context).addToRequestQueue(noBodyRequest);
    }
}
