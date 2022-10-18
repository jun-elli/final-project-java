package com.ironhack.finalProject.DTO;

import com.ironhack.finalProject.models.enums.AccountStatus;
import com.ironhack.finalProject.models.enums.AccountType;

import java.math.BigDecimal;

public class AccountDTO {
    private AccountType accountType;
    private BigDecimal balance;
    private String currency;
    private int primary_owner_id;
    private int secondary_owner_id;
    // checking & savings & student
    private String rawSecretKey;
    private String creationDate;
    private AccountStatus accountStatus;
    // credit
    private BigDecimal creditLimit;
    // credit & savings
    private BigDecimal interestRate;
    // savings
    private BigDecimal minimumBalance;

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getPrimary_owner_id() {
        return primary_owner_id;
    }

    public void setPrimary_owner_id(int primary_owner_id) {
        this.primary_owner_id = primary_owner_id;
    }

    public int getSecondary_owner_id() {
        return secondary_owner_id;
    }

    public void setSecondary_owner_id(int secondary_owner_id) {
        this.secondary_owner_id = secondary_owner_id;
    }

    public String getRawSecretKey() {
        return rawSecretKey;
    }

    public void setRawSecretKey(String rawSecretKey) {
        this.rawSecretKey = rawSecretKey;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }
}
