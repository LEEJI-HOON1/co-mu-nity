package com.comu.comunity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardResponseDto {
    private long id;
    private String name;
//    private long memberId;
    private String contents;
//    private List<CommentResponseDto> comment;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
