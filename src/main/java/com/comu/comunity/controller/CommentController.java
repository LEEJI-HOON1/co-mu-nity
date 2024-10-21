package com.comu.comunity.controller;

import com.comu.comunity.dto.CommentRequestDto;
import com.comu.comunity.dto.CommentResponseDto;
import com.comu.comunity.service.CommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
