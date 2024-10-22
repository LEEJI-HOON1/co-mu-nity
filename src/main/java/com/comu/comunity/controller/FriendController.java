package com.comu.comunity.controller;

import com.comu.comunity.dto.FriendResponseDto;
import com.comu.comunity.model.entity.Friend;
import com.comu.comunity.service.FriendService;
import com.comu.comunity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;
    private final MemberService memberService;

    // Todo
    //  로그인기능 완료후, fromMemberId는 토큰으로 구분해주기
    // 팔로잉하기 (친구맺기)
    @PostMapping("/members/{fromMemberId}/follow/{toMemberId}")
    public ResponseEntity<FriendResponseDto> follow(@PathVariable Long fromMemberId, @PathVariable Long toMemberId) {
        Friend followFriend = friendService.follow(fromMemberId, toMemberId);

        FriendResponseDto friendResponseDto = new FriendResponseDto(followFriend.getId(), fromMemberId, toMemberId);
        return ResponseEntity.ok(friendResponseDto);
    }

}
