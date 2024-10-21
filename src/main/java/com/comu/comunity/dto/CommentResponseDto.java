package com.comu.comunity.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String name;
    private String contents;
    private LocalDateTime createDate;
    private  LocalDateTime updateDate;


}
