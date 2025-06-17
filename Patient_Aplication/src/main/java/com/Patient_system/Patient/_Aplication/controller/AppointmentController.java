package com.Patient_system.Patient._Aplication.controller;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.request.AppointmentDTO;
import com.Patient_system.Patient._Aplication.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("{patientID}")
    public BaseApiResponse getAppointment (@PathVariable String patientID) throws Exception{
        return appointmentService.fetchAppointmentByPatientID(patientID);

    }
}
