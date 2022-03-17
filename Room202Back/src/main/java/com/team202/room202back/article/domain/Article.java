package com.team202.room202back.article.domain;


import com.team202.room202back.article.domain.dto.ArticleRequestDto;
import com.team202.room202back.board.domain.Board;
import com.team202.room202back.comment.domain.Comment;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@AttributeOverride(name = "id", column = @Column(name = "article_id"))
public class Article extends BaseEntity{

    @Column(nullable = false)
    private String title;

    @Lob
    private String content;

    private int viewCount;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<Comment>();

    public void update(ArticleRequestDto articleRequestDto){
        this.title = articleRequestDto.getTitle();
        this.content = articleRequestDto.getContent();
        this.viewCount = articleRequestDto.getViewCount();
    }

    @Builder
    public Article(Long id, LocalDateTime createdDate, LocalDateTime modifiedDate, String title, String content, int viewCount, Board board) {
        super(id, createdDate, modifiedDate);
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.board = board;
    }

}
