package com.Patient_system.Patient._Aplication.service.impl;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.request.DiagnosisDTO;
import com.Patient_system.Patient._Aplication.dto.request.PatientDTO;
import com.Patient_system.Patient._Aplication.dto.response.DiagnosisResponseDTO;
import com.Patient_system.Patient._Aplication.entity.DiagnosisEntity;
import com.Patient_system.Patient._Aplication.exceptions.DiagnosisNotFoundException;
import com.Patient_system.Patient._Aplication.exceptions.PatientNotFoundException;
import com.Patient_system.Patient._Aplication.repository.DiagnosisRepository;
import com.Patient_system.Patient._Aplication.repository.PatientRepository;
import com.Patient_system.Patient._Aplication.service.DiagnosisService;
import com.Patient_system.Patient._Aplication.utils.db.DiagnosisDbUtilService;
import com.Patient_system.Patient._Aplication.utils.db.DoctorDBUtilService;
import com.Patient_system.Patient._Aplication.utils.db.PatientDbUtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Slf4j
@Service
public class DiagnosisServiceImpl implements DiagnosisService {
    @Autowired
    private final PatientDbUtilService patientDbUtilService;
    private final DiagnosisDbUtilService diagnosisDbUtilService;
    private final DoctorDBUtilService doctorDBUtilService;
    private PatientRepository patientRepository;
    private PatientDTO patientDTO;
    private DiagnosisDTO diagnosisDTO;
    @Override
    public BaseApiResponse addDiagnosis(DiagnosisDTO diagnosisDTO) throws Exception{
        var patientEntity = patientDbUtilService.checkPatient(diagnosisDTO.getPatientID());
        log.info("patientEntity:{}", patientEntity.isPresent());

        if (patientEntity.isEmpty()){
            throw new Exception("Patient not found");
        }
        //checkin is doctor exists
        var doctorEntity = doctorDBUtilService.checkDoctor(String.valueOf(diagnosisDTO.getDoctorID()));
        log.info("doctorEntity:{}", doctorEntity.isEmpty());
        if (doctorEntity.isEmpty()){
            throw new Exception("doctor is not available, invalid doctor ID:" +diagnosisDTO.getDoctorID());
        }
        var diagnosis = DiagnosisEntity.builder()
                .symptoms(diagnosisDTO.getSymptoms())
                .dateofDiagnosis(diagnosisDTO.getDateofDiagnosis())
                .severity(diagnosisDTO.getSeverity())
               .doctorID(diagnosisDTO.getDoctorID())
                .patientID(diagnosisDTO.getPatientID())
                .build();
        DiagnosisEntity savediagnosis = diagnosisDbUtilService.saveDiagnosisDetails(diagnosis);
        return new BaseApiResponse(true, 200, "Diagnosis saved successfully", null);
    }
    @Autowired
    private final DiagnosisRepository diagnosisRepository;
    public BaseApiResponse getPatientDiagnosisList() throws Exception  {
        List<DiagnosisEntity> diagnoses = diagnosisRepository.findAll();
        var DiagnosisDetails = diagnoses.stream()
                .map( diagnosis ->{
                    DiagnosisResponseDTO dto = new DiagnosisResponseDTO();
                    dto.setPatientID(diagnosis.getPatientID());
                    dto.setSymptoms(diagnosis.getSymptoms());
                    dto.setSeverity(diagnosis.getSeverity());
                    dto.setDateofDiagnosis(diagnosis.getDateofDiagnosis());
                    dto.setDoctorID(diagnosis.getDoctorID());
                    return dto;

        }).collect(Collectors.toList());
        return BaseApiResponse.builder()
                .data(DiagnosisDetails)
                .status(200)
                .message("Patient diagnosis fetched successfully")
                .build();

    }
    // fetching using patient ID
    @Override
    public BaseApiResponse getDiagnosisByPatientID(String patientID) throws Exception  {
        // Check if patient exists
        var patientEntity = patientDbUtilService.checkPatient(patientID);
        log.info("patientEntity:{}", patientEntity.isPresent());

        if (patientEntity.isEmpty()){
            throw new PatientNotFoundException("Patient not diagnosed");

        }

        List<DiagnosisEntity> diagnoses = diagnosisRepository.findByPatientID(patientID);
        var DiagnosisDetails = diagnoses.stream()
                .map( diagnosis ->{
                    DiagnosisResponseDTO dto = new DiagnosisResponseDTO();
                    dto.setPatientID(diagnosis.getPatientID());
                    dto.setSymptoms(diagnosis.getSymptoms());
                    dto.setSeverity(diagnosis.getSeverity());
                    dto.setDateofDiagnosis(diagnosis.getDateofDiagnosis());
                    dto.setDoctorID(diagnosis.getDoctorID());
                    return dto;

                }).collect(Collectors.toList());
        return BaseApiResponse.builder()
                .data(DiagnosisDetails)
                .status(200)
                .message("Patient diagnosis fetched successfully")
                .build();

    }
    //selection of join patient and diagnosis// implemeents fetchDiagnosisAndPatientName from diagnosis serivce
    @Override
    public BaseApiResponse fetchDiagnosisAndPatientName(String patientID) throws Exception {
        var patient = patientDbUtilService.checkPatient(patientID);
        if (patient.isEmpty()) {
            throw new PatientNotFoundException("Patient not found with ID: " + patientID);
        }

        var diagnosis = diagnosisDbUtilService.checkDiagnosisByPatientID(patientID);
        if (diagnosis.isEmpty()) {
            throw new DiagnosisNotFoundException("No diagnosis found for patient ID: " + patientID);
        }


        List<Map<String, Object>> result = diagnosisRepository.fetchDiagnosisWithPatientFullNameByPatientID(patientID);

        if (result.isEmpty()) {
            return BaseApiResponse.builder()
                    .status(404)
                    .message("No diagnosis data available for this patient.")
                    .data(null)
                    .build();
        }

        return BaseApiResponse.builder()
                .status(200)
                .message("Diagnosis and patient full name retrieved successfully.")
                .data(result)
                .build();
    }
}


