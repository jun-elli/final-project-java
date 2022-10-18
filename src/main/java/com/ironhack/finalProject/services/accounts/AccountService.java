package com.ironhack.finalProject.services.accounts;

import com.ironhack.finalProject.DTO.AccountDTO;
import com.ironhack.finalProject.models.accounts.Account;

public interface AccountService {
    Account createNewAccount(AccountDTO dto);
}
