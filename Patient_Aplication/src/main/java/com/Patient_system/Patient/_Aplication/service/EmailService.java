package com.Patient_system.Patient._Aplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendAppointmentConfirmation(String toEmail, String patientName, String doctorName,
                                            String appointmentDate, String appointmentTime) {
        String subject = "Appointment Confirmation";
        String body = String.format("Hello %s,\n\nYour appointment with Dr. %s is confirmed for %s at %s.\n\nThank you.",
                patientName, doctorName, appointmentDate, appointmentTime);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("reubenmwangikibara@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
