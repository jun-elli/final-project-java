package com.ironhack.finalProject.models.accounts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.finalProject.models.Money;
import com.ironhack.finalProject.models.users.AccountHolder;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "credit_cards")
public class CreditCard extends Account{
    //creditLimit
    // interestRate
    /*CreditCard accounts have a default creditLimit of 100
    CreditCards may be instantiated with a creditLimit higher
    than 100 but not higher than 100000

    CreditCards have a default interestRate of 0.2
    CreditCards may be instantiated with an interestRate
    less than 0.2 but not lower than 0.1*/
    @NotNull
    private BigDecimal creditLimit = BigDecimal.valueOf(100);
    @NotNull
    private BigDecimal interestRate = BigDecimal.valueOf(0.2);

    @Transient
    @JsonIgnore
    private LocalDate whenLastMonthlyInterestWasAdded = LocalDate.now();

    public CreditCard() {
    }

    public CreditCard(Money money, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal creditLimit, BigDecimal interestRate) {
        super(money, primaryOwner, secondaryOwner);
        setCreditLimit(creditLimit);
        setInterestRate(interestRate);
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        BigDecimal max = new BigDecimal("100000");
        BigDecimal min = new BigDecimal("100");
        if (creditLimit.compareTo(max) > 0) {
            this.creditLimit = max;
        } else if (creditLimit.compareTo(min) < 0) {
            this.creditLimit = min;
        } else {
            this.creditLimit = creditLimit;
        }
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        BigDecimal max = new BigDecimal("0.2");
        BigDecimal min = new BigDecimal("0.1");
        if (interestRate.compareTo(max) > 0) {
            this.interestRate = max;
        } else if (interestRate.compareTo(min) < 0) {
            this.interestRate = min;
        } else {
            this.interestRate = interestRate;
        }
    }

    public LocalDate getWhenLastMonthlyInterestWasAdded() {
        return whenLastMonthlyInterestWasAdded;
    }

    public void setWhenLastMonthlyInterestWasAdded(LocalDate whenLastMonthlyInterestWasAdded) {
        this.whenLastMonthlyInterestWasAdded = whenLastMonthlyInterestWasAdded;
    }
    /*
    Interest on credit cards is added to the balance monthly.
    If you have a 12% interest rate (0.12) then 1% interest will be added
    to the account monthly. When the balance of a credit card is accessed,
    check to determine if it has been 1 month or more since the account
    was created or since interested was added, and if so,
    add the appropriate interest to the balance.
        */

    public int getMonthsSinceLastInterest() {
        return Period.between(getWhenLastMonthlyInterestWasAdded(), LocalDate.now()).getMonths();
    }

    // To modify balance, should I update Money property and then through that, modify balance?
    public void addMonthlyInterest() {
        BigDecimal monthlyInterest = getInterestRate().divide(BigDecimal.valueOf(12), RoundingMode.HALF_EVEN);
        BigDecimal addedInterest = getBalance().multiply(monthlyInterest);
        Money newTotal = new Money(getBalance().add(addedInterest));
        setMoney(newTotal);
        whenLastMonthlyInterestWasAdded = LocalDate.now();
    }

    public boolean isTimeToAddInterest() {
        int months = getMonthsSinceLastInterest();
        return months >= 1;
    }

    public BigDecimal checkBalance() {
        if (isTimeToAddInterest()) {
            addMonthlyInterest();
            return getBalance();
        }
        return getBalance();
    }

}

