package org.uv.SGLAC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uv.SGLAC.services.EmailService;

@RestController
@RequestMapping("/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-code")
    public void sendVerificationCode(@RequestParam String to, @RequestParam String code) {
        emailService.sendVerificationCode(to, code);
    }

    @PostMapping("/send-attachment")
    public void sendEmailWithAttachment(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String message,
            @RequestParam String filePath) {
        emailService.sendEmailWithAttachment(to, subject, message, filePath);
    }
}
