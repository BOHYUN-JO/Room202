package com.team202.room202back.board.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleRequestDto {

    private String title;

    private String content;

    private int viewCount;

    public ArticleRequestDto(String title, String content, int viewCount) {
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
    }

    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .viewCount(viewCount)
                .build();
    }

}
