package com.ironhack.finalProject.controllers;

import com.ironhack.finalProject.models.users.User;
import com.ironhack.finalProject.services.users.UserService;
import com.ironhack.finalProject.services.users.UserServiceImp;
import com.ironhack.finalProject.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserServiceImp userServiceImp;
    private PasswordUtil passwordUtil;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userServiceImp.listAllUsers();
    }

   /* @GetMapping("/getsuper")
    public String createSuper(){
        return null;
    }*/

}
