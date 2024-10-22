package com.comu.comunity.controller;

import com.comu.comunity.auth.TokenInfo;
import com.comu.comunity.dto.MemberRequestDto;
import com.comu.comunity.dto.MemberResponseDto;
import com.comu.comunity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginController {

    private final MemberService memberService;

    //회원가입
    @PostMapping("/auth/register")
    public MemberResponseDto register(@RequestBody MemberRequestDto memberRequestDto) {
        return memberService.register(memberRequestDto);
    }
    //로그인
    @PostMapping("/auth/login")
    public TokenInfo login(@RequestBody MemberRequestDto memberRequestDto) {
        return memberService.getMemberToken(memberRequestDto.getEmail(),memberRequestDto.getPassword());
    }

}
