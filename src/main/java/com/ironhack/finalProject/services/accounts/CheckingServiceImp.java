package com.ironhack.finalProject.services.accounts;


import com.ironhack.finalProject.models.accounts.Checking;
import com.ironhack.finalProject.repositories.accounts.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckingServiceImp implements CheckingService{

    @Autowired
    private CheckingRepository checkingRepository;

    @Override
    public List<Checking> listCheckings() {
        return checkingRepository.findAll();
    }
}
