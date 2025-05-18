package com.Patient_system.Patient._Aplication.utils.db;

import com.Patient_system.Patient._Aplication.entity.DiagnosisEntity;
import com.Patient_system.Patient._Aplication.entity.DoctorEntity;
import com.Patient_system.Patient._Aplication.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@RequiredArgsConstructor
@Slf4j
@Service

public class DoctorDBUtilService {
    @Autowired
    private DoctorRepository doctorRepository;

    public Optional <DoctorEntity> checkDoctor(String doctorID){
       log.info("Checking if doctor exists by Doctor-ID: {}", doctorID);
        return doctorRepository.findByDoctorID( doctorID);


    }
    // checking by email
    public Optional<DoctorEntity> checkDoctorByEmail(String email) {
        return doctorRepository.findByEmail(email); // assumes you have this method in your repository
    }

    // saving doctors details
    public DoctorEntity saveDoctorDetails(DoctorEntity doctors){
        return doctorRepository.save(doctors);

    }




}
