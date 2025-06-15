package com.Patient_system.Patient._Aplication.repository;

import com.Patient_system.Patient._Aplication.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity,Long> {


}
