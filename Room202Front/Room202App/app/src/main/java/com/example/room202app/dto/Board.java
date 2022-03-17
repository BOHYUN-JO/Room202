package com.example.room202app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//게시판 종류
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
    private Long boardId;
    private String boardName;

}
