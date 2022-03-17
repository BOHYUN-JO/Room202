package com.team202.room202back.comment.controller;

import com.team202.room202back.comment.domain.Comment;
import com.team202.room202back.comment.domain.dto.CommentRequestDto;
import com.team202.room202back.comment.domain.dto.CommentResponseDto;
import com.team202.room202back.comment.service.CommentService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/articles/{article_id}")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/comments")
    public List<CommentResponseDto> viewAll(@PathVariable Long article_id){
        return commentService.viewAll(article_id);
    }

    @PostMapping("/comments")
    public CommentResponseDto save(@RequestBody CommentRequestDto commentRequestDto){
        return commentService.save(commentRequestDto);
    }

    @DeleteMapping("/comments/{comment_id}")
    public void delete(@PathVariable Long commentId){
        commentService.deleteById(commentId);
    }

}
