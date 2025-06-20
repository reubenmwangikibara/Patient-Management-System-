package com.Patient_system.Patient._Aplication.repository;

import com.Patient_system.Patient._Aplication.entity.AppointmentEntity;
import com.Patient_system.Patient._Aplication.entity.DiagnosisEntity;
import com.Patient_system.Patient._Aplication.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity,Long> {
    Optional<AppointmentEntity> findByPatientID(String patientID);
    List<AppointmentEntity> findAllByPatientIDOrAppointmentID(String patientID, String appointmentID);
    Long countByPatientIDAndStatus(String patientID, Integer status);
    Optional<AppointmentEntity>findByStatus(Integer status);
    List<AppointmentEntity> findAllByOrderByAppointmentDateAsc();



    @Query("SELECT a FROM AppointmentEntity a WHERE a.appointmentID = :appointmentID")
    AppointmentEntity checkByAppointmentID(@Param("appointmentID") String appointmentID);

    //AppointmentEntity checkByAppointmentID(String appointmentID);
}
