package com.ironhack.finalProject.models.accounts;

import com.ironhack.finalProject.models.Money;
import com.ironhack.finalProject.models.enums.AccountStatus;
import com.ironhack.finalProject.models.users.AccountHolder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "saving_accounts")
public class Savings extends Account {


    /*Savings accounts have a default interest rate of 0.0025
    Savings accounts may be instantiated with an interest rate other
    than the default, with a maximum interest rate of 0.5

    Savings accounts should have a default minimumBalance of 1000
    Savings accounts may be instantiated with a minimum balance of
    less than 1000 but no lower than 100*/

    @NotNull
    private String secretKey;
    @Transient
    private BigDecimal minimumBalance = BigDecimal.valueOf(1000);
    @NotNull
    private Date creationDate;
    @NotNull
    private BigDecimal interestRate = BigDecimal.valueOf(0.0025);
    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.DISABLED;

    public Savings() {
    }

    public Savings(Money money, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, BigDecimal minimumBalance, Date creationDate, BigDecimal interestRate, AccountStatus status) {
        super(money, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        setMinimumBalance(minimumBalance);
        this.creationDate = creationDate;
        setInterestRate(interestRate);
        this.status = status;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        BigDecimal max = new BigDecimal("1000");
        BigDecimal min = new BigDecimal("100");
        if (minimumBalance.compareTo(max) > 0) {
            this.minimumBalance = max;
        } else if (minimumBalance.compareTo(min) < 0) {
            this.minimumBalance = min;
        } else {
            this.minimumBalance = minimumBalance;
        }
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        BigDecimal max = new BigDecimal("0.5");
        BigDecimal min = new BigDecimal("0");
        if (interestRate.compareTo(max) > 0) {
            this.interestRate = max;
        } else if (interestRate.compareTo(min) < 0) {
            this.interestRate = min;
        } else {
            this.interestRate = interestRate;
        }
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }
}
