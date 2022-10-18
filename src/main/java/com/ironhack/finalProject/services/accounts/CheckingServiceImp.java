package com.ironhack.finalProject.services.accounts;


import com.ironhack.finalProject.DTO.BalanceDTO;
import com.ironhack.finalProject.models.Money;
import com.ironhack.finalProject.models.accounts.Checking;
import com.ironhack.finalProject.repositories.accounts.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Currency;
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
        if (myAccount == null){
            throw new IllegalArgumentException("Account with that id not found.");
        }
        // check if admin or super
        boolean isAdminOrSuper = checkIfIsAdminOrSuper(authentication.getAuthorities().toArray());
        if (!isAdminOrSuper){
            String authUsername = authentication.getName();
            String myAccountUsername = myAccount.getPrimaryOwner().getCredentials().getUsername();
            if (!Objects.equals(authUsername, myAccountUsername)){
                throw new IllegalArgumentException("Wrong username for this account.");
            }
        }
        return myAccount;
    }

    @Override
    public Checking changeBalance(int id, BalanceDTO dto) {
        Checking a = checkingRepository.findById(id).orElse(null);
        if (a == null){
            throw new IllegalArgumentException("Account with that id not found.");
        }
        Checking savedAcc;
        switch (dto.getOperation()){
            case ADD -> savedAcc = addToAccount(a, dto);
            case SUBTRACT -> savedAcc = subtractFromAccount(a, dto);
            default -> throw new IllegalArgumentException("Operation not available.");
        }
        return savedAcc;
    }

    private Checking subtractFromAccount(Checking a, BalanceDTO dto) {
        Money m = new Money(dto.getAmount(), Currency.getInstance(dto.getCurrency()));
        a.subtractMoney(m);
        return checkingRepository.save(a);
    }

    private Checking addToAccount(Checking a, BalanceDTO dto) {
        Money m = new Money(dto.getAmount(), Currency.getInstance(dto.getCurrency()));
        a.addMoney(m);
        return checkingRepository.save(a);
    }

    private boolean checkIfIsAdminOrSuper(Object[] array) {
        for (Object role : array) {
            if (Objects.equals(role.toString(), "ROLE_ADMIN") || Objects.equals(role.toString(), "ROLE_SUPER")){
                return true;
            }
        }
        return false;
    }
}
