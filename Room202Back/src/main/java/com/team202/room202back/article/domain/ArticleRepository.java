package com.team202.room202back.article.domain;

import com.team202.room202back.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    //List<Article> findArticleById(Long id);
}
