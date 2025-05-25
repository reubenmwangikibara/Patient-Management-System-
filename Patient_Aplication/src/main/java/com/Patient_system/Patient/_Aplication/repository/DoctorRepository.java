package com.Patient_system.Patient._Aplication.repository;

import com.Patient_system.Patient._Aplication.entity.DiagnosisEntity;
import com.Patient_system.Patient._Aplication.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
    Optional<DoctorEntity>findByDoctorID(String doctorID);
    Optional<DoctorEntity> findByEmail(String email);
    @Query("SELECT p FROM DoctorEntity p")
    List<DoctorEntity> findAll();

    @Query("SELECT d FROM DoctorEntity d WHERE d.doctorID = :doctorID")
    DoctorEntity checkByDoctorID(@Param("doctorID") String doctorID);

    @Query("SELECT d FROM DoctorEntity d WHERE d.status = :status")
    DoctorEntity checkBYStatus(@Param("doctorID") String status);
    Optional<DoctorEntity> findByLicenseNumber(String licenseNumber);
    Optional<DoctorEntity> findByPhoneNumber(String phoneNumber);
    @Query(value = "SELECT " +
            "doc.first_name AS doctorFirstName, " +
            "doc.last_name AS doctorLastName, " +
            "p.first_name AS firstName, " +
            "p.middle_name AS middleName, " +
            "p.last_name AS lastName, " +
            "d.symptoms AS symptoms, " +
            "d.severity AS severity, " +
            "d.date_of_diagnosis AS dateOfDiagnosis " +
            "FROM diagnosis d " +
            "JOIN doctors doc ON d.doctor_id = doc.doctor_id " +
            "JOIN patientinformation p ON d.patient_id = p.patient_id " +
            "WHERE d.doctor_id = :doctorID",
            nativeQuery = true)
    List<Map<String, Object>> getPatientDiagnosesByDoctorID(@Param("doctorID") String doctorID);


}
