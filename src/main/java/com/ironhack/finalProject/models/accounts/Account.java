package com.ironhack.finalProject.models.accounts;

import com.ironhack.finalProject.models.Money;
import com.ironhack.finalProject.models.users.AccountHolder;

import java.math.BigDecimal;

public abstract class Account {
    private long id;
    private Money balance;
    private AccountHolder primaryOwner;
    private AccountHolder secondaryOwner;
    private final BigDecimal PENALTY_FEE = BigDecimal.valueOf(40);
}
