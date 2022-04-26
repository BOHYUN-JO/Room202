package com.team202.room202back.comment.domain.dto;


import lombok.Getter;

import javax.persistence.Lob;
import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private Long id;

    private String content;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    public CommentResponseDto(Long id, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

}

