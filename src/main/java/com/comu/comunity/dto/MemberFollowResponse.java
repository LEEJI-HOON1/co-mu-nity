package com.comu.comunity.dto;


import com.comu.comunity.model.entity.Friend;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberFollowResponse {
    private Long id;
    private String name;

    public MemberFollowResponse(Friend friend) {
        this.id = friend.getToMember().getId();
        this.name = friend.getToMember().getName();
    }

    public MemberFollowResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
