package com.Patient_system.Patient._Aplication.service.impl;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.request.AppointmentDTO;
import com.Patient_system.Patient._Aplication.dto.request.DoctorDTO;
import com.Patient_system.Patient._Aplication.dto.request.PatientDTO;
import com.Patient_system.Patient._Aplication.dto.response.AppointmentResponseDTO;
import com.Patient_system.Patient._Aplication.entity.AppointmentEntity;
import com.Patient_system.Patient._Aplication.entity.DiagnosisEntity;
import com.Patient_system.Patient._Aplication.exceptions.DoctorExistException;
import com.Patient_system.Patient._Aplication.exceptions.PatientNotFoundException;
import com.Patient_system.Patient._Aplication.repository.AppointmentRepository;
import com.Patient_system.Patient._Aplication.service.AppointmentService;
import com.Patient_system.Patient._Aplication.utils.db.AppointmentDbUtilService;
import com.Patient_system.Patient._Aplication.utils.db.DoctorDBUtilService;
import com.Patient_system.Patient._Aplication.utils.db.PatientDbUtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final PatientDbUtilService patientDbUtilService;
    private final DoctorDBUtilService doctorDBUtilService;
    private final AppointmentDbUtilService appointmentDbUtilService;
    private final AppointmentRepository appointmentRepository;
    @Override
    public BaseApiResponse bookAppointment(AppointmentDTO appointmentDTO) throws Exception {

        var patientEntity = patientDbUtilService.checkPatient(appointmentDTO.getPatientID());
        log.info("Patient ID: {}, Found: {}", appointmentDTO.getPatientID(), patientEntity.isPresent());

//        if (patientEntity.isEmpty()){
//            throw new Exception("Patient not found");
//        }
        if (patientEntity.isEmpty()) {
            return BaseApiResponse.builder()
                    .data(null)
                    .status(404)
                    .message("Patient not found")
                    .errors("No patient with ID: " + appointmentDTO.getPatientID())
                    .build();
        }
        var doctorEntity = doctorDBUtilService.checkDoctor(appointmentDTO.getDoctorID());
        log.info("Doctor ID:{}, Found:{}", appointmentDTO.getDoctorID(), doctorEntity.isEmpty());

//        if (doctorEntity.isEmpty()) {
//            log.info("doctor not found");
//            throw new Exception("Doctor with ID " + appointmentDTO.getDoctorID() + " does not exists");
//        }
        if (doctorEntity.isEmpty()) {
            return BaseApiResponse.builder()
                    .data(null)
                    .status(404)
                    .message("Doctor not found")
                    .errors("No doctor with ID: " + appointmentDTO.getDoctorID())
                    .build();
        }
        String patientID =appointmentDTO.getPatientID();
        Integer status = appointmentDTO.getStatus() != null ? appointmentDTO.getStatus() : 1;  // Default to 1 if null

        //Integer status = appointmentDTO.getStatus(1);
        long appointmentCount = appointmentRepository.countByPatientIDAndStatus(patientID, status);
        if (appointmentCount >= 3 && status==1) {
           // log.info("Error in booking appointment!!");
            throw new IllegalStateException("Patient with ID " + patientID + " already has exceeded Appointment limit (3)");
        }

        String prefix = "APP-";
        String numberPart = String.format("%05d", new Random().nextInt(10_000_000));
        String appointID = prefix + numberPart;
        var appointment = AppointmentEntity.builder()
                .appointmentID(appointID)
                .patientID(appointmentDTO.getPatientID())
                .doctorID(appointmentDTO.getDoctorID())
                .appointmentReason(appointmentDTO.getAppointmentReason())
                .appointmentDate(LocalDateTime.now())
                .appointmentTime(LocalTime.now())
                .updatedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .status(1)
                .build();
        AppointmentEntity addAppointment = appointmentDbUtilService.saveAppointment(appointment);
        return new BaseApiResponse(
                (appointment),
                200,
                "Appointment Created successfully",
                null);
    }
    public BaseApiResponse fetchAppointmentByPatientID(String patientID) throws Exception  {
        // Check if patient exists
        var appointmentEntity = appointmentDbUtilService.checkPatient(patientID);
        log.info("patientEntity:{}", appointmentEntity.isEmpty());

        if (appointmentEntity.isEmpty()){
            throw new PatientNotFoundException("Patient does not have an appointment");

        }
        List< AppointmentEntity> appointments = appointmentRepository.findAllByPatientID(patientID);
        var appointmentsList = appointments.stream().map(
                appointment->{
                    AppointmentResponseDTO appointmentDTO = new AppointmentResponseDTO();
                    appointmentDTO.setAppointmentID(appointment.getAppointmentID());
                    appointmentDTO.setDoctorID(appointment.getDoctorID());
                    appointmentDTO.setPatientID(appointment.getPatientID());
                    appointmentDTO.setAppointmentReason(appointment.getAppointmentReason());
                    appointmentDTO.setAppointmentDate(appointment.getAppointmentDate());
                    return appointmentDTO;

        }).collect(Collectors.toList());
        return BaseApiResponse.builder()
                .data(appointmentsList)
                .status(200)
                .message("Patient Appointment fetched successfully")
                .build();

    }
}
