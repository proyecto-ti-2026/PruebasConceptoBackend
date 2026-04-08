package com.example.demo.controller;

import com.example.demo.dto.EmailRequest;
import com.example.demo.service.EmailService;
import com.resend.core.exception.ResendException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody EmailRequest request) {
        try {
            String resetLink = "https://prueba.com/reset-password?token=test123";
            String id = emailService.sendResetPassword(request.getTo(), resetLink);
            return ResponseEntity.ok("Email enviado con ID: " + id);
        } catch (ResendException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}