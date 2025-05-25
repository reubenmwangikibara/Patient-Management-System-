package com.Patient_system.Patient._Aplication.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class LoginDTO implements Serializable {
    @NotBlank(message = "userName date is required")
    private String userName;
    @NotBlank(message = "password date is required")
    private String password;
}
