package com.Patient_system.Patient._Aplication.service.impl;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.request.DoctorDTO;
import com.Patient_system.Patient._Aplication.dto.request.DoctorPatientListDiagnosisDTO;
import com.Patient_system.Patient._Aplication.dto.response.DoctorResponseDTO;
import com.Patient_system.Patient._Aplication.entity.DiagnosisEntity;
import com.Patient_system.Patient._Aplication.entity.DoctorEntity;
import com.Patient_system.Patient._Aplication.entity.PatientEntity;
import com.Patient_system.Patient._Aplication.exceptions.DoctorExistException;
import com.Patient_system.Patient._Aplication.exceptions.DoctorNotFoundException;
import com.Patient_system.Patient._Aplication.exceptions.PatientNotFoundException;
import com.Patient_system.Patient._Aplication.repository.DiagnosisRepository;
import com.Patient_system.Patient._Aplication.repository.DoctorRepository;
import com.Patient_system.Patient._Aplication.repository.PatientRepository;
import com.Patient_system.Patient._Aplication.service.DoctorService;
import com.Patient_system.Patient._Aplication.utils.db.DoctorDBUtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service

public class DoctorServiceImpl implements DoctorService {
    // @Autowired
    private final DoctorDBUtilService doctorDBUtilService;

    //private final DoctorDTO doctorDTO;
    public BaseApiResponse addDoctor(DoctorDTO doctorDTO) throws Exception {
        var doctorEntity = doctorDBUtilService.checkDoctor(doctorDTO.getDoctorID());
        log.info("DoctorEntity:{}", doctorEntity.isPresent());

        if (doctorEntity.isPresent()) {
            log.info("doctor already exist");
            throw new DoctorExistException("Doctor with ID " + doctorDTO.getDoctorID() + " already exists");
        }
// check by email
        var doctorByEmail = doctorDBUtilService.checkDoctorByEmail(doctorDTO.getEmail());
        log.info("DoctorEntity by Email: {}", doctorByEmail.isPresent());

        if (doctorByEmail.isPresent()) {
            log.info("Doctor already exists with email");
            throw new DoctorExistException("Doctor with email " + doctorDTO.getEmail() + " already exists");
        }
        var checklicense = doctorDBUtilService.checkDoctorByLicenseNumber(doctorDTO.getLicenseNumber());
        if (checklicense.isPresent()) {
            log.info("Doctor already exists with the license");
            throw new DoctorExistException("Doctor with license " + doctorDTO.getLicenseNumber() + " already exists");

        }
        // generate custom doctor ID
        String prefix = "DOC-";
        char firstL = Character.toUpperCase(doctorDTO.getDoctorFirstName().charAt(0));
        char secondL = Character.toUpperCase(doctorDTO.getMiddleName().charAt(0));
        String numberPart = String.format("%05d", new Random().nextInt(10_000_000));
        String doctorID = prefix + firstL + secondL + numberPart;

        var doctors = DoctorEntity.builder()
                .doctorID(doctorID)
                .doctorFirstName(doctorDTO.getDoctorFirstName())
                .middleName(doctorDTO.getMiddleName())
                .lastName(doctorDTO.getLastName())
                .phoneNumber(doctorDTO.getPhoneNumber())
                .gender(doctorDTO.getGender())
                .address(doctorDTO.getAddress())
                .licenseNumber(doctorDTO.getLicenseNumber())
                .email(doctorDTO.getEmail())
                .qualification(doctorDTO.getQualification())
                .dateJoined(doctorDTO.getDateJoined())
                .specialization(doctorDTO.getSpecialization())
                .yearsOfExperience(doctorDTO.getYearsOfExperience())
                .workingHours(doctorDTO.getWorkingHours())
                .department(doctorDTO.getDepartment())
                .status(1)
                .build();
        DoctorEntity savedoctors = doctorDBUtilService.saveDoctorDetails(doctors);
        return new BaseApiResponse(true, 200, "Doctor " + doctorDTO.getDoctorFirstName() + "'s details saved successfully", null);

    }

    //getting doctors' list
    @Autowired
    private final DoctorRepository doctorRepository;

    public BaseApiResponse getDoctorList() throws Exception {
        List<DoctorEntity> doctors = doctorRepository.findAll();
        var doctorsDetails = doctors.stream().map(doctor -> {
            DoctorResponseDTO dto = new DoctorResponseDTO();
            dto.setDoctorId(doctor.getDoctorID());
            dto.setDoctorFirstName(doctor.getDoctorFirstName());
            dto.setMiddleName(doctor.getMiddleName());
            dto.setLastName(doctor.getLastName());
            dto.setEmail(doctor.getEmail());
            dto.setAddress(doctor.getAddress());
            dto.setDateJoined(doctor.getDateJoined());
            dto.setYearsOfExperience(doctor.getYearsOfExperience());
            dto.setLicenseNumber(doctor.getLicenseNumber());
            dto.setDepartment(doctor.getDepartment());
            //dto.setStatus(doctor.getStatus());
            dto.setGender(doctor.getGender());
            dto.setQualification(doctor.getQualification());
            dto.setPhoneNumber(doctor.getPhoneNumber());
            dto.setWorkingHours(doctor.getWorkingHours());
            dto.setSpecialization(doctor.getSpecialization());
            dto.setWorkingHours(doctor.getWorkingHours());
            return dto;

        }).collect(Collectors.toList());
        return BaseApiResponse.builder()
                .data(doctorsDetails)
                .status(200)
                .message("List of Doctor's details fetched successfully")
                .build();

    }

