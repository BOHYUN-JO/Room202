package com.team202.room202back.board.domain;


import com.team202.room202back.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AttributeOverride(name = "id", column = @Column(name = "article_id"))
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "article_title")
    private String title;

    @Column(name = "article_content")
    private String content;

    @Column(name = "article_view_count")
    private int viewCount;

    @Builder
    public Article(Long id, LocalDateTime createdDate, LocalDateTime modifiedDate, String title, String content, int viewCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
    }
}
