package com.team202.room202back.board.controller;

import com.team202.room202back.article.domain.dto.ArticleResponseDto;
import com.team202.room202back.board.domain.dto.BoardResponseDto;
import com.team202.room202back.board.service.BoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @ApiOperation(value = "모든 게시판 조회", notes = "모든 게시판 조회")
    @GetMapping
    public Map<String, List<BoardResponseDto>> viewAll(){
        List<BoardResponseDto> boardList = boardService.viewAll();
        Map<String, List<BoardResponseDto>> boards = new HashMap<>();
        boards.put("boards", boardList);
        return boards;
    }

    @ApiOperation(value = "게시판 상세 내용 조회", notes = "보드판 id를 통한 게시글 리스트 조회")
    @GetMapping("/{board_id}")
    public Map<String, List<ArticleResponseDto>> viewDetail(@PathVariable("board_id") Long id, @RequestParam("page") int page) throws NotFoundException {
        List<ArticleResponseDto> articleList = boardService.viewDetail(id, page);
        Map<String, List<ArticleResponseDto>> articles = new HashMap<>();
        articles.put("articles", articleList);
        return articles;
    }

}
