package com.Patient_system.Patient._Aplication.controller;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.request.UserDTO;
import com.Patient_system.Patient._Aplication.dto.request.passwordUpdateDTO;
import com.Patient_system.Patient._Aplication.entity.UserEntity;
import com.Patient_system.Patient._Aplication.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path= "api/v1/auth")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;
 @PostMapping("/register-user")
 public UserEntity addNewUser (@Valid @RequestBody UserDTO userDTO) throws Exception {
     return userService.addNewUser(userDTO);
 }
 @PutMapping("/update_Password")
    public ResponseEntity <BaseApiResponse> updatePassword(
         @RequestParam(required = false) String username,
         @RequestParam(required = false) String phoneNumber,
         @RequestParam(required = false) String email,
         @RequestBody passwordUpdateDTO updateDTO
 ){
     try {
         BaseApiResponse response = userService.updatePassword(username, phoneNumber, email, updateDTO);
         return ResponseEntity.ok(response);
     } catch (Exception e) {
         BaseApiResponse errorResponse = new BaseApiResponse(false, 400, e.getMessage(), null);
         return ResponseEntity.badRequest().body(errorResponse);

     }

 }

}
