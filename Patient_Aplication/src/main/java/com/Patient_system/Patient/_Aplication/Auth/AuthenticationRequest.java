package com.Patient_system.Patient._Aplication.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
//for Login
public class AuthenticationRequest {
    private String email;
    private String password;
}
