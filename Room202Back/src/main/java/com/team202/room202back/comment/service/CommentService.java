package com.team202.room202back.comment.service;


import com.team202.room202back.article.domain.Article;
import com.team202.room202back.article.domain.ArticleRepository;
import com.team202.room202back.comment.domain.Comment;
import com.team202.room202back.comment.domain.CommentRepository;
import com.team202.room202back.comment.domain.dto.CommentRequestDto;
import com.team202.room202back.comment.domain.dto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public List<CommentResponseDto> viewAll(Long id){
        Article article = articleRepository.getById(id);
        List<Comment> comments = article.getComments();
        return comments
                .stream()
                .map( comment -> new CommentResponseDto(comment.getId(), comment.getContent(), comment.getCreatedDate(),
                        comment.getModifiedDate() ))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponseDto save(CommentRequestDto commentRequestDto){
        Article article = articleRepository.getById(commentRequestDto.getArticleId());
        Comment comment = commentRepository.save(commentRequestDto.toEntity(article));
        CommentResponseDto commentResponseDto = new CommentResponseDto(comment.getId(), comment.getContent(),
                comment.getCreatedDate(), comment.getModifiedDate());
        return commentResponseDto;
    }

    @Transactional
    public void deleteById(Long id){
        commentRepository.deleteById(id);
    }

}
