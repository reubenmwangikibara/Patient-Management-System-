package com.Patient_system.Patient._Aplication.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserDTO implements Serializable {
    private long tid;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String phoneNumber;
    private String role;
    private int status ;
}
