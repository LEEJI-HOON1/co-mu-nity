package com.comu.comunity.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberRequestDto { // 사용자 입력 데이터를 받아옴
    private String name;
    private String email;
    private LocalDate birthDate;
//    비밀번호 필드 포함
    private String password;
}
