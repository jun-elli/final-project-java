package com.ironhack.finalProject.controllers;

import com.ironhack.finalProject.models.accounts.Checking;
import com.ironhack.finalProject.services.accounts.CheckingServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CheckingController {

    @Autowired
    private CheckingServiceImp checkingServiceImp;

    @GetMapping("/checkings")
    public List<Checking> getAllCheckings(){
        return checkingServiceImp.listCheckings();
    }

    @GetMapping("/myaccount/{id}")
    public Checking getMyCheckingAccount(Authentication authentication, @PathVariable int id){
        return checkingServiceImp.showMyAccount(authentication, id);
    }
}
