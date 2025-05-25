package com.Patient_system.Patient._Aplication.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO implements Serializable {
    private long tid;
    private String doctorID;
    @NotBlank(message = "first name cannot be empty")
    private String doctorFirstName;
    @NotBlank(message = "last name cannot be empty")
    private String lastName;
    @NotBlank(message = "middle name cannot be empty")
    private String middleName;
    @NotBlank(message = "email cannot be empty")
    private String email;
    @NotBlank(message = "phone number cannot be empty")
    private String phoneNumber;
    @NotBlank(message = "gender cannot be empty")
    private String gender;
    @NotBlank(message = "specialization cannot be empty")
    private String specialization;
    @NotBlank(message = "department cannot be empty")
    private String department;
    @NotNull(message = "experience cannot be empty")
    private int yearsOfExperience;
    @NotBlank(message = "license Number cannot be empty")
    private String licenseNumber;
    @NotBlank(message = "qualification cannot be empty")
    private String qualification;
    @NotBlank(message = "what are your working hours")
    private String workingHours;
    private LocalDate dateJoined;
    @NotBlank(message = "provide your address")
    private String address;
    private Integer status;
}
