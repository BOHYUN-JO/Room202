package com.example.room202app.volley;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.room202app.R;
import com.example.room202app.dto.Article;
import com.example.room202app.dto.Board;
import com.example.room202app.dto.Comment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class HttpDelete {

    public static void deleteArticle(Context context, Long articleId, MutableLiveData<Boolean> isSuccessDeleteArticle){

        String articlePath = VolleySingleton.default_path + "/articles/"+articleId;

        NoBodyRequest noBodyRequest = new NoBodyRequest(Request.Method.DELETE, articlePath, null, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                isSuccessDeleteArticle.setValue(true);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                isSuccessDeleteArticle.setValue(false);
            }
        });


        // Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance(context).addToRequestQueue(noBodyRequest);
    }

    public static void deleteComment(Context context, Long articleId, Long commentId, MutableLiveData<Boolean> isSuccessDeleteComment){

        String commentPath = VolleySingleton.default_path + "/articles/"+articleId+"/comments/"+commentId;

        NoBodyRequest noBodyRequest = new NoBodyRequest
                (Request.Method.DELETE, commentPath, null, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //성공
                        isSuccessDeleteComment.setValue(true);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //실패
                        isSuccessDeleteComment.setValue(false);
                    }
                });

        // Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance(context).addToRequestQueue(noBodyRequest);
    }
}
