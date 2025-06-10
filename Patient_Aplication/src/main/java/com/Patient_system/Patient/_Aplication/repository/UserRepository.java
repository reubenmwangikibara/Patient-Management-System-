package com.Patient_system.Patient._Aplication.repository;

import com.Patient_system.Patient._Aplication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface UserRepository extends JpaRepository <UserEntity, Long>{
    Optional<UserEntity> findUserEntitiesByUserNameOrPhoneNumber(String username, String phoneNumber);
    Optional<UserEntity> findUserEntitiesByUserName(String userName);
    @Query("SELECT u FROM UserEntity u WHERE u.email = :email") // ✅ Correct
    Optional<UserEntity> findByEmail (String email);

}
