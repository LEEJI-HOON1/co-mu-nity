package com.comu.comunity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FriendResponseDto {
    private Long id;
    private Long fromMemberId;
    private Long toMemberId;

    // memberResponseDtoFromMember
    // memberResponseDtoToMember

    public FriendResponseDto(Long id ,Long fromMemberId, Long toMemberId) {
        this.id = id;
        this.fromMemberId = fromMemberId;
        this.toMemberId = toMemberId;
    }
}
