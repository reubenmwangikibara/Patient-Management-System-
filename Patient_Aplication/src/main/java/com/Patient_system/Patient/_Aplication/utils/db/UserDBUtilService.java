package com.Patient_system.Patient._Aplication.utils.db;

import com.Patient_system.Patient._Aplication.entity.UserEntity;
import com.Patient_system.Patient._Aplication.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@AllArgsConstructor
@Service

public class UserDBUtilService {
    private final UserRepository userRepository;
    public Optional<UserEntity> checkIfUserExist(String username, String phoneNumber) {

        return userRepository.findUserEntitiesByUserNameOrPhoneNumber(username, phoneNumber);
    }
    public UserEntity saveUserDetails(UserEntity user) {

        return userRepository.save(user);
    }
    public  Optional<UserEntity>selectUser(String username, String phoneNumber, String email){
        return userRepository.findByUserNameOrPhoneNumberOrEmail( username, phoneNumber, email);
    }

}
