package com.comu.comunity.service;

import com.comu.comunity.dto.MemberRequestDto;
import com.comu.comunity.dto.MemberResponseDto;
import com.comu.comunity.model.entity.Member;
import com.comu.comunity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService { // 비즈니스 로직 담당 -> 회원 정보 조회, 수정, 삭제 처리
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화

    // 회원 정보 조회
    public MemberResponseDto getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
        return new MemberResponseDto(member.getId(), member.getName(), member.getEmail(), member.getBirthDate());
    }

    @Transactional
    // 회원 정보 수정
    public void updateMember(Long id, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        // 회원 정보 업데이트
        member.updateProfile(memberRequestDto.getName(), memberRequestDto.getEmail(), memberRequestDto.getBirthDate());
    }

    @Transactional
    // 회원 탈퇴
    public void deleteMember(Long id, String password) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        // ... 비밀번호 관련 로직은 JWT 토큰 및 PasswordEncoder..?

        memberRepository.delete(member);
    }
}