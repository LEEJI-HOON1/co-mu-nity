package com.comu.comunity.dto;

import lombok.Getter;

@Getter
public class FriendRequestDto {
    private Long fromMemberId;  // 토큰시에는 값이 빠져야함
    private Long toMemberId;

}
