package com.example.SMTP.REDIS.controller;

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
        if (bindingResult.hasErrors()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", bindingResult.getFieldError().getDefaultMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
        ResponseEntity response = mailService.sendMail(emailRequestDto.getEmail());
        return response;
    }
}
