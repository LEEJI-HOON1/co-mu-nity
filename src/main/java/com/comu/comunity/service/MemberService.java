package com.comu.comunity.service;

import com.comu.comunity.auth.JwtTokenProvider;
import com.comu.comunity.auth.TokenInfo;
import com.comu.comunity.dto.MemberRequestDto;
import com.comu.comunity.dto.MemberResponseDto;
import com.comu.comunity.model.entity.Member;
import com.comu.comunity.repository.MemberRepository;
import com.comu.comunity.util.PasswordEncryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncryptor passwordEncryptor; // 비밀번호 암호화

    public MemberResponseDto getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
        return new MemberResponseDto(member.getId(), member.getName(), member.getEmail(), member.getBirthDate());
    }

    @Transactional
    public void updateMember(Long id, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
        member.updateProfile(memberRequestDto.getName(), memberRequestDto.getEmail(), memberRequestDto.getBirthDate());
    }

    @Transactional
    public void deleteMember(Long id, String password) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
        memberRepository.delete(member);
    }


    //         회원가입
    public MemberResponseDto register(MemberRequestDto memberRequestDto) {
        // Member 객체 생성 및 필드 설정
        Member member = new Member();
        member.setName(memberRequestDto.getName());
        member.setEmail(memberRequestDto.getEmail());
        member.setPassword(passwordEncryptor.encryptPassword(memberRequestDto.getPassword())); // 비밀번호 암호화 제외
        Member savedMember = memberRepository.save(member);

        MemberResponseDto saveDto = new MemberResponseDto();
        saveDto.setEmail(savedMember.getEmail());
        saveDto.setName(savedMember.getName());
        saveDto.setBirthDate(savedMember.getBirthDate());
        return saveDto;
    }

    //로그인
    public TokenInfo getMemberToken(String email, String password) {
        Optional<Member> memberOptional = memberRepository.findByEmail(email);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();

            if (passwordEncryptor.validatePassword(password, member.getPassword())) { // 비밀번호 비교 (암호화 제외)
                return TokenInfo.builder()
                        .email(member.getEmail())
                        .token(jwtTokenProvider.createToken(member))
                        .build();

            } else {
                throw new RuntimeException("잘못된 비밀번호");
            }

        } else {
            throw new RuntimeException("회원이 존재하지 않습니다");
        }
    }

}


