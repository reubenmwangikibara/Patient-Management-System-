package com.Patient_system.Patient._Aplication.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentResponseDTO {
    private String appointmentID;
    private String patientID;
    private String doctorID;
    private String appointmentReason;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
   // private Integer status;

}
