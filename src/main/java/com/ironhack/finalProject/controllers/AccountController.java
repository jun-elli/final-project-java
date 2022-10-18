package com.ironhack.finalProject.controllers;

import com.ironhack.finalProject.DTO.AccountDTO;
import com.ironhack.finalProject.models.accounts.Account;
import com.ironhack.finalProject.services.accounts.AccountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    private AccountServiceImp accountServiceImp;

    @PostMapping("/newaccount")
    public Account createNewAccount(@RequestBody AccountDTO dto){
        return accountServiceImp.createNewAccount(dto);
    }
}
