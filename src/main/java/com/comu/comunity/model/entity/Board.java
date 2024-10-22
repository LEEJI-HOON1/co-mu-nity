package com.comu.comunity.model.entity;

import com.comu.comunity.dto.BoardRequestDto;
import com.comu.comunity.dto.BoardResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "board")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "board" , cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "name")
    private String name;

    @Column(name = "contents")
    private String contents;

    public static Board from(BoardRequestDto boardRequestDto) {
        Board board = new Board();
        board.initData(boardRequestDto);
        return board;
    }

    private void initData(BoardRequestDto boardRequestDto) {
        this.name = boardRequestDto.getName();
        this.contents = boardRequestDto.getContents();
    }

    public BoardResponseDto to() {
        return new BoardResponseDto(
                id,
                memberId,
                name,
                contents,
//                comments.stream().map(Comment::to).toList(),
                getCreateDate(),
                getUpdateDate()
        );
    }

    public void updateData(BoardRequestDto boardRequestDto) {
        this.name = boardRequestDto.getName();
        this.contents = boardRequestDto.getContents();
    }
}
