package com.ironhack.finalProject.services.accounts;


import com.ironhack.finalProject.models.accounts.Checking;
import com.ironhack.finalProject.repositories.accounts.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CheckingServiceImp implements CheckingService{

    @Autowired
    private CheckingRepository checkingRepository;

    @Override
    public List<Checking> listCheckings() {
        return checkingRepository.findAll();
    }

    @Override
    public Checking showMyAccount(Authentication authentication, int id) {
        Checking myAccount = checkingRepository.findById(id).orElse(null);
        String authUsername = authentication.getName();
        String myAccountUsername = myAccount.getPrimaryOwner().getCredentials().getUsername();
        if (myAccount == null || !Objects.equals(authUsername, myAccountUsername)){
            throw new IllegalArgumentException("Account not found for that username.");
        }
        return myAccount;
    }
}
