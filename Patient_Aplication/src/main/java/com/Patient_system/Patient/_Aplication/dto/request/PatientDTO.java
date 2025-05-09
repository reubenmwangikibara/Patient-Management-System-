package com.Patient_system.Patient._Aplication.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PatientDTO implements Serializable {
    private long tid;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateofBirth;
    private String gender;
    private String maritalStatus;
    private Long nationalID;
    private String patientID;
    private String homeAddress;
    private String phoneNumber;
    private String email;
    private String emergencyContact;
    private Integer status;
}
