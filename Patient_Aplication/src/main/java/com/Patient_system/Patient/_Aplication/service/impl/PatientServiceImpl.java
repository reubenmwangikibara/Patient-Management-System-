package com.Patient_system.Patient._Aplication.service.impl;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.request.PatientDTO;
import com.Patient_system.Patient._Aplication.dto.response.PatientResponseDTO;
import com.Patient_system.Patient._Aplication.entity.PatientEntity;
import com.Patient_system.Patient._Aplication.exceptions.PatientExistException;
import com.Patient_system.Patient._Aplication.repository.PatientRepository;
import com.Patient_system.Patient._Aplication.service.PatientService;
import com.Patient_system.Patient._Aplication.utils.db.PatientDbUtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    @Autowired
    private final PatientDbUtilService patientDbUtilService;


    @Override
    public BaseApiResponse addNewPatient(PatientDTO patientDTO) throws Exception {
        var patientEntity = patientDbUtilService.checkIfPatientExist(patientDTO.getNationalID());
        log.info("patientEntity:{}", patientEntity.isPresent());
        //log.info("Query result for nationalID {}: {}", patientDTO.getNationalID(),patientEntity);

        if (patientEntity.isPresent()) {
            log.info("user already exist");
            throw new PatientExistException("user already exists");
        }
        char firstletter = Character.toUpperCase(patientDTO.getFirstName().charAt(0));
        char secondletter = Character.toUpperCase(patientDTO.getLastName().charAt(0));
        char thirdletter = Character.toUpperCase(patientDTO.getMiddleName().charAt(0));
        String numberPart = String.format("%05d", new Random().nextInt(10_000_000));
        String patientID = String.valueOf(firstletter) + secondletter + thirdletter + numberPart;

        var patient = PatientEntity.builder()
                .patientID(patientID)
                .firstName(patientDTO.getFirstName())
                .middleName(patientDTO.getMiddleName())
                .lastName(patientDTO.getLastName())
                .dateofBirth(patientDTO.getDateofBirth())
                .gender(patientDTO.getGender())
                .maritalStatus(patientDTO.getMaritalStatus())
                .nationalID(patientDTO.getNationalID())
                .homeAddress(patientDTO.getHomeAddress())
                .phoneNumber(patientDTO.getPhoneNumber())
                .email(patientDTO.getEmail())
                .emergencyContact(patientDTO.getEmergencyContact())
                .status(1)
                .build();
        PatientEntity savedPatient = patientDbUtilService.savePatientDetails(patient);
        return new BaseApiResponse(true, 200, "Patient saved successfully", null);


    }

   @Autowired
   //private PatientRepository patientRepository;

   private final PatientRepository patientRepository;
//    @Override
//
//    public BaseApiResponse getPatientList() throws Exception {
//        List<PatientEntity> patients = patientRepository.findAll();
//        var patientDetails = patients.stream()
//                .map(patient -> {
//                    PatientResponseDTO dto = new PatientResponseDTO();
//                    dto.setFirstName(patient.getFirstName());
//                    dto.setMiddleName(patient.getMiddleName());
//                    dto.setLastName(patient.getLastName());
//                    dto.setEmail(patient.getEmail());
//                    dto.setDateofBirth(patient.getDateofBirth());
//                    dto.setEmergencyContact(patient.getEmergencyContact());
//                    dto.setHomeAddress(patient.getHomeAddress());
//                    dto.setPhoneNumber(patient.getPhoneNumber());
//                    return dto;
//
//                }).collect(Collectors.toList());
//        return BaseApiResponse.builder()
//                .data(patientDetails) // Set data as the list of employee DTOs
//                .status(200) // Success status
//                .message("Patient fetched successfully")
//                .build();
//
//    }
//@Override
public BaseApiResponse getPatientList() throws Exception {
    List<PatientEntity> patients = patientRepository.findAll();
    var patientDetails = patients.stream()
            .map(patient -> {
                PatientResponseDTO dto = new PatientResponseDTO();
                dto.setFirstName(patient.getFirstName());
                dto.setMiddleName(patient.getMiddleName());
                dto.setLastName(patient.getLastName());
                dto.setEmail(patient.getEmail());
                dto.setPatientID(patient.getPatientID());
                dto.setNationalID(patient.getNationalID());
                dto.setDateofBirth(patient.getDateofBirth());
                dto.setEmergencyContact(patient.getEmergencyContact());
                dto.setHomeAddress(patient.getHomeAddress());
                dto.setPhoneNumber(patient.getPhoneNumber());
                dto.setStatus(patient.getStatus());
                dto.setGender(patient.getGender());
                dto.setMaritalStatus(patient.getMaritalStatus());
                return dto;
            }).collect(Collectors.toList());

    return BaseApiResponse.builder()
            .data(patientDetails)
            .status(200)
            .message("Patients fetched successfully")
            .build();
}

}