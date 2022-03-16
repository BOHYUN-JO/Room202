package com.team202.room202back.comment.domain.dto;

import com.team202.room202back.article.domain.Article;
import com.team202.room202back.comment.domain.Comment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDto {

    private String content;

    private Long articleId;

    public CommentRequestDto(String content, Long articleId) {
        this.content = content;
        this.articleId = articleId;
    }

    public Comment toEntity(Article article){
        return Comment.builder()
                .content(content)
                .article(article)
                .build();
    }

}
