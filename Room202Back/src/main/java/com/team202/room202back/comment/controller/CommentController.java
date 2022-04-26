package com.team202.room202back.comment.controller;

import com.team202.room202back.comment.domain.Comment;
import com.team202.room202back.comment.domain.dto.CommentRequestDto;
import com.team202.room202back.comment.domain.dto.CommentResponseDto;
import com.team202.room202back.comment.service.CommentService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "모든 댓글 조회", notes = "게시글 id를 통한 모든 댓글 조회")
    @GetMapping("/comments")
    public List<CommentResponseDto> viewAll(@PathVariable Long article_id){
        return commentService.viewAll(article_id);
    }

    @ApiOperation(value = "댓글 저장", notes = "댓글 DB에 저장")
    @PostMapping("/comments")
    public CommentResponseDto save(@RequestBody CommentRequestDto commentRequestDto){
        return commentService.save(commentRequestDto);
    }

    @ApiOperation(value = "댓글 삭제", notes = "댓글 id를 통한 삭제")
    @DeleteMapping("/comments/{comment_id}")
    public void delete(@PathVariable Long commentId){
        commentService.deleteById(commentId);
    }

}
