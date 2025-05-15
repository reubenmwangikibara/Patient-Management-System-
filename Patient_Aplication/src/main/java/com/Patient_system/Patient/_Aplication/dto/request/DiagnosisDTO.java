package com.Patient_system.Patient._Aplication.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
@AllArgsConstructor
@Data
@NoArgsConstructor

public class DiagnosisDTO implements Serializable {
    private long tid;

   @NotBlank(message = "Symptoms cannot be blank")
    private String symptoms;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateofDiagnosis;
    @NotBlank(message = "Severity level required")
    private String severity;
    private long doctorID;
    //@NotBlank(message = "Patient ID is required")
    private String patientID;

}
