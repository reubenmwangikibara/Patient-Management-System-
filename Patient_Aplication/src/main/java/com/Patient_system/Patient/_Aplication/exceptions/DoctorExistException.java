package com.Patient_system.Patient._Aplication.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DoctorExistException extends Exception {
    private final String message;
    @Override
    public String getMessage() {
        return message; // Return the custom message
    }


}
