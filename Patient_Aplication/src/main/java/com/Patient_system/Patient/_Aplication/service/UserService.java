package com.Patient_system.Patient._Aplication.service;

import com.Patient_system.Patient._Aplication.dto.BaseApiResponse;
import com.Patient_system.Patient._Aplication.dto.request.UserDTO;
import com.Patient_system.Patient._Aplication.dto.request.passwordUpdateDTO;
import com.Patient_system.Patient._Aplication.entity.UserEntity;
import com.Patient_system.Patient._Aplication.exceptions.UserExistException;


public interface UserService {

    UserEntity addNewUser (UserDTO userDTO) throws UserExistException;
    //BaseApiResponse updatePassword (String email, passwordUpdateDTO updateDTO) throws Exception;
    BaseApiResponse updatePassword(String username, String phoneNumber, String email,passwordUpdateDTO updateDTO) throws Exception;

}
