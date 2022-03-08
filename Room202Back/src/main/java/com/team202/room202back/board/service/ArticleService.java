package com.team202.room202back.board.service;


import com.team202.room202back.board.domain.ArticleRepository;
import com.team202.room202back.board.domain.ArticleRequestDto;
import com.team202.room202back.board.domain.ArticleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public Long save(ArticleRequestDto articleRequestDto){
        return articleRepository.save(articleRequestDto.toEntity()).getId();
    }

//    @Transactional
//    public ArticleResponseDto findById(Long id){
//        return new ArticleResponseDto(articleRepository.findById(id).get());
//    }

    @Transactional
    public void deleteById(Long id){
        articleRepository.deleteById(id);
    }

}
