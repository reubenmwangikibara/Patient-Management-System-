package com.Patient_system.Patient._Aplication.utils.db;

import com.Patient_system.Patient._Aplication.entity.AppointmentEntity;
import com.Patient_system.Patient._Aplication.entity.PatientEntity;
import com.Patient_system.Patient._Aplication.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentDbUtilService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentEntity saveAppointment( AppointmentEntity appointment){
        log.info("Saving appointment: {}", appointment);
        return appointmentRepository.save(appointment);
    }
    public List<AppointmentEntity> checkPatient(String patientID, String appointmentID) {
        log.info("Checking if patient exists by patient-ID: {}", patientID);
        return appointmentRepository.findAllByPatientIDOrAppointmentID(patientID,appointmentID);

    }
    public Optional<AppointmentEntity> checkStatus(Integer status){
        return appointmentRepository.findByStatus(status);
    }
    public  AppointmentEntity checkByAppointmentID(String appointmentID){
        return appointmentRepository.checkByAppointmentID(appointmentID);
    }

}
