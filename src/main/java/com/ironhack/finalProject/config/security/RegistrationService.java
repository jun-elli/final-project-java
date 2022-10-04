package com.ironhack.finalProject.config.security;

import com.ironhack.finalProject.DTO.RegistrationUserDTO;
import com.ironhack.finalProject.models.users.User;

import java.util.Optional;

public interface RegistrationService {
    User addNewUser(RegistrationUserDTO userDTO);
}
