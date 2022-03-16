package com.team202.room202back.comment.domain;


import com.team202.room202back.article.domain.Article;
import com.team202.room202back.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;


@Slf4j
@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseEntity {

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Builder
    public Comment(Long id, LocalDateTime createdDate, LocalDateTime modifiedDate, String content, Article article) {
        super(id, createdDate, modifiedDate);
        this.content = content;
        this.article = article;
    }
}
