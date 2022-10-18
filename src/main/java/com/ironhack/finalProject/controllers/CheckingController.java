package com.ironhack.finalProject.controllers;

import com.ironhack.finalProject.DTO.BalanceDTO;
import com.ironhack.finalProject.models.accounts.Checking;
import com.ironhack.finalProject.services.accounts.CheckingServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CheckingController {

    @Autowired
    private CheckingServiceImp checkingServiceImp;

    @GetMapping("/checkings")
    public List<Checking> getAllCheckings(){
        return checkingServiceImp.listCheckings();
    }

    @GetMapping("/myaccount/checking/{id}")
    public Checking getMyCheckingAccount(Authentication authentication, @PathVariable int id){
        return checkingServiceImp.showMyAccount(authentication, id);
    }
    @PutMapping("/change/checking/{id}")
    public Checking changeBalanceChecking(@PathVariable int id, @RequestBody BalanceDTO dto){
        return checkingServiceImp.changeBalance(id, dto);
    }
}
