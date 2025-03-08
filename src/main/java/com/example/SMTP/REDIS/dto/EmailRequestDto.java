package com.example.SMTP.REDIS.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class EmailRequestDto {
    @Email
    @NotEmpty(message = "이메일을 입력해주세요")
    private String email;
}