    //@Override
    public BaseApiResponse editDoctorDetailsByDoctorID(String doctorID, DoctorDTO doctorDTO) throws Exception {
        DoctorEntity doctorEntity = doctorRepository.checkByDoctorID(doctorID);
        if (doctorEntity == null) {
            throw new Exception("Doctor with DoctorID " + doctorID + " not found.");

        }
        if (doctorEntity.getStatus() == 0) {
            throw new Exception("Doctor with Doctor ID " + doctorID + " is already deactivated.");

        }
        doctorEntity.setDoctorFirstName(doctorDTO.getDoctorFirstName());
        doctorEntity.setMiddleName(doctorDTO.getMiddleName());
        doctorEntity.setLastName(doctorDTO.getLastName());
        doctorEntity.setEmail(doctorDTO.getEmail());
        doctorEntity.setAddress(doctorDTO.getAddress());
        doctorEntity.setDateJoined(doctorDTO.getDateJoined());
        doctorEntity.setYearsOfExperience(doctorDTO.getYearsOfExperience());
        doctorEntity.setLicenseNumber(doctorDTO.getLicenseNumber());
        doctorEntity.setDepartment(doctorDTO.getDepartment());
        doctorEntity.setGender(doctorDTO.getGender());
        doctorEntity.setQualification(doctorDTO.getQualification());
        doctorEntity.setPhoneNumber(doctorDTO.getPhoneNumber());
        doctorEntity.setWorkingHours(doctorDTO.getWorkingHours());
        doctorEntity.setSpecialization(doctorDTO.getSpecialization());
        doctorEntity.setWorkingHours(doctorDTO.getWorkingHours());
        // Save the updated entity
        DoctorEntity updatedDoctor = doctorRepository.save(doctorEntity);

        return new BaseApiResponse(true, 200, "Doctor details updated successfully", updatedDoctor);


    }

    public BaseApiResponse deactivateDoctorByDoctorID(String doctorID) throws Exception {
        DoctorEntity doctorEntity = doctorRepository.checkByDoctorID(doctorID);
        if (doctorEntity == null) {
            throw new Exception("Doctor with DoctorID " + doctorID + " does not exist!.");

        }
        if (doctorEntity.getStatus() == 0) {
            throw new Exception("Doctor with Doctor ID " + doctorID + " is already deactivated.");

        }
        doctorEntity.setStatus(0);
        DoctorEntity deactivateDoctor = doctorRepository.save(doctorEntity);

        return new BaseApiResponse(true, 200, "Doctor details updated successfully", deactivateDoctor);


    }

    @Override
    public BaseApiResponse fetchDoctorPatientDiagnosis(String doctorID) throws Exception {
       //fetchDoctorPatientDiagnosis is from doctor service eing implemented here
        var doctor = doctorDBUtilService.checkDoctorbyDotorID(doctorID);

        if (doctor.isEmpty()) {
            throw new DoctorNotFoundException("Doctor  with ID: " +doctorID +"not found");
        }
        List<Map<String, Object>> result = doctorRepository.getPatientDiagnosesByDoctorID(doctorID);
        if (result.isEmpty()) {
            return BaseApiResponse.builder() //
                    .status(404)
                    .message("No data available for this doctor.")
                    .data(null)
                    .build();
        }

        return BaseApiResponse.builder()
                .status(200)
                .message("Doctor list of Diagnosed patients retrieved successfully.")
                .data(result)
                .build();
    }

    @Autowired
    private final PatientRepository patientRepository;
    private final DiagnosisRepository diagnosisRepository;
   // private final DoctorRepository doctorRepository;

    public BaseApiResponse getDoctorWithDiagnoses(String doctorID) throws Exception {
        var doctor = doctorDBUtilService.checkDoctorbyDotorID(doctorID);

        if (doctor.isEmpty()) {
            throw new DoctorNotFoundException("Doctor  with ID: " +doctorID +" not found");
        }
        var checkDoctor = diagnosisRepository.findByDoctorDoctorID(doctorID);
        if (checkDoctor.isEmpty()) {
            throw new DoctorNotFoundException("Doctor  with ID: " +doctorID +" has not diagnosed any patient");
        }
        //findByDoctor_DoctorID exist in the diagnosis repo
        List<DiagnosisEntity> diagnoses = diagnosisRepository.findByDoctorDoctorID(doctorID);
        List<DoctorPatientListDiagnosisDTO> dtoList = diagnoses.stream()
                .map(diagnosis -> {
                    PatientEntity patient = diagnosis.getPatient();
                    DoctorEntity doctors =diagnosis.getDoctor();
                    return new DoctorPatientListDiagnosisDTO(
                            doctors.getDoctorID(),
                            doctors.getDoctorFirstName(),
                            doctors.getQualification(),

                            patient.getFirstName(),
                            patient.getLastName(),
                            patient.getMiddleName(),

                            diagnosis.getSymptoms(),
                            diagnosis.getSeverity(),
                            diagnosis.getDateofDiagnosis()


                            );
                }).collect(Collectors.toList());

        // 4. Return response
        return BaseApiResponse.builder()
                .data(dtoList)
                .status(200)
                .message("Doctor and associated diagnoses fetched successfully")
                .build();

        }


}