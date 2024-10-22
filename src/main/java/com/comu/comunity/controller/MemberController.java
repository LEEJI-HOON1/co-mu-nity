package com.comu.comunity.controller;

import com.comu.comunity.dto.MemberRequestDto;
import com.comu.comunity.dto.MemberResponseDto;
import com.comu.comunity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // 사용자 정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMemberProfile(@PathVariable Long id) {
        MemberResponseDto memberResponseDto = memberService.getMember(id);
        return ResponseEntity.ok(memberResponseDto);
    }

    // 사용자 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateMemberProfile(@PathVariable Long id, @RequestBody MemberRequestDto memberRequestDto) {
        memberService.updateMember(id, memberRequestDto);
        return ResponseEntity.ok("수정을 완료했습니다.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id, @RequestParam String password) {
        memberService.deleteMember(id, password);
        return ResponseEntity.ok("탈퇴되었습니다.");
    }
}
