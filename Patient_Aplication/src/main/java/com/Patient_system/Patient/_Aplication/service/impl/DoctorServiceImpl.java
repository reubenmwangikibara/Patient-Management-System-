package com.Patient_system.Patient._Aplication.service.impl;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.request.DoctorDTO;
import com.Patient_system.Patient._Aplication.dto.response.DoctorResponseDTO;
import com.Patient_system.Patient._Aplication.entity.DoctorEntity;
import com.Patient_system.Patient._Aplication.exceptions.DoctorExistException;
import com.Patient_system.Patient._Aplication.exceptions.DoctorNotFoundException;
import com.Patient_system.Patient._Aplication.repository.DoctorRepository;
import com.Patient_system.Patient._Aplication.service.DoctorService;
import com.Patient_system.Patient._Aplication.utils.db.DoctorDBUtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service

public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private final DoctorDBUtilService doctorDBUtilService;
    public BaseApiResponse addDoctor (DoctorDTO doctorDTO) throws Exception {
        var doctorEntity = doctorDBUtilService.checkDoctor(doctorDTO.getDoctorID());
       log.info("DoctorEntity:{}", doctorEntity.isPresent());

        if(doctorEntity.isPresent()){
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
        // generate custom doctor ID
        String prefix = "DOC-";
        char firstL = Character.toUpperCase(doctorDTO.getFirstName().charAt(0));
        char secondL = Character.toUpperCase(doctorDTO.getMiddleName().charAt(0));
        String numberPart = String.format("%05d", new Random().nextInt(10_000_000));
        String doctorID = String.valueOf(prefix) + firstL + secondL +numberPart;

        var doctors = DoctorEntity.builder()
                .doctorID(doctorID)
                .firstName(doctorDTO.getFirstName())
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
        DoctorEntity savedoctors= doctorDBUtilService.saveDoctorDetails(doctors);
        return new BaseApiResponse(true, 200, "Doctor " +doctorDTO.getFirstName()+ "'s details saved successfully", null);

    }
    //getting doctors' list
    @Autowired
    private final DoctorRepository doctorRepository;
    public BaseApiResponse getDoctorList () throws Exception{
        List< DoctorEntity> doctors = doctorRepository.findAll();
        var doctorsDetails = doctors.stream().map(doctor->{
            DoctorResponseDTO dto = new DoctorResponseDTO();
            dto.setDoctorId(doctor.getDoctorID());
            dto.setFirstName(doctor.getFirstName());
            dto.setMiddleName(doctor.getMiddleName());
            dto.setLastName(doctor.getLastName());
            dto.setEmail(doctor.getEmail());
            dto.setAddress(doctor.getAddress());
            dto.setDateJoined(doctor.getDateJoined());
            dto.setYearsOfExperience(doctor.getYearsOfExperience());
            dto.setLicenseNumber(doctor.getLicenseNumber());
            dto.setDepartment(doctor.getDepartment());
            dto.setStatus(doctor.getStatus());
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

    }

