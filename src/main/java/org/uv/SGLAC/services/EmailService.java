package org.uv.SGLAC.services;

public interface EmailService {
    void sendVerificationCode(String to, String code);
    void sendEmailWithAttachment(String to, String subject, String message, String filePath);
}
