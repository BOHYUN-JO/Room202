package com.example.room202app.volley;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.room202app.dto.Article;
import com.example.room202app.dto.Comment;

import org.json.JSONObject;

public class HttpPost {
    public static void postArticle(Context context, Article article, MutableLiveData<Boolean> isSuccessPostArticle){

        String articlePath = VolleySingleton.default_path + "/articles";

        NoBodyRequest noBodyRequest = new NoBodyRequest(Request.Method.POST, articlePath,
                new JSONObject(article.getArticleMapPost()), new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                isSuccessPostArticle.setValue(true);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                isSuccessPostArticle.setValue(false);
            }
        });


        // Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance(context).addToRequestQueue(noBodyRequest);
    }

    public static void postComment(Context context, Comment comment, MutableLiveData<Boolean> isSuccessPostComment) {
        String commentPath = VolleySingleton.default_path + "/articles/"+comment.getArticleId()+"/comments";

        NoBodyRequest noBodyRequest = new NoBodyRequest(Request.Method.POST, commentPath,
                new JSONObject(comment.getCommentMapPost()), new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                isSuccessPostComment.setValue(true);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                isSuccessPostComment.setValue(false);
            }
        });


        // Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance(context).addToRequestQueue(noBodyRequest);
    }


}
