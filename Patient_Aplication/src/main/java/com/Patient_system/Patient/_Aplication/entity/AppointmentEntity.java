package com.Patient_system.Patient._Aplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointment")

public class AppointmentEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    @Column(name = "tid", nullable = false)
    private long tid;
    @Column(name = "appointment_id")
    private String appointmentID;
    @Column(name = "patient_id")
    private String patientID;
    @Column(name = "doctor_id")
    private String doctorID;
    @Column(name = "appointment_reason")
    private String appointmentReason;
    @Column(name = "status")
    private Integer status;
    @Column(name = "appointment_date")
    private LocalDateTime appointmentDate;
    @Column(name = "appointment_time")
    private LocalTime appointmentTime;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;



}
