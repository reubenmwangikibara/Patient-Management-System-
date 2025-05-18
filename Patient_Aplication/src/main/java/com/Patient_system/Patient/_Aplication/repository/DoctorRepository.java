package com.Patient_system.Patient._Aplication.repository;

import com.Patient_system.Patient._Aplication.entity.DiagnosisEntity;
import com.Patient_system.Patient._Aplication.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
    Optional<DoctorEntity>findByDoctorID(String doctorID);
    Optional<DoctorEntity> findByEmail(String email);
    @Query("SELECT p FROM DoctorEntity p")
    List<DoctorEntity> findAll();

}
