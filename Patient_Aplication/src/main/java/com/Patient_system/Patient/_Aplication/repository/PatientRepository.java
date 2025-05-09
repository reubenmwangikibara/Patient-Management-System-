package com.Patient_system.Patient._Aplication.repository;

import com.Patient_system.Patient._Aplication.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<PatientEntity,Long> {
//Optional<PatientEntity>checkIfPatientExist(Long nationalID);
    @Query("SELECT p FROM PatientEntity p WHERE p.nationalID = :nationalID")
    Optional<PatientEntity> checkIfPatientExist(@Param("nationalID") Long nationalID);
    Optional<PatientEntity>findByNationalID(Long nationalID);
@Query("SELECT p FROM PatientEntity p")
    List<PatientEntity> findAll();

}
