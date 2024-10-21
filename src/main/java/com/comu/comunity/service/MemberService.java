package com.comu.comunity.service;

import com.comu.comunity.dto.MemberRequestDto;
import com.comu.comunity.dto.MemberResponseDto;
import com.comu.comunity.model.entity.Member;
import com.comu.comunity.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder // 비밀번호 암호화

    public MemberResponseDto getMemberProfile(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
        return new MemberResponseDto(member.getId(), member.getName(), member.getEmail(), member.getBirthDate());
    }
    @Transactional
    public void updateMemberProfile(Long id, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        // 비밀번호 관련 로직은 JWT 토큰 및 PasswordEncoder에
    }

    }