package com.team202.room202back.board.domain;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ArticleEntity {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
