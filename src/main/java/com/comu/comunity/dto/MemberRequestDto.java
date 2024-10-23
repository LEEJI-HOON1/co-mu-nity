package com.comu.comunity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberRequestDto {
    private String name;
    private String email;
    private LocalDate birthDate;
    private String password;

}
