package com.Patient_system.Patient._Aplication.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor

public class DoctorPatientListDiagnosisDTO implements Serializable {
    //doctor details
    private String doctorID;
    private String doctorFirstName;
    private String qualification;

    //patient details

    private String firstName;
    private String middleName;
    private String lastName;

    private String symptoms;
    private String severity;
    private LocalDate dateofDiagnosis;

}
