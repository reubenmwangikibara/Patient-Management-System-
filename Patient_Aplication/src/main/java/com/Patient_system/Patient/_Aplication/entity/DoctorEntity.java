package com.Patient_system.Patient._Aplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="doctors")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    @Column(name = "tid", nullable = false)
    private long tid;
    @Column(name = "doctor_id")
    private String doctorID;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "gender")
    private String gender;
    @Column(name = "specialization")
    private String specialization;
    @Column(name = "department")
    private String department;
    @Column(name = "years_of_experience")
    private int yearsOfExperience;
    @Column(name = "license_number")
    private String licenseNumber;
    @Column(name = "qualification")
    private String qualification;
    @Column(name = "working_hours")
    private String workingHours;
    @Column(name = "date_joined")
    private LocalDate dateJoined;
    @Column(name = "address")
    private String address;
    @Column(name = "status")
    private Integer status;
}
