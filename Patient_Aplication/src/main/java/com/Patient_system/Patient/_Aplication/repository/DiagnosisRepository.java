package com.Patient_system.Patient._Aplication.repository;

import com.Patient_system.Patient._Aplication.entity.DiagnosisEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository

public interface DiagnosisRepository extends JpaRepository <DiagnosisEntity, Long> {
    @Autowired
//public DiagnosisRepository diagnosisRepository;

    @Query("SELECT p FROM DiagnosisEntity p")
    List<DiagnosisEntity> findAll();
    List<DiagnosisEntity> findByPatientID(String patientID);
    //selecting diagnosed patient
    //List<DiagnosisEntity> checkDiagnosedByPatientID(String patientID);
    @Query("SELECT d FROM DiagnosisEntity d JOIN d.patient p WHERE p.patientID = :patientID")
    List<DiagnosisEntity> findDiagnosesWithPatientDetails(@Param("patientID") String patientID);

 //combining patient and diagnosis

@Query(value = "SELECT d.patient_id AS patientID, " +
        "p.first_name AS firstName, " +
        "p.middle_name AS middleName, " +
        "p.last_name AS lastName, " +
        "d.symptoms AS symptoms, " +
        "d.severity AS severity, " +
        "d.doctor_id AS doctorID, " +
        "d.date_of_diagnosis AS dateOfDiagnosis " +  // Added date field
        "FROM diagnosis d " +
        "INNER JOIN patientinformation p ON d.patient_id = p.patient_id " +
        "WHERE d.patient_id = :patientID " +
        "ORDER BY d.date_of_diagnosis DESC",  // Order by diagnosis date
        nativeQuery = true)
List<Map<String, Object>> fetchDiagnosisWithPatientFullNameByPatientID(@Param("patientID") String patientID);
}
