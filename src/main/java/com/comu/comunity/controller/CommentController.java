package com.comu.comunity.controller;

import com.comu.comunity.dto.CommentRequestDto;
import com.comu.comunity.dto.CommentResponseDto;
import com.comu.comunity.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class CommentController {

    private  final CommentService commentService;



    @PostMapping("/comments")
    public ResponseEntity<CommentResponseDto> createComment (@RequestBody CommentRequestDto requestDto){
        CommentResponseDto responseDto = commentService.createComment(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }


    @GetMapping("/boards/{boardId}/comments")
    public ResponseEntity<List<CommentResponseDto>> getComment(@PathVariable Long boardId ) {
        List<CommentResponseDto> responseDtoList = commentService.getComment(boardId);


        return ResponseEntity.ok().body(responseDtoList);

    }


    @PutMapping("/boards/{boardId}/comments/{commentId}")
    public ResponseEntity<Long> updateComment(@PathVariable(value = "commentId") Long commentId, @RequestBody CommentRequestDto requestDto){
        Long updatedCommentId = commentService.updateComment(commentId, requestDto);
        return ResponseEntity.ok(updatedCommentId);
    }

    @DeleteMapping("/boards/{boardId}/comments/{commentId}")
    public ResponseEntity<Long> deleteComment(@PathVariable(value = "commentId") Long commentId) {
        Long deletedCommentId = commentService.deleteComment(commentId);

        return ResponseEntity.ok(deletedCommentId);

    }
}
