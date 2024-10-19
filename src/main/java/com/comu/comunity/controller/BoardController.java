package com.comu.comunity.controller;

import com.comu.comunity.dto.BoardRequestDto;
import com.comu.comunity.dto.BoardResponseDto;
import com.comu.comunity.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    //게시글 생성
    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardRequestDto boardRequestDto) {
        BoardResponseDto dto = boardService.createBoard(boardRequestDto);
    }
    //게시글 상세조회

    //게시글 전체조회

    //게시글 수정

    //게시글 삭제

}
