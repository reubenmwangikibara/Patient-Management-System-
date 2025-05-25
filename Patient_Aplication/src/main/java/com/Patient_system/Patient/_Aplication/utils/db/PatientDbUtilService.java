package com.Patient_system.Patient._Aplication.utils.db;

import com.Patient_system.Patient._Aplication.entity.DoctorEntity;
import com.Patient_system.Patient._Aplication.entity.PatientEntity;
import com.Patient_system.Patient._Aplication.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@RequiredArgsConstructor
@Slf4j
@Service
public class PatientDbUtilService {
    @Autowired
    private PatientRepository patientRepository;
    //saving the patientdetails
    public PatientEntity savePatientDetails(PatientEntity patient) {

        return patientRepository.save(patient);
    }
    public Optional<PatientEntity> checkIfPatientExist(Long nationalID) {
        log.info("Checking if nationalID exists: {}", nationalID);

        return patientRepository.findByNationalID(nationalID);
    }
    // checking of patient exists for diagnosis
    public Optional<PatientEntity> checkPatient(String patientID) {
        log.info("Checking if patient exists by patient-ID: {}", patientID);
        return patientRepository.findByPatientID(patientID);

    }

}
