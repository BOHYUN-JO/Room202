package com.team202.room202back.article.service;


import com.team202.room202back.article.domain.ArticleRepository;
import com.team202.room202back.article.domain.dto.ArticleRequestDto;
import com.team202.room202back.board.domain.Board;
import com.team202.room202back.board.domain.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(ArticleRequestDto articleRequestDto){
        Board board = boardRepository.getById(articleRequestDto.getBoardId());

        return articleRepository.save(articleRequestDto.toEntity(board)).getId();
    }

    @Transactional
    public void deleteById(Long id){
        articleRepository.deleteById(id);
    }

}
