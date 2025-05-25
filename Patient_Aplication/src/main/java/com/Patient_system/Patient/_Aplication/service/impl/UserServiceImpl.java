package com.Patient_system.Patient._Aplication.service.impl;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.request.UserDTO;
import com.Patient_system.Patient._Aplication.entity.UserEntity;
import com.Patient_system.Patient._Aplication.exceptions.UserExistException;
import com.Patient_system.Patient._Aplication.service.UserService;
import com.Patient_system.Patient._Aplication.utils.db.UserDBUtilService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    @Autowired
  //  private PasswordEncoder passwordEncoder;
    private final UserDBUtilService userDBUtilService;
   @Override
   public UserEntity addNewUser (UserDTO userDTO) throws UserExistException {
    var userEntity = userDBUtilService.checkIfUserExist(userDTO.getUserName(), userDTO.getPhoneNumber());
    log.info("userEntity: {}", userEntity.isPresent());
    if (userEntity.isPresent()) {
       // log.info("user already exist");
        throw new UserExistException("username or Phone Number taken");
    }
       //String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());

       var user = UserEntity.builder().
            status(1)
            .userName(userDTO.getUserName())
            .firstName(userDTO.getFirstName())
            .phoneNumber(userDTO.getPhoneNumber())
            .lastName(userDTO.getLastName())
            .role(userDTO.getRole())
            //.password(encryptedPassword);
            .build();
    log.info("We are about to create a new user {}",new Gson().toJson(user));
       // Save the new user to the database
       UserEntity savedUser = userDBUtilService.saveUserDetails(user);
    log.info("User Created Successfully");
    return savedUser;


}
}
