package com.comu.comunity.controller;

import com.comu.comunity.dto.BoardRequestDto;
import com.comu.comunity.dto.BoardResponseDto;
import com.comu.comunity.service.BoardService;
import com.comu.comunity.dto.BoardResponsePage;
import com.comu.comunity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    //게시글 생성
    @PostMapping()
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardRequestDto boardRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(boardService.createBoard(boardRequestDto));
    }
    //게시글 전체조회
    @GetMapping()
    public ResponseEntity<BoardResponsePage> getBoardList(@RequestParam(name="page", required = false, defaultValue = "0") int page,
                                                               @RequestParam(name="size", required = false, defaultValue = "10") int size,
                                                               @RequestParam(name="criteria", required = false, defaultValue = "updateDate") String criteria) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(boardService.getBoardListWithPaging(page, size, criteria));
    }
    //게시글 상세조회
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable(name="id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(boardService.getBoard(id));
    }
    //게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBoard(@PathVariable(name="id") Long id, @RequestBody BoardRequestDto boardRequestDto) {
        boardService.updateBoard(id, boardRequestDto);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
    //게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<BoardResponseDto> deleteBoard(@PathVariable(name="id") Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
