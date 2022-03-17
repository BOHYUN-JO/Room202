package com.team202.room202back.article.domain.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.team202.room202back.article.domain.Article;
import com.team202.room202back.board.domain.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ArticleRequestDto {

    private String title;

    private String content;

    private int viewCount;

    private Long boardId;

    public ArticleRequestDto(String title, String content, int viewCount, Long boardId) {
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.boardId = boardId;
    }

    public Article toEntity(Board board){
        return Article.builder()
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .board(board)
                .build();
    }

}
