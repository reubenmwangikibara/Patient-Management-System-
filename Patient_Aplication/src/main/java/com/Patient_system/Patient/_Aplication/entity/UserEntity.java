package com.Patient_system.Patient._Aplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@Entity
@Table(name="users")
@Data
@Builder
@NoArgsConstructor

public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    @Column(name = "tid", nullable = false)
    private long tid;
    private String firstName;
    @Column(name = "Last_name")
    private String lastName;
    @Column(name = "username")
    private String userName;
    @Column(name = "Password")
    private String password;
    @Column(name = "Phone_number")
    private String phoneNumber;
    @Column(name = "Role")
    private String role;
    @Column(name = "status")
    private Integer status;

}
