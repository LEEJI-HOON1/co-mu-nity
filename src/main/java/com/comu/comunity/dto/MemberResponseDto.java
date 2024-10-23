package com.comu.comunity.dto;

import com.comu.comunity.model.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private int follower;
    private int following;

    public MemberResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.birthDate = member.getBirthDate();
        this.follower = member.getFollowers().size();
        this.following = member.getFollowings().size();
    }
}
