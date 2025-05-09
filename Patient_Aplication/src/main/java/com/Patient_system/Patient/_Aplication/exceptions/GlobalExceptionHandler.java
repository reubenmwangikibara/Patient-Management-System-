package com.Patient_system.Patient._Aplication.exceptions;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice

public class GlobalExceptionHandler {

  @ExceptionHandler(PatientExistException.class)
  public ResponseEntity<BaseApiResponse> handlePatientExistException(PatientExistException ex) {
    BaseApiResponse response = new BaseApiResponse(null, 409, ex.getMessage(), null);
    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }
}
