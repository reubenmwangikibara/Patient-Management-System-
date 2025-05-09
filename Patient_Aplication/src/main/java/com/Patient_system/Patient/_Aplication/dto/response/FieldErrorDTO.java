package com.Patient_system.Patient._Aplication.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldErrorDTO {
    private String field;
    private String message;


}
