package com.team202.room202back.article.service;


import com.team202.room202back.article.domain.Article;
import com.team202.room202back.article.domain.ArticleRepository;
import com.team202.room202back.article.domain.dto.ArticleRequestDto;
import com.team202.room202back.article.domain.dto.ArticleResponseDto;
import com.team202.room202back.board.domain.Board;
import com.team202.room202back.board.domain.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public ArticleResponseDto findById(Long id) throws NotFoundException {
        Article article = articleRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
        ArticleResponseDto articleResponseDto = new ArticleResponseDto( article.getId(), article.getTitle(), article.getContent(),
                article.getViewCount(), article.getCreatedDate(), article.getModifiedDate()
        );
        return articleResponseDto;
    }

    @Transactional
    public ArticleResponseDto save(ArticleRequestDto articleRequestDto){
        Board board = boardRepository.getById(articleRequestDto.getBoardId());
        Article article = articleRepository.save(articleRequestDto.toEntity(board));
        ArticleResponseDto articleResponseDto = new ArticleResponseDto( article.getId(), article.getTitle(), article.getContent(),
                article.getViewCount(), article.getCreatedDate(), article.getModifiedDate()
        );
        return articleResponseDto;
    }

    @Transactional
    public ArticleResponseDto update(Long id, ArticleRequestDto articleRequestDto) throws NotFoundException {
        Article article = articleRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found"));
        article.update(articleRequestDto);
        ArticleResponseDto articleResponseDto = new ArticleResponseDto( article.getId(), article.getTitle(), article.getContent(),
                article.getViewCount(), article.getCreatedDate(), article.getModifiedDate()
        );
        return articleResponseDto;
    }

    @Transactional
    public void deleteById(Long id){
        articleRepository.deleteById(id);
    }

}
