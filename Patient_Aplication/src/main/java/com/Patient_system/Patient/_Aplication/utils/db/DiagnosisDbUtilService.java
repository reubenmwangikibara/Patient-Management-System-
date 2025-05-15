package com.Patient_system.Patient._Aplication.utils.db;

import com.Patient_system.Patient._Aplication.entity.DiagnosisEntity;
import com.Patient_system.Patient._Aplication.entity.PatientEntity;
import com.Patient_system.Patient._Aplication.repository.DiagnosisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service

public class DiagnosisDbUtilService {
    @Autowired

    private DiagnosisRepository diagnosisRepository;
    public DiagnosisEntity saveDiagnosisDetails(DiagnosisEntity diagnosis) {

        return diagnosisRepository.save(diagnosis);
    }


    //@Override
    public List<DiagnosisEntity> getDiagnosisByPatientID(String patientID) {
        return diagnosisRepository.findByPatientID(patientID);
    }

    public Optional<DiagnosisEntity> checkDiagnosedPatient(String patientID) {
        log.info("Checking if patient exists by patient-ID: {}", patientID);
        // Since the repository method returns a List, you handle it like this:
        List<DiagnosisEntity> results = diagnosisRepository.findByPatientID(patientID);

        // Return first match as Optional, or Optional.empty() if none
        return results.stream().findFirst();
    }
    public Optional<DiagnosisEntity> checkDiagnosisByPatientID(String patientID) {
        return diagnosisRepository.findByPatientID(patientID).stream().findFirst();
    }




}


