package com.example.room202app.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//댓글

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    Long commentId;
    Long articleId;
    Long userId;
    String userName;
    String comment;
    String commentDate;

    public Map<String, Object> getCommentMapPost() {
        Map<String, Object> comment = new HashMap<>();
        comment.put("article_id", this.articleId);
        comment.put("user_id", this.userId);
        comment.put("comment", this.comment);
        comment.put("comment_date", this.commentDate);
        return comment;
    }
    public Map<String, Object> getCommentMapPut() {
        Map<String, Object> comment = new HashMap<>();
        comment.put("comment_id", this.commentId);
        comment.put("article_id", this.articleId);
        comment.put("user_id", this.userId);
        comment.put("comment", this.comment);
        comment.put("comment_date", this.commentDate);
        return comment;
    }
}
