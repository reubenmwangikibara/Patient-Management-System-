package com.Patient_system.Patient._Aplication.exceptions;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.response.FieldErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j

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
  @ExceptionHandler(DoctorExistException.class)
  public ResponseEntity<BaseApiResponse> handleDoctorExistException(DoctorExistException ex) {
    BaseApiResponse response = new BaseApiResponse(null, 409, ex.getMessage(), null);
    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
    String errorMsg = ex.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
            .collect(Collectors.joining(", "));
    return ResponseEntity.badRequest().body(new BaseApiResponse(false, 400, errorMsg, null));
  }
  //user exist exeption
  @ExceptionHandler(UserExistException.class)
  public ResponseEntity<?> handleUserExist(UserExistException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(
            Map.of(
                    "status", HttpStatus.CONFLICT.value(),
                    "message", "User already exists",
                    "error", ex.getMessage()
            )
    );
  }
//  @ExceptionHandler(UserExistException.class)
//  public BaseApiResponse handleJsonMappingException(UserExistException e){
//    String error = e.getMessage();
//    log.info(error);
//    return new BaseApiResponse(null,400,error,null);
//  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<BaseApiResponse> handleException(Exception e) {
    BaseApiResponse errorResponse = BaseApiResponse.builder()
            .status(500)
            .message("Something went wrong.")
            .errors(List.of(new FieldErrorDTO("error", e.getMessage())))
            .build();
    return ResponseEntity.status(500).body(errorResponse);
  }

}
