package com.example.SMTP.REDIS.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AuthCodeRequestDto {
    @Email
    @NotEmpty
    private String email;

    private String code;

    public AuthCodeRequestDto(String email, String code) {
        this.email = email;
        this.code = code;
    }
}
