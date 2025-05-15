package com.Patient_system.Patient._Aplication.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class DiagnosisResponseDTO {

    private String symptoms;
    private LocalDate dateofDiagnosis;
    private String severity;
    private long doctorID;
    private String patientID;

}
