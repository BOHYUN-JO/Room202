package com.example.room202app.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//글
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {
    private Long boardId;
    private Long articleId;
    private String articleTitle;
    private String articleUpdateDate;
    private String articleContent;
    private Long commentCount;
    private Long articleViewCount;
    private Long userId;
    private String userName;

    public Map<String, Object> getArticleMapPost()
    {
        Map<String, Object> article = new HashMap<>();
        article.put("board_id", boardId);
        article.put("article_title", articleTitle);
        article.put("user_id", userId);
        article.put("article_content", articleContent);
        article.put("article_update_date", articleUpdateDate);
        return article;
    }

    public Map<String, Object> getArticleMapPut()
    {
        Map<String, Object> article = new HashMap<>();
        article.put("board_id", boardId);
        article.put("article_id", articleId);
        article.put("article_title", articleTitle);
        article.put("user_id", userId);
        article.put("article_content", articleContent);
        article.put("article_update_date", articleUpdateDate);
        return article;
    }
}
