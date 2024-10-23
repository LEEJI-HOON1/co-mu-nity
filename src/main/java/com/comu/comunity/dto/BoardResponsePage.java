package com.comu.comunity.dto;

import com.comu.comunity.model.entity.Board;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardResponsePage {
    private List<BoardListResponseDto> boards;
    private int totalPages;
    private long totalElements;

    public BoardResponsePage(Page<Board> page) {
        this.boards = page.getContent().stream()
                .map(Board::listTo)
                .collect(Collectors.toList());
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }
}
