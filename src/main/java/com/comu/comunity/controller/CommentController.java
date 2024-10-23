package com.comu.comunity.controller;

import com.comu.comunity.auth.JwtTokenProvider;
import com.comu.comunity.dto.CommentRequestDto;
import com.comu.comunity.dto.CommentResponseDto;
import com.comu.comunity.model.entity.Member;
import com.comu.comunity.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/boards/{boardId}/comments")
@RequiredArgsConstructor

public class CommentController {

    private  final CommentService commentService;
    private  final JwtTokenProvider jwtTokenProvider;

    @PostMapping()
    public ResponseEntity<CommentResponseDto> createComment (@PathVariable Long boardId, @RequestBody CommentRequestDto requestDto){

        Member loginedMember = jwtTokenProvider.getLoginedMember();

        CommentResponseDto responseDto = commentService.createComment(boardId,requestDto, loginedMember);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

    @GetMapping()
    public ResponseEntity<List<CommentResponseDto>> getComment(@PathVariable Long boardId ) {
        List<CommentResponseDto> responseDtoList = commentService.getComment(boardId);


        return ResponseEntity.ok().body(responseDtoList);

    }


    @PutMapping("/{commentId}")
    public ResponseEntity<Long> updateComment(@PathVariable(name="commentId") Long commentId, @RequestBody CommentRequestDto requestDto){

        Member loginedMember = jwtTokenProvider.getLoginedMember();

        Long updatedCommentId = commentService.updateComment(commentId, requestDto, loginedMember);

        return ResponseEntity.ok(updatedCommentId);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Long> deleteComment(@PathVariable(name="commentId" ) Long commentId ) {

        Member loginedMember = jwtTokenProvider.getLoginedMember();

        Long deletedCommentId = commentService.deleteComment(commentId, loginedMember);

        return ResponseEntity.ok(deletedCommentId);

    }
}
