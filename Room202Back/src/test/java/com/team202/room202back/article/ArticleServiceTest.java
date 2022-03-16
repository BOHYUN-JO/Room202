package com.team202.room202back.article;


import com.team202.room202back.board.domain.Board;
import com.team202.room202back.article.domain.dto.ArticleRequestDto;
import com.team202.room202back.article.service.ArticleService;
import com.team202.room202back.board.domain.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void save(){

        Board board = Board.builder().name("board1").build();

        Board savedBoard = boardRepository.save(board);

        ArticleRequestDto articleRequestDto = new ArticleRequestDto("test", "please", 1, savedBoard.getId());

        articleService.save(articleRequestDto);



    }

}
