package com.Patient_system.Patient._Aplication.exceptions;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice

public class GlobalExceptionHandler {

  @ExceptionHandler(PatientExistException.class)
  public ResponseEntity<BaseApiResponse> handlePatientExistException(PatientExistException ex) {
    BaseApiResponse response = new BaseApiResponse(null, 409, ex.getMessage(), null);
    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }
  @ExceptionHandler(PatientNotFoundException.class)
  public ResponseEntity<?> handlePatientNotFoundException(PatientNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new BaseApiResponse(false, 404, ex.getMessage(), null)
    );
  }
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
    String errorMsg = ex.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
            .collect(Collectors.joining(", "));
    return ResponseEntity.badRequest().body(new BaseApiResponse(false, 400, errorMsg, null));
  }

}
