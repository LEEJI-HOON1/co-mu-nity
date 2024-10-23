package com.comu.comunity.dto;

import com.comu.comunity.model.entity.Comment;
import com.comu.comunity.model.entity.Member;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private Long id;
    private Long memberId;
    private String memberName;
    private String contents;


    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.memberId = comment.getMemberId();
        this.name = comment.getName();
        this.contents = comment.getContents();
    }
}