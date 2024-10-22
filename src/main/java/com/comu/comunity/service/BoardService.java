package com.comu.comunity.service;

import com.comu.comunity.dto.BoardRequestDto;
import com.comu.comunity.dto.BoardResponseDto;
import com.comu.comunity.dto.BoardResponsePage;
import com.comu.comunity.model.entity.Board;
import com.comu.comunity.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;



    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto) {
        //게시글 생성
        //Dto -> Entity
        Board board = Board.from(boardRequestDto);
        //DB에 저장
        Board savedBoard = boardRepository.save(board);

        return savedBoard.to();
    }

    //게시글 전체조회
    public List<BoardResponseDto> getBoardList() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(Board::to)
                .collect(Collectors.toList());
    }

    public BoardResponsePage getBoardListWithPaging(int page, int size, String criteria){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, criteria));
        Page<Board> boards = boardRepository.findAll(pageable);
        return new BoardResponsePage(boards);
    }
    //게시글 선택 조회
    public BoardResponseDto getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Board not fount with id"+id));
        return board.to();
    }

    //게시글 수정
    @Transactional
    public void updateBoard(Long id, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findBoardById(id);
        board.updateData(boardRequestDto);
    }

    //게시글 삭제
    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.findBoardById(id);
        boardRepository.deleteById(id);
    }
}
