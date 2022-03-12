package com.team202.room202back.board.domain;


import com.team202.room202back.article.domain.Article;
import com.team202.room202back.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "board_id"))
public class Board extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "board")
    List<Article> articles = new ArrayList<Article>();

    @Builder
    public Board(Long id, LocalDateTime createdDate, LocalDateTime modifiedDate, String name, List<Article> articles) {
        super(id, createdDate, modifiedDate);
        this.name = name;
        this.articles = articles;
    }
}
