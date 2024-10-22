package com.comu.comunity.dto;

import lombok.Getter;

@Getter
public class FriendResponseDto {
    private String message;
    private Long fromMemberId;
    private Long toMemberId;

    public FriendResponseDto(String message, Long fromMemberId, Long toMemberId) {
        this.message = message;
        this.fromMemberId = fromMemberId;
        this.toMemberId = toMemberId;
    }
}
