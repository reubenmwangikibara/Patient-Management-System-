package com.Patient_system.Patient._Aplication.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
   // private String username;
    private String email;
    private Boolean success;
    private int status;
    private String message;

}
