package com.ironhack.finalProject.DTO;

import com.ironhack.finalProject.models.enums.AccountStatus;
import com.ironhack.finalProject.models.enums.AccountType;

import java.math.BigDecimal;

public class AccountDTO {
    private AccountType accountType;
    private BigDecimal balance;
    private String currency;
    private int primaryOwnerId;
    private int secondaryOwnerId;
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

    public int getPrimaryOwnerId() {
        return primaryOwnerId;
    }

    public void setPrimaryOwnerId(int primaryOwnerId) {
        this.primaryOwnerId = primaryOwnerId;
    }

    public int getSecondaryOwnerId() {
        return secondaryOwnerId;
    }

    public void setSecondaryOwnerId(int secondaryOwnerId) {
        this.secondaryOwnerId = secondaryOwnerId;
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
