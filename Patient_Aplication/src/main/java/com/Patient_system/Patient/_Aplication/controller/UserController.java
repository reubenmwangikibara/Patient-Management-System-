package com.Patient_system.Patient._Aplication.controller;

import com.Patient_system.Patient._Aplication.dto.request.UserDTO;
import com.Patient_system.Patient._Aplication.entity.UserEntity;
import com.Patient_system.Patient._Aplication.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path= "api/v1/auth")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;
 @PostMapping("/register-user")
 public UserEntity addNewUser (@Valid @RequestBody UserDTO userDTO) throws Exception {
     return userService.addNewUser(userDTO);
 }
}
