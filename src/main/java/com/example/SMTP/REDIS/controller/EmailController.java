package com.example.SMTP.REDIS.controller;

import com.example.SMTP.REDIS.dto.AuthCodeRequestDto;
import com.example.SMTP.REDIS.dto.EmailRequestDto;
import com.example.SMTP.REDIS.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailService mailService;

    @PostMapping("/send-email")
    public ResponseEntity sendemail(@RequestBody @Valid EmailRequestDto emailRequestDto, BindingResult bindingResult) {
        ResponseEntity<Map<String, String>> errorResponse = getMapResponseEntity(bindingResult);
        if (errorResponse != null) return errorResponse;
        return mailService.sendMail(emailRequestDto.getEmail());
    }

    @PostMapping("/auth-code")
    public ResponseEntity authCode(@RequestBody @Valid AuthCodeRequestDto authCodeDto, BindingResult bindingResult) {
        ResponseEntity<Map<String, String>> errorResponse = getMapResponseEntity(bindingResult);
        if (errorResponse != null) return errorResponse;
        return mailService.validateAuthCode(authCodeDto.getEmail(), authCodeDto.getCode());
    }

    private static ResponseEntity<Map<String, String>> getMapResponseEntity(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", bindingResult.getFieldError().getDefaultMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
        return null;
    }
}
