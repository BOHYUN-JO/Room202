package com.team202.room202back.board.service;

import com.team202.room202back.article.domain.Article;
import com.team202.room202back.article.domain.dto.ArticleResponseDto;
import com.team202.room202back.board.domain.Board;
import com.team202.room202back.board.domain.BoardRepository;
import com.team202.room202back.board.domain.dto.BoardResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public List<BoardResponseDto> viewAll(){
        List<BoardResponseDto> boardResponseDtoList = boardRepository.findAll()
                .stream()
                .map(b -> new BoardResponseDto(b.getId(), b.getName()))
                .collect(Collectors.toList());
        return boardResponseDtoList;
    }

    @Transactional
    public List<ArticleResponseDto> viewDetail(Long id, int page) throws NotFoundException {
        Board board = boardRepository.findById(id).orElseThrow(()-> new NotFoundException("NO"));
        return board.getArticles()
                .stream()
                .map(article -> new ArticleResponseDto(article.getId(), article.getTitle(), article.getContent(),
                        article.getViewCount(),article.getCreatedDate(), article.getModifiedDate() ) )
                .collect(Collectors.toList());
    }

}
