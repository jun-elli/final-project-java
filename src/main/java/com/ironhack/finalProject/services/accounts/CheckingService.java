package com.ironhack.finalProject.services.accounts;

import com.ironhack.finalProject.DTO.BalanceDTO;
import com.ironhack.finalProject.models.accounts.Checking;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CheckingService {
    List<Checking> listCheckings();

    Checking showMyAccount(Authentication authentication, int id);

    Checking changeBalance(int id, BalanceDTO dto);
}
