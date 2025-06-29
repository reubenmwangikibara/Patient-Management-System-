package com.Patient_system.Patient._Aplication.service;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.request.AppointmentDTO;
import com.Patient_system.Patient._Aplication.entity.AppointmentEntity;
import com.Patient_system.Patient._Aplication.entity.DiagnosisEntity;

public interface AppointmentService {
    BaseApiResponse bookAppointment(AppointmentDTO appointmentDTO) throws Exception;
    BaseApiResponse fetchAppointmentByPatientID(String patientID, String appointmentID)throws Exception;
    BaseApiResponse fetchAllAppointments() throws Exception;
    BaseApiResponse editAppointmentDetails(String appointmentID,AppointmentDTO appointmentDTO) throws Exception;

    BaseApiResponse completeAppointment(String appointmentID) throws Exception;
}
