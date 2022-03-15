package com.team202.room202back.article.controller;


import com.team202.room202back.article.domain.dto.ArticleRequestDto;
import com.team202.room202back.article.domain.dto.ArticleResponseDto;
import com.team202.room202back.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private ArticleService articleService;


}
