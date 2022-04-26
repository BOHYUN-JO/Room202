package com.team202.room202back.comment;

import com.team202.room202back.article.service.ArticleService;
import com.team202.room202back.comment.domain.Comment;
import com.team202.room202back.comment.domain.CommentRepository;
import com.team202.room202back.comment.domain.dto.CommentRequestDto;
import com.team202.room202back.comment.domain.dto.CommentResponseDto;
import com.team202.room202back.comment.service.CommentService;
import org.apache.ibatis.javassist.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @AfterEach
    public void cleanUp(){
        commentRepository.deleteAll();
    }

    @Test
    public void viewAllTest(){
        commentService.save(new CommentRequestDto("test1", 74L));
        commentService.save(new CommentRequestDto("test2", 74L));
        commentService.save(new CommentRequestDto("test3", 74L));

        List<CommentResponseDto> commentResponseDtos = commentService.viewAll(74L);
        for(CommentResponseDto commentResponseDto : commentResponseDtos){
            System.out.println(commentResponseDto.getContent());
        }

    }

    @Test
    public void saveTest() throws NotFoundException {
        //given
        CommentResponseDto commentResponseDto = commentService.save(new CommentRequestDto("good", 74L));

        //when
        Comment comment = commentRepository.findById(commentResponseDto.getId()).orElseThrow(()->new NotFoundException("No"));

        //then
        assertThat(commentResponseDto.getId()).isEqualTo(comment.getId());
    }

    @Test
    public void deleteTest() {
        // given
        CommentResponseDto commentResponseDto = commentService.save(new CommentRequestDto("bad", 74L));

        // when
        commentService.deleteById(commentResponseDto.getId());

        // then
        assertThat(commentRepository.findById(commentResponseDto.getId())).isEqualTo(Optional.empty());

    }
}
