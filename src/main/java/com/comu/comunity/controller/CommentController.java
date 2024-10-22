package com.comu.comunity.controller;

import com.comu.comunity.dto.CommentRequestDto;
import com.comu.comunity.dto.CommentResponseDto;
import com.comu.comunity.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards/{boardId}")
public class CommentController {

    private  final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;}


    @PostMapping("/comments")
    public CommentResponseDto createComment (@RequestBody CommentRequestDto requestDto){
        //현재 로그인한 사용자의 정보를 가져옴
        return commentService.createComment(requestDto);
    }


    @GetMapping("/comments/{commentId}")
    public List<CommentResponseDto> getComment() { return commentService.getComment(); }


    @PutMapping("/comments/{commentId}")
    public Long updateComment(@PathVariable(value = "Id") Long Id, @RequestBody CommentRequestDto requestDto){
        return commentService.updateComment(Id, requestDto);
    }

    @DeleteMapping("/comments/commentId")
    public Long deleteComment(@PathVariable(value = "Id") Long Id){
        return commentService.deleteComment(Id);

    }
}
