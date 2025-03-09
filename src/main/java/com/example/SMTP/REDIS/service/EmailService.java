package com.example.SMTP.REDIS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    private String emailCode;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public ResponseEntity sendMail(String email) {
        try{
            emailCode = createEmailCode();
            MimeMessage message = createEmail(email);
            javaMailSender.send(message);

            return ResponseEntity.ok().build();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private MimeMessage createEmail(String email) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.setRecipients(Message.RecipientType.TO, email);
        message.setSubject("인증코드 안내");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<div>");
        stringBuilder.append("인증코k드를 확인해주세요.<br><strong style=\"font-size: 30px;\">");
        stringBuilder.append(emailCode);
        stringBuilder.append("</strong><br>인증코드는 3분간 유지됩니다.</div>");

        message.setText(stringBuilder.toString(), "utf-8", "html");
        message.setFrom(new InternetAddress("chltjswo789@gmail.com", "Praitce"));

        return message;
    }

    private static String createEmailCode() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int n = random.nextInt(10);
            stringBuilder.append(n);
        }

        return stringBuilder.toString();
    }


}
