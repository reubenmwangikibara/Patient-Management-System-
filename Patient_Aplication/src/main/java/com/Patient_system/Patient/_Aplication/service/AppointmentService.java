package com.Patient_system.Patient._Aplication.service;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.request.AppointmentDTO;
import com.Patient_system.Patient._Aplication.entity.AppointmentEntity;
import com.Patient_system.Patient._Aplication.entity.DiagnosisEntity;

public interface AppointmentService {
    public BaseApiResponse bookAppointment(AppointmentDTO appointmentDTO) throws Exception;
}
