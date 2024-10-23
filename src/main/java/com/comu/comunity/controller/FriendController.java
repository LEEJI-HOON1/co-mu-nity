package com.comu.comunity.controller;

import com.comu.comunity.auth.JwtTokenProvider;
import com.comu.comunity.dto.FriendResponseDto;
import com.comu.comunity.dto.MemberResponseDto;
import com.comu.comunity.model.entity.Member;
import com.comu.comunity.service.FriendService;
import com.comu.comunity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FriendController {

    private final FriendService friendService;

    // Todo
    //  로그인기능 완료후, fromMemberId는 토큰으로 구분해주기
    // 팔로잉하기 (친구맺기)
    @PostMapping("/members/{fromMemberId}/following/{toMemberId}")
    public ResponseEntity<FriendResponseDto> follow(@PathVariable Long fromMemberId, @PathVariable Long toMemberId) {


        FriendResponseDto followResponse = friendService.follow(fromMemberId, toMemberId);
        return ResponseEntity.ok(followResponse);
    }

    // 팔로잉 목록 조회
    // fromMember의 toMember 리스트를 조회한다.
    @GetMapping("/followings/{fromMemberId}")
    public ResponseEntity<List<MemberResponseDto>> getFollowings(@PathVariable Long fromMemberId) {
        List<MemberResponseDto> followings = friendService.getFollowings(fromMemberId);
        return ResponseEntity.ok(followings);
    }

    // 팔로워 목록 조회
    // toMember의 fromMember 리스트를 찾는다
    @GetMapping("/followers/{toMemberId}")
    public ResponseEntity<List<MemberResponseDto>> getFollowers(@PathVariable Long toMemberId) {
        List<MemberResponseDto> followers = friendService.getFollowers(toMemberId);
        return ResponseEntity.ok(followers);
    }

    // 팔로우 끊기
    @DeleteMapping("/members/{fromMemberId}/follow/{toMemberId}")
    public ResponseEntity<FriendResponseDto> unfollow(@PathVariable Long fromMemberId, @PathVariable Long toMemberId) {
        FriendResponseDto unFollowResponse = friendService.unfollow(fromMemberId, toMemberId);
        return ResponseEntity.ok(unFollowResponse);
    }


}
