package com.example.room202app.volley;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.example.room202app.dto.Article;
import com.example.room202app.dto.Board;
import com.example.room202app.dto.Comment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HttpGet {
    public static void getBoards(Context context, MutableLiveData<ArrayList<Board>> boards, MutableLiveData<Boolean> isSuccessGetBoards) {

        String path = VolleySingleton.default_path + "/boards";

        ArrayList<Board> boardList = new ArrayList<Board>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //json형태를 boardList로 만듦
                            JSONArray boardsArray = response.getJSONArray("boards");
                            for (int i = 0; i < boardsArray.length(); i++) {
                                Board board = new Board();

                                board.setBoardId(Long.valueOf((int) boardsArray.getJSONObject(i).get("board_id")));
                                board.setBoardName(boardsArray.getJSONObject(i).get("board_name").toString());
                                boardList.add(board);
                            }

                            //성공
                            boards.setValue(boardList);
                            isSuccessGetBoards.setValue(true);

                        } catch (JSONException e) {
                            //실패
                            boards.setValue(null);
                            isSuccessGetBoards.setValue(false);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //실패
                        boards.setValue(null);
                        isSuccessGetBoards.setValue(false);
                    }
                });


        // Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);


    }

    public static void getArticles(Context context, Long boardId, Long page, MutableLiveData<ArrayList<Article>> articles,
                                   MutableLiveData<Long> totalPages, MutableLiveData<Boolean> isSuccessGetArticles) {

        String path = VolleySingleton.default_path + "/boards/" + boardId + "?page=" + page;

        ArrayList<Article> articleList = new ArrayList<Article>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //json형태를 articleList 만듦
                            JSONArray articlesArray = response.getJSONArray("articles");
                            for (int i = 0; i < articlesArray.length(); i++) {
                                Article article = new Article();
                                article.setArticleId(Long.valueOf((int) articlesArray.getJSONObject(i).get("article_id")));
                                article.setArticleTitle(articlesArray.getJSONObject(i).get("article_title").toString());
                                article.setUserName(articlesArray.getJSONObject(i).get("user_name").toString());
                                article.setArticleUpdateDate(articlesArray.getJSONObject(i).get("article_update_date").toString());
                                article.setCommentCount(Long.valueOf((int) articlesArray.getJSONObject(i).get("comment_count")));
                                articleList.add(article);
                            }
                            //live Data에 데이터가 변경됬음을 알려줌
                            totalPages.setValue(Long.valueOf((int) response.get("total_pages")));
                            articles.setValue(articleList);
                            isSuccessGetArticles.setValue(true);
                        } catch (JSONException e) {
                            totalPages.setValue(null);
                            articles.setValue(null);
                            isSuccessGetArticles.setValue(false);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        totalPages.setValue(null);
                        articles.setValue(null);
                        isSuccessGetArticles.setValue(false);
                    }
                });


        // Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);


    }


    public static void getArticleAndComments(Context context, Long articleId,
                                             MutableLiveData<Article> article, MutableLiveData<ArrayList<Comment>> comments,
                                             MutableLiveData<Boolean> isSuccessGetArticleAndComments) {

        String articlePath = VolleySingleton.default_path + "/articles/" + articleId;
        Article ac = new Article();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, articlePath, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ac.setArticleId(Long.valueOf((int) response.get("article_id")));
                            ac.setArticleTitle(response.get("article_title").toString());
                            ac.setUserId(Long.valueOf((int) response.get("user_id")));
                            ac.setUserName(response.get("user_name").toString());
                            ac.setArticleContent(response.get("article_content").toString());
                            ac.setArticleUpdateDate(response.get("article_update_date").toString());
                            ac.setArticleViewCount(Long.valueOf((int) response.get("article_view_count")));

                            //
                            article.setValue(ac);
                            getComments(context, articleId, comments, isSuccessGetArticleAndComments);

                        } catch (JSONException jsonException) {
                            //실패
                            article.setValue(null);
                            isSuccessGetArticleAndComments.setValue(false);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //실패
                        article.setValue(null);
                        isSuccessGetArticleAndComments.setValue(false);
                    }
                });


        // Access the RequestQueue through your singleton class.
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);


    }

    public static void getComments(Context context, Long articleId, MutableLiveData<ArrayList<Comment>> comments, MutableLiveData<Boolean> isSuccessGetArticleAndComments) {
        String commentsPath = VolleySingleton.default_path + "/articles/" + articleId + "/comments";
        ArrayList<Comment> commentList = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, commentsPath, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //json형태를 commentList로 만듦
                            JSONArray commentArray = response.getJSONArray("comments");
                            for (int i = 0; i < commentArray.length(); i++) {

                                Comment comment = new Comment();
                                comment.setArticleId(Long.valueOf((int) commentArray.getJSONObject(i).get("article_id")));
                                comment.setCommentId(Long.valueOf((int) commentArray.getJSONObject(i).get("comment_id")));
                                comment.setUserName(commentArray.getJSONObject(i).get("user_name").toString());
                                comment.setUserId(Long.valueOf((int) commentArray.getJSONObject(i).get("user_id")));
                                comment.setComment(commentArray.getJSONObject(i).get("comment").toString());
                                comment.setCommentDate(commentArray.getJSONObject(i).get("comment_date").toString());
                                commentList.add(comment);
                            }
                            //성공
                            comments.setValue(commentList);
                            isSuccessGetArticleAndComments.setValue(true);
                        } catch (JSONException e) {
                            //실패
                            comments.setValue(null);
                            isSuccessGetArticleAndComments.setValue(false);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //실패
                        comments.setValue(null);
                        isSuccessGetArticleAndComments.setValue(false);
                    }
                });
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

}
