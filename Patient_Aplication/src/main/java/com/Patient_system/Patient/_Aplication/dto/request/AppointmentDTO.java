package com.Patient_system.Patient._Aplication.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
@AllArgsConstructor
@Data
@NoArgsConstructor

public class AppointmentDTO implements Serializable {
    private long tid;
    private String appointmentID;
    @NotNull(message = "patient ID cannot be null")
    private String patientID;
    @NotNull(message = "Doctor ID cannot be blank")
    private String doctorID;
    @NotBlank(message = "Reason for appointment")
    private String appointmentReason;
    @Column(name = "appointment_date")
    private LocalDate appointmentDate;
    @Column(name = "appointment_time")
    private LocalTime appointmentTime;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    private Integer status;
}
