package com.Patient_system.Patient._Aplication.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
@NoArgsConstructor
@Data
public class passwordUpdateDTO {
    private String currentPassword;
    private String newPassword;

}
