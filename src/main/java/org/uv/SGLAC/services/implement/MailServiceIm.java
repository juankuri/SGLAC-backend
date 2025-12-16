package org.uv.SGLAC.services.implement;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import org.springframework.stereotype.Service;
import org.uv.SGLAC.services.MailService;

@Service
public class MailServiceIm implements MailService {

    private final String from = "juan111pa@gmail.com";
    private final String key = "nabdqczioyrwgmii";

    private final String testTo = "juankupa11@gmail.com";
    private final boolean testing = false;

    @Override
    public void sendOTP(String otp, String to) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, key);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));

            String destinoFinal = testing ? testTo : to;

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinoFinal)
            );

            message.setSubject("Tu código de verificación");

            String texto = "Hola,\n\n"
                    + "Has solicitado iniciar sesión en nuestro sistema. Tu código OTP es:\n\n"
                    + otp + "\n\n"
                    + "Este código es válido por 5 minutos y solo se puede usar una vez.\n\n"
                    + "Si no solicitaste este código, por favor ignora este correo.\n\n"
                    + "Saludos,\nEquipo de soporte.";

            message.setText(texto);

            Transport.send(message);
            System.out.println("Correo enviado exitosamente a " + destinoFinal);

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al enviar el correo: " + e.getMessage());
        }
    }
}
