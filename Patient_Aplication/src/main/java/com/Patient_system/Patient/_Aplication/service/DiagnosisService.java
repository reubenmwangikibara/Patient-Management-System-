package com.Patient_system.Patient._Aplication.service;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.request.DiagnosisDTO;
import com.Patient_system.Patient._Aplication.dto.request.PatientDTO;

public interface DiagnosisService {

    public BaseApiResponse addDiagnosis (DiagnosisDTO diagnosisDTO) throws Exception;
     BaseApiResponse getPatientDiagnosisList() throws Exception;
    //selects a specific patient by id
    BaseApiResponse getDiagnosisByPatientID(String patientID)throws Exception;
    BaseApiResponse fetchDiagnosisAndPatientName(String patientID) throws Exception;





}
