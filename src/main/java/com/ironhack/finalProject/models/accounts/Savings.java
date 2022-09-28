package com.ironhack.finalProject.models.accounts;

import com.ironhack.finalProject.models.Money;
import com.ironhack.finalProject.models.enums.AccountStatus;
import com.ironhack.finalProject.models.users.AccountHolder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

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
    private LocalDate creationDate;
    @NotNull
    private BigDecimal interestRate = BigDecimal.valueOf(0.0025);
    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.DISABLED;
    @Transient
    private LocalDate whenLastInterestWasAdded = LocalDate.now();

    public Savings() {
    }

    public Savings(Money money, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, BigDecimal minimumBalance, LocalDate creationDate, BigDecimal interestRate, AccountStatus status) {
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
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

    public LocalDate getWhenLastInterestWasAdded() {
        return whenLastInterestWasAdded;
    }

    public void setWhenLastInterestWasAdded(LocalDate whenLastInterestWasAdded) {
        this.whenLastInterestWasAdded = whenLastInterestWasAdded;
    }

    /*
            Interest on savings accounts is added to the account annually
            at the rate of specified interestRate per year.
            That means that if I have 1000000 in a savings account with a 0.01 interest rate,
             1% of 1 Million is added to my account after 1 year.
             When a savings account balance is accessed,
             you must determine if it has been 1 year or more since either
             the account was created or since interest was added to the account,
             and add the appropriate interest to the balance if necessary.
                    */
    public int getYearsSinceCreation() {
        return Period.between(getCreationDate(), LocalDate.now()).getYears();
    }

    public int getYearsSinceLastInterest() {
        return Period.between(getWhenLastInterestWasAdded(), LocalDate.now()).getYears();
    }

    public void addYearlyInterest() {
        BigDecimal addedInterest = getBalance().multiply(getInterestRate());
        Money newTotal = new Money(getBalance().add(addedInterest));
        setMoney(newTotal);
        whenLastInterestWasAdded = LocalDate.now();
    }

    public boolean isTimeToAddInterest() {
        int years = getYearsSinceLastInterest();
        return years >= 1;
    }

    public BigDecimal checkBalance() {
        if (isTimeToAddInterest()) {
            addYearlyInterest();
            return getBalance();
        }
        return getBalance();
    }

}
