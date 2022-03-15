package com.team202.room202back.article.domain.dto;


import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ArticleResponseDto {

    private Long id;

    private String title;

    private String content;

    private int viewCount;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public ArticleResponseDto(Long id, String title, String content, int viewCount, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "ArticleResponseDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", viewCount=" + viewCount +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }

}
