package com.team202.room202back;


import com.team202.room202back.board.domain.ArticleRequestDto;
import com.team202.room202back.board.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    public void save(){
        ArticleRequestDto articleRequestDto = new ArticleRequestDto("test", "please", 1);

        Long result = articleService.save(articleRequestDto);

        if(result > 0){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
    }
}
