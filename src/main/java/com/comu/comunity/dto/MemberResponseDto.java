package com.comu.comunity.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberResponseDto {
    private Long id;
    private String name;
    private String email;
    private LocalDate birthDate;

    public MemberResponseDto(Long id, String name, String email, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }
}
