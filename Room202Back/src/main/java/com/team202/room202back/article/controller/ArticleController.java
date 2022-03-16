package com.team202.room202back.article.controller;


import com.team202.room202back.article.domain.dto.ArticleRequestDto;
import com.team202.room202back.article.domain.dto.ArticleResponseDto;
import com.team202.room202back.article.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/articles")
public class ArticleController {

    private ArticleService articleService;


    @GetMapping("/{article_id}")
    @ResponseStatus(HttpStatus.OK)
    public ArticleResponseDto findById(@PathVariable("article_id") Long id) throws NotFoundException {
        return articleService.findById(id);
    }

    @PostMapping()
    public ArticleResponseDto save(@RequestBody ArticleRequestDto articleRequestDto){
        return articleService.save(articleRequestDto);
    }

    @PatchMapping("/{article_id}")
    public ArticleResponseDto update(@PathVariable("article_id")Long id, @RequestBody ArticleRequestDto articleRequestDto) throws NotFoundException {
        return articleService.update(id, articleRequestDto);
    }

    @DeleteMapping("/{article_id}")
    public void delete(@PathVariable("article_id") Long id){
        articleService.deleteById(id);
    }

}
