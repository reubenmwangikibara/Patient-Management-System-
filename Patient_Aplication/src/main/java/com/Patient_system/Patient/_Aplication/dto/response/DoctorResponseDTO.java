package com.Patient_system.Patient._Aplication.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class DoctorResponseDTO {
   // private long tid;
    private String doctorId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phoneNumber;
    private String gender;
    private String specialization;
    private String department;
    private int yearsOfExperience;
    private String licenseNumber;
    private String qualification;
    private String workingHours;
    private LocalDate dateJoined;
    private String address;
    private Integer status;
}
