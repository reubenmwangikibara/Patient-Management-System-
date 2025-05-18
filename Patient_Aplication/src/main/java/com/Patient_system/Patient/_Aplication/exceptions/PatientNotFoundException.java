package com.Patient_system.Patient._Aplication.exceptions;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String message) {

        super(message);
    }
    public PatientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
