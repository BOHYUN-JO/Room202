package com.team202.room202back.article.controller;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.team202.room202back.article.domain.dto.ArticleRequestDto;
import com.team202.room202back.article.domain.dto.ArticleResponseDto;
import com.team202.room202back.article.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @ApiOperation(value = "게시글 조회", notes = "게시글 id를 통한 조회")
    @GetMapping("/{article_id}")
    @ResponseStatus(HttpStatus.OK)
    public ArticleResponseDto findById(@PathVariable("article_id") Long id) throws NotFoundException {
        return articleService.findById(id);
    }

    @ApiOperation(value = "게시글 저장", notes = "게시글 DB에 저장")
    @PostMapping()
    public ArticleResponseDto save(@RequestBody ArticleRequestDto articleRequestDto) {
        return articleService.save(articleRequestDto);
    }

    @ApiOperation(value = "게시글 수정", notes = "게시글 id를 통한 수정")
    @PatchMapping("/{article_id}")
    public ArticleResponseDto update(@PathVariable("article_id")Long id, @RequestBody ArticleRequestDto articleRequestDto) throws NotFoundException {
        return articleService.update(id, articleRequestDto);
    }

    @ApiOperation(value = "게시글 삭제", notes = "게시글 id를 통한 삭제")
    @DeleteMapping("/{article_id}")
    public void delete(@PathVariable("article_id") Long id){
        articleService.deleteById(id);
    }

}
