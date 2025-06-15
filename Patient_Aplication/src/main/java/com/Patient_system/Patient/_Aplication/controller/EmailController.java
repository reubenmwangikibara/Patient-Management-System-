package com.Patient_system.Patient._Aplication.controller;

import com.Patient_system.Patient._Aplication.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/send")
    public String sendTestEmail(@RequestParam String to) {
        emailService.sendAppointmentConfirmation(
                to,
                "John Doe",         // sample patient name
                "Jane Smith",       // sample doctor name
                "2025-06-15",       // sample date
                "10:00 AM"          // sample time
        );
        return "Appointment email sent to " + to;
    }
}
