package com.Patient_system.Patient._Aplication.service.impl;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.request.UserDTO;
import com.Patient_system.Patient._Aplication.dto.request.passwordUpdateDTO;
import com.Patient_system.Patient._Aplication.entity.Role;
import com.Patient_system.Patient._Aplication.entity.UserEntity;
import com.Patient_system.Patient._Aplication.exceptions.PasswordUpdateException;
import com.Patient_system.Patient._Aplication.exceptions.UserExistException;
import com.Patient_system.Patient._Aplication.repository.UserRepository;
import com.Patient_system.Patient._Aplication.service.UserService;
import com.Patient_system.Patient._Aplication.utils.db.UserDBUtilService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserDBUtilService userDBUtilService;
    private final UserRepository userRepository;
   @Override
   public UserEntity addNewUser (UserDTO userDTO) throws UserExistException {
    var userEntity = userDBUtilService.checkIfUserExist(userDTO.getUserName(), userDTO.getPhoneNumber());
    log.info("userEntity: {}", userEntity.isPresent());
    if (userEntity.isPresent()) {
       // log.info("user already exist");
        throw new UserExistException("username or Phone Number taken");
    }
    var checkEmail = userDBUtilService.checkUserEmail(userDTO.getEmail());
       log.info("userEntity: {}", checkEmail.isPresent());
       if (checkEmail.isPresent()) {
           // log.info("user already exist");
           throw new UserExistException("Email is taken");
       }
       var user = UserEntity.builder().
            status(1)
            .userName(userDTO.getUserName())
            .firstName(userDTO.getFirstName())
            .phoneNumber(userDTO.getPhoneNumber())
            .lastName(userDTO.getLastName())
             .email(userDTO.getEmail())
               .role(Role.USER)
             .password( passwordEncoder.encode(userDTO.getPassword()))
            .build();
    log.info("We are about to create a new user {}",new Gson().toJson(user));
       // Save the new user to the database
       UserEntity savedUser = userDBUtilService.saveUserDetails(user);
    log.info("User Created Successfully");
    return savedUser;
   }
   // updating the password
    public BaseApiResponse updatePassword(String username, String phoneNumber, String email,passwordUpdateDTO updateDTO) throws Exception{
       var optionalUser = userDBUtilService.selectUser(username, phoneNumber, email);

        if (optionalUser.isEmpty()) {
            throw new PasswordUpdateException("User not found with given username, phone number, or email.");
        }
        var userEntity = optionalUser.get();

        if (!passwordEncoder.matches(updateDTO.getCurrentPassword(),userEntity.getPassword())){
            throw new Exception("Current password is incorrect.");
        }
        userEntity.setPassword(passwordEncoder.encode(updateDTO.getNewPassword()));
        UserEntity updatedUser = userRepository.save(userEntity);
        return new BaseApiResponse(null, 200, "Password updated successfully", null);
    }
}
