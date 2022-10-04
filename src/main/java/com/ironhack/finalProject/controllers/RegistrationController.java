package com.ironhack.finalProject.controllers;

import com.ironhack.finalProject.DTO.RegistrationUserDTO;
import com.ironhack.finalProject.config.security.RegistrationServiceImp;
import com.ironhack.finalProject.models.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    @Autowired
    private RegistrationServiceImp registrationServiceImp;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public User registerNewUser(@RequestBody RegistrationUserDTO userDTO){
        return registrationServiceImp.addNewUser(userDTO);
    }
}
