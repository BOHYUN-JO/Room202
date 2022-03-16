package com.team202.room202back.board.service;

import com.team202.room202back.article.domain.Article;
import com.team202.room202back.article.domain.dto.ArticleResponseDto;
import com.team202.room202back.board.domain.Board;
import com.team202.room202back.board.domain.BoardRepository;
import com.team202.room202back.board.domain.dto.BoardResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardResponseDto> viewAll(){
        List<BoardResponseDto> boardResponseDtoList = boardRepository.findAll()
                .stream()
                .map(b -> new BoardResponseDto(b.getId(), b.getName()))
                .collect(Collectors.toList());
        return boardResponseDtoList;
    }

    public List<Article> viewDetail(Long id, int page){
        Board board = boardRepository.getById(id);
        return board.getArticles();
    }

}
