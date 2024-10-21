package com.comu.comunity.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String name;
    private String content;
    private LocalDateTime createDate;
    private  LocalDateTime updateDate;


}
