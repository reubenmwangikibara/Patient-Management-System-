package com.Patient_system.Patient._Aplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    /**
     * Sends a welcome email to a newly registered user.
     *
     * @param toEmail   The recipient's email address (from saved user)
     * @param userName  The recipient's name (from saved user)
     */

    public void sendWelcomeEmail(String toEmail, String userName, String firstName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Welcome to Our System!");
        message.setText("Hello " + userName + ",\n\n"
                + "Your account has been successfully created.\n\n"
                +"your username is " + toEmail+ ".\n\n"
                + "Thank you for registering with us!\n\n"
                + "Best regards,\n"
                + "The Team");
        message.setFrom("reubenmwangikibara@gmail.com");



        mailSender.send(message);
    }
}
