package com.team202.room202back.board.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResponseDto {

    private Long id;

    private String name;

    public BoardResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
