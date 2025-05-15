package com.Patient_system.Patient._Aplication.controller;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.request.PatientDTO;
import com.Patient_system.Patient._Aplication.dto.response.FieldErrorDTO;
import com.Patient_system.Patient._Aplication.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "api/v1/patient")
@RequiredArgsConstructor

public class PatientController {
    @Autowired
    private PatientService patientService;
    @PostMapping("/add")
        public BaseApiResponse addNewPatient (@RequestBody PatientDTO patientDTO) throws Exception{
        return patientService.addNewPatient(patientDTO);

    }

    @GetMapping("/list")
    public ResponseEntity<BaseApiResponse> getPatientList ()
    {
        try {
            BaseApiResponse response = patientService.getPatientList();
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


}
