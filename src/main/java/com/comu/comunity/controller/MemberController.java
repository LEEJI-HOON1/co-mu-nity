package com.comu.comunity.controller;

import com.comu.comunity.dto.MemberResponseDto;
import com.comu.comunity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 사용자 정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMemberProfile(@PathVariable Long id) {
        MemberResponseDto memberResponseDto = memberService.getMemberProfile(id);
        return ResponseEntity.ok(memberResponseDto);
    }
}
