package com.Patient_system.Patient._Aplication.controller;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.request.DoctorDTO;
import com.Patient_system.Patient._Aplication.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path= "api/v1/doctor")
@RestController
@RequiredArgsConstructor

public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @PostMapping("/add")
    public BaseApiResponse addDoctor(@Valid @RequestBody DoctorDTO doctorDTO) throws Exception{
        return doctorService.addDoctor(doctorDTO);

    }
    @GetMapping("/Doctor-List")
    public ResponseEntity<BaseApiResponse> getDoctorList () throws Exception {
        BaseApiResponse response = doctorService.getDoctorList();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @PutMapping("/Doctor-Update-details/{doctorID}")
    public ResponseEntity<BaseApiResponse> updateDoctorDetails(
            @PathVariable String doctorID,
            @RequestBody DoctorDTO doctorDTO) throws Exception {
       BaseApiResponse response = doctorService.editDoctorDetailsByDoctorID(doctorID,doctorDTO);
       return ResponseEntity.ok(response);

    }
    //deactivating the doctor
    @PutMapping("/deactive-doctor/{doctorID}")
    public ResponseEntity<BaseApiResponse> deactivateDoctor (
            @PathVariable String doctorID) throws Exception {
        BaseApiResponse response = doctorService.deactivateDoctorByDoctorID(doctorID);
        return  ResponseEntity.ok(response);
    }
    @GetMapping("/doctor-diagnosis/{doctorID}")
    public BaseApiResponse getDiagnosisAndPatientName(@PathVariable String doctorID) throws Exception {
        return doctorService.fetchDoctorPatientDiagnosis(doctorID);
    }
    //useing dto
    @GetMapping ("doctor_list_ofdiagnosis/{doctorID}")
    public BaseApiResponse getDoctorWithDiagnoses(@PathVariable String doctorID) throws Exception{
        return doctorService.getDoctorWithDiagnoses(doctorID);
    }
}
