package com.team202.room202back.board;

import com.team202.room202back.article.domain.Article;
import com.team202.room202back.article.domain.dto.ArticleRequestDto;
import com.team202.room202back.article.domain.dto.ArticleResponseDto;
import com.team202.room202back.article.service.ArticleService;
import com.team202.room202back.board.domain.dto.BoardResponseDto;
import com.team202.room202back.board.service.BoardService;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private ArticleService articleService;


    @Test
    public void viewAllTest(){
        List<BoardResponseDto> boardResponseDtos = boardService.viewAll();
        for(BoardResponseDto boardResponseDto : boardResponseDtos){
            System.out.println(boardResponseDto.getName());
        }
    }

    @Test
    public void viewDetailTest() throws NotFoundException {
        articleService.save(new ArticleRequestDto("test1", "success!!", 1, 1L));
        articleService.save(new ArticleRequestDto("test2", "success!!", 1, 1L));
        articleService.save(new ArticleRequestDto("test3", "success!!", 1, 1L));

        List<ArticleResponseDto> articles = boardService.viewDetail(1L, 0);
        for(ArticleResponseDto article : articles){
            System.out.println(article.getTitle());
        }
    }
}
