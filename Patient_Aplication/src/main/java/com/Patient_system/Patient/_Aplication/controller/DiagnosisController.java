package com.Patient_system.Patient._Aplication.controller;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.request.DiagnosisDTO;
import com.Patient_system.Patient._Aplication.dto.response.FieldErrorDTO;
import com.Patient_system.Patient._Aplication.service.DiagnosisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "api/v1/diagnosis")
@RequiredArgsConstructor

public class DiagnosisController {
    @Autowired
    private DiagnosisService diagnosisService;
    @PostMapping("/add")
    public BaseApiResponse addDiagnosis(@Valid @RequestBody DiagnosisDTO diagnosisDTO) throws Exception{
        return diagnosisService.addDiagnosis(diagnosisDTO);

    }
    @GetMapping("/list-Diagnose")
    public ResponseEntity<BaseApiResponse>getPatientDiagnosisList (){
        try {

            BaseApiResponse response = diagnosisService.getPatientDiagnosisList();
            return ResponseEntity.status(response.getStatus()).body(response);
        }
        catch (Exception e) {
            // Handle exception and return a failed response
            BaseApiResponse errorResponse = BaseApiResponse.builder()
                    .status(500)
                    .message("An error occurred while fetching patient data.")
                    .errors(List.of(new FieldErrorDTO("unknown", e.getMessage())))
                    .build();
            return ResponseEntity.status(500).body(errorResponse);
        }

    }
    @GetMapping("{patientID}")
  public BaseApiResponse getPatientDiagnosisList(@PathVariable String patientID) throws Exception {
    return diagnosisService.getDiagnosisByPatientID(patientID);

    }
    @GetMapping("/patient-info/{patientID}")
    public BaseApiResponse getDiagnosisAndPatientName(@PathVariable String patientID) throws Exception {
        return diagnosisService.fetchDiagnosisAndPatientName(patientID);
    }



}
