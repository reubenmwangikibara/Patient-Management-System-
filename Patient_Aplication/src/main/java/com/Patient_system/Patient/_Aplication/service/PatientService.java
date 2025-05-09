package com.Patient_system.Patient._Aplication.service;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.request.PatientDTO;

public interface PatientService {
    BaseApiResponse addNewPatient (PatientDTO patientDTO) throws Exception;
    BaseApiResponse getPatientList () throws Exception;
}
