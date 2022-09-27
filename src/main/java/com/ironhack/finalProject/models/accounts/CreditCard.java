package com.ironhack.finalProject.models.accounts;

import com.ironhack.finalProject.models.Money;
import com.ironhack.finalProject.models.users.AccountHolder;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "credit_cards")
public class CreditCard extends Account{
    //creditLimit
    // interestRate
    /*CreditCard accounts have a default creditLimit of 100
    CreditCards may be instantiated with a creditLimit higher than 100 but not higher than 100000
    CreditCards have a default interestRate of 0.2
    CreditCards may be instantiated with an interestRate less than 0.2 but not lower than 0.1*/
    @NotNull
    private BigDecimal creditLimit = BigDecimal.valueOf(100);
    @NotNull
    private BigDecimal interestRate = BigDecimal.valueOf(0.2);

    public CreditCard() {
    }

    public CreditCard(Money money, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal creditLimit, BigDecimal interestRate) {
        super(money, primaryOwner, secondaryOwner);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
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
}

