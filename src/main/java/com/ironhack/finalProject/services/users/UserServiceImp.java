package com.ironhack.finalProject.services.users;

import com.ironhack.finalProject.models.users.User;
import com.ironhack.finalProject.repositories.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> listAllUsers() {
        return userRepository.findAll();
    }
}
