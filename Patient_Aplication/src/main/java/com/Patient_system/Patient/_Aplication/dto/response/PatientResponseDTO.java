package com.Patient_system.Patient._Aplication.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@Data
@NoArgsConstructor

public class PatientResponseDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateofBirth;
    private String gender;
    private String   maritalStatus;
    private Long nationalID;
    private String patientID;
    private String homeAddress;
    private String phoneNumber;
    private String email;
    private String emergencyContact;
    private Integer status;
}
