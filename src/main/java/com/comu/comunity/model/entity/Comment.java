package com.comu.comunity.model.entity;

import com.comu.comunity.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(name ="member_id")
    private Long memberId;

    @Column(name ="name")
    private String name;

    @Column(name = "contents")
    private String contents;


    public  Comment(CommentRequestDto requestDto) {
        this.name = requestDto.getName();
        this.contents = requestDto.getContents();
    }

    public void update(CommentRequestDto requestDto) {
        this.name = requestDto.getName();
        this.contents = requestDto.getContents();
    }
}
