package com.Patient_system.Patient._Aplication.utils.db;

import com.Patient_system.Patient._Aplication.entity.AppointmentEntity;
import com.Patient_system.Patient._Aplication.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentDbUtilService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentEntity saveAppointment( AppointmentEntity appointment){
        log.info("Saving appointment: {}", appointment);
        return appointmentRepository.save(appointment);
    }

}
