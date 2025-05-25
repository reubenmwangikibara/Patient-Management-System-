package com.Patient_system.Patient._Aplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "diagnosis")


public class DiagnosisEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    @Column(name = "tid", nullable = false)
    private long tid;
    @Column(name = "symptoms")
    private String symptoms;
    @Column(name = "date_of_diagnosis")
    private LocalDate dateofDiagnosis;
    @Column(name = "severity")
    private String severity;
    @Column(name = "doctor_id")
    private String doctorID;
    @Column(name = "patient_id")
    private String patientID;
    //linking with patient db
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", insertable = false, updatable = false)
    private PatientEntity patient;

    // linking with doctor to fetch the doctor_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", referencedColumnName = "doctor_id", insertable = false, updatable = false)
    private DoctorEntity doctor;


}
