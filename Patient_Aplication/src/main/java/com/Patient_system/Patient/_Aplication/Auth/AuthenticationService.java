package com.Patient_system.Patient._Aplication.Auth;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.request.UserDTO;
import com.Patient_system.Patient._Aplication.entity.Role;
import com.Patient_system.Patient._Aplication.entity.UserEntity;
import com.Patient_system.Patient._Aplication.exceptions.UserExistException;
import com.Patient_system.Patient._Aplication.repository.UserRepository;
import com.Patient_system.Patient._Aplication.utils.db.UserDBUtilService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDBUtilService userDBUtilService;
    //@Override
    public AuthenticationResponse register(RegisterRequest request) {

        var userEntity = userDBUtilService.checkIfUserExist(request.getUserName(), request.getPhoneNumber());
        log.info("userEntity: {}", userEntity.isPresent());
        if (userEntity.isPresent()) {
             log.info("user already exist");
            throw new UserExistException("username or Phone Number taken");
        }
        var checkemail = userDBUtilService.checkUserEmail(request.getEmail());
        log.info("userEntity: {}", userEntity.isPresent());
        if (userEntity.isPresent()) {
            log.info("user already exist");
            throw new UserExistException("Email already exist");
        }
        var user= UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .userName(request.getUserName())
                .status(request.getStatus())
                .role(Role.USER)
                .build();
        log.info("We are about to create a new user {}",new Gson().toJson(user));

        userRepository.save(user);
        log.info("User Created Successfully");
        //logic for generated token

        var jwtToken = jwtService.generateToken(new HashMap<>(),user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
        // Save the new user to the database

    }
//login request
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User with email do not exist"+ request.getEmail()));
        var jwtToken = jwtService.generateToken(new HashMap<>(),user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .email(request.getEmail())
                .success(true)
                .status(200)
                .message("User logged in successfully")
                .build();
    }
}
