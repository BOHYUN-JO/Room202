package com.team202.room202back.board.controller;

import com.team202.room202back.article.domain.Article;
import com.team202.room202back.board.domain.dto.BoardResponseDto;
import com.team202.room202back.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/boards")
public class BoardController {

    private BoardService boardService;

    @GetMapping
    public List<BoardResponseDto> viewAll(){
        return boardService.viewAll();
    }

    @GetMapping("/{board_id}")
    public List<Article> viewDetail(@RequestParam("board_id") Long id, @PathVariable("page") int page){
        return boardService.viewDetail(id, page);
    }

}
