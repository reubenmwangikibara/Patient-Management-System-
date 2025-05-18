package com.Patient_system.Patient._Aplication.service;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.request.DiagnosisDTO;
import com.Patient_system.Patient._Aplication.dto.request.DoctorDTO;

public interface DoctorService {
    public BaseApiResponse addDoctor(DoctorDTO doctorDTO) throws Exception;
    public BaseApiResponse getDoctorList () throws Exception;

}
