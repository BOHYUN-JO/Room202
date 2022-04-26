package com.team202.room202back.article;


import com.team202.room202back.article.domain.Article;
import com.team202.room202back.article.domain.ArticleRepository;
import com.team202.room202back.article.domain.dto.ArticleResponseDto;
import com.team202.room202back.board.domain.Board;
import com.team202.room202back.article.domain.dto.ArticleRequestDto;
import com.team202.room202back.article.service.ArticleService;
import com.team202.room202back.board.domain.BoardRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private BoardRepository boardRepository;

    @AfterEach
    public void cleanUp(){
        articleRepository.deleteAll();
    }

    @DisplayName("게시글 조회 테스트")
    @Test
    public void findByIdTest() throws NotFoundException {
        // given
        ArticleRequestDto articleRequestDto = new ArticleRequestDto("test", "success!!", 1, 1L);
        ArticleResponseDto articleResponseDto =  articleService.save(articleRequestDto);

        // when
        ArticleResponseDto result = articleService.findById(articleResponseDto.getId());

        // then
        assertThat(result.getId()).isEqualTo(articleResponseDto.getId());
    }

    @DisplayName("게시글 등록 테스트")
    @Test
    public void saveTest() throws NotFoundException {
        // given
        ArticleRequestDto articleRequestDto = new ArticleRequestDto("test", "success!!", 1, 1L);
        ArticleResponseDto articleResponseDto =  articleService.save(articleRequestDto);

        // when
        Article article = articleRepository.findById(articleResponseDto.getId())
                .orElseThrow(() -> new NotFoundException("등록 실패") );

        // then
        assertThat(articleResponseDto.getId()).isEqualTo(article.getId());
    }

    @DisplayName("게시글 변경 테스트")
    @Test
    public void updateTest() throws NotFoundException {
        // given
        ArticleRequestDto articleRequestDto = new ArticleRequestDto("test", "success!!", 1, 1L);
        ArticleResponseDto articleResponseDto =  articleService.save(articleRequestDto);

        // when
        Article article = articleRepository.findById(articleResponseDto.getId())
                .orElseThrow(() -> new NotFoundException("등록 실패") );
        ArticleRequestDto changedRequestDto = new ArticleRequestDto("test", "change!!", 1, 1L);

        ArticleResponseDto update = articleService.update(article.getId(), changedRequestDto);

        // then
        assertThat(update.getContent()).isEqualTo(changedRequestDto.getContent());
    }

    @DisplayName("게시글 삭제 테스트")
    @Test
    public void deleteTest(){
        // given
        ArticleRequestDto articleRequestDto = new ArticleRequestDto("test", "success!!", 1, 1L);
        ArticleResponseDto articleResponseDto =  articleService.save(articleRequestDto);

        // when
        articleService.deleteById(articleResponseDto.getId());

        // then
        assertThatThrownBy( () -> articleService.findById(articleResponseDto.getId()))
                .isInstanceOf(NotFoundException.class);
    }

}
