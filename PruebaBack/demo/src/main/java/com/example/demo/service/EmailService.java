package com.example.demo.service;

import com.example.demo.dto.EmailRequest;
import com.example.demo.templates.EmailTemplates;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final Resend resend;

    public EmailService(Resend resend) {
        this.resend = resend;
    }

    public String sendResetPassword(String to, String resetLink) throws ResendException {
        CreateEmailOptions request = CreateEmailOptions.builder()
                .from("onboarding@resend.dev")
                .to(to)
                .subject("Reinicio de contraseña")
                .html(EmailTemplates.resetPassword(resetLink))
                .build();

        CreateEmailResponse response = resend.emails().send(request);
        return response.getId();
    }
}