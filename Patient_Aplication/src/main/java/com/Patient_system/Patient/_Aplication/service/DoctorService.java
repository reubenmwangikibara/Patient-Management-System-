package com.Patient_system.Patient._Aplication.service;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.request.DiagnosisDTO;
import com.Patient_system.Patient._Aplication.dto.request.DoctorDTO;
import com.Patient_system.Patient._Aplication.dto.request.DoctorPatientListDiagnosisDTO;

import java.util.List;

public interface
DoctorService {
    public BaseApiResponse addDoctor(DoctorDTO doctorDTO) throws Exception;
    public BaseApiResponse getDoctorList () throws Exception;
    public BaseApiResponse editDoctorDetailsByDoctorID(String doctorID,DoctorDTO doctorDTO) throws Exception;
    public BaseApiResponse deactivateDoctorByDoctorID(String doctorID) throws Exception;
    BaseApiResponse fetchDoctorPatientDiagnosis(String doctorID) throws Exception;
    BaseApiResponse getDoctorWithDiagnoses(String doctorId) throws Exception;



}
