package com.Patient_system.Patient._Aplication.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserDTO implements Serializable {
    private long tid;
    @NotBlank(message = "First Name cannot be blank")
    private String firstName;
    @NotBlank(message = "Last Name cannot be blank")
    private String lastName;
    @NotBlank(message = "username Name cannot be blank")
    private String userName;
    private String password;
    @NotBlank(message = "Enter Phone number")
    private String phoneNumber;
    @NotBlank(message = "Enter your role")
    private String role;
    private int status ;
}
