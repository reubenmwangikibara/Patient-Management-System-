package com.Patient_system.Patient._Aplication.controller;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.request.AppointmentDTO;
import com.Patient_system.Patient._Aplication.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path= "api/v1/Appointment")
@RequiredArgsConstructor
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    private AppointmentDTO appointmentDTO;
    @PostMapping("/add")
    public BaseApiResponse bookAppointment(@Valid @RequestBody AppointmentDTO appointmentDTO) throws Exception{
        return appointmentService.bookAppointment(appointmentDTO);

    }
//    @GetMapping("{patientID}")
//    public BaseApiResponse getAppointment (@PathVariable String patientID, String appointmentID) throws Exception{
//        return appointmentService.fetchAppointmentByPatientID(patientID, appointmentID);
//
//    }
    @GetMapping("/Appointment-List")
    public BaseApiResponse getAllAppointments() throws Exception{
        return appointmentService.fetchAllAppointments();
    }
    @GetMapping("/appointments")
    public BaseApiResponse getAppointment (
            @RequestParam(required = false) String patientID,
            @RequestParam(required = false) String appointmentID) throws Exception{
        return appointmentService.fetchAppointmentByPatientID(patientID, appointmentID);

    }
}
