package com.ironhack.finalProject.models.accounts;

import com.ironhack.finalProject.models.Money;
import com.ironhack.finalProject.models.enums.AccountStatus;
import com.ironhack.finalProject.models.users.AccountHolder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "checking_accounts")
public class Checking extends Account{
    //like IBAN 2000 4564 5000 3837

/*
    When creating a new Checking account, if the primaryOwner is less than 24,
    a StudentChecking account should be created otherwise
    a regular Checking Account should be created.
    Checking accounts should have a minimumBalance of 250 and a monthlyMaintenanceFee of 12
*/

    @NotNull
    private String secretKey;
    @NotNull
    private final BigDecimal MINIMUM_BALANCE = BigDecimal.valueOf(250);
    @NotNull
    private final BigDecimal MONTHLY_MAINTENANCE_FEE = BigDecimal.valueOf(12);
    @NotNull
    private LocalDate creationDate;
    @Transient
    private LocalDate whenLastMaintenanceFeeWasDeducted;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.DISABLED;

    public Checking() {
    }

    public Checking(Money money, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, LocalDate creationDate, AccountStatus status) {
        super(money, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.creationDate = creationDate;
        this.whenLastMaintenanceFeeWasDeducted = creationDate;
        this.status = status;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public BigDecimal getMINIMUM_BALANCE() {
        return MINIMUM_BALANCE;
    }

    public BigDecimal getMONTHLY_MAINTENANCE_FEE() {
        return MONTHLY_MAINTENANCE_FEE;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public LocalDate getWhenLastMaintenanceFeeWasDeducted() {
        return whenLastMaintenanceFeeWasDeducted;
    }

    public void setWhenLastMaintenanceFeeWasDeducted(LocalDate whenLastMaintenanceFeeWasDeducted) {
        this.whenLastMaintenanceFeeWasDeducted = whenLastMaintenanceFeeWasDeducted;
    }

    @Override
    public void setPrimaryOwner(AccountHolder primaryOwner) {
        // calculate age
        int age = Period.between(primaryOwner.getDateOfBirth(), LocalDate.now()).getYears();
        //conditional to 24 or thrwo exception
        if (age < 24) {
            throw new IllegalArgumentException("The owner is less than 24, a Student account should be opened instead");
        }
        super.setPrimaryOwner(primaryOwner);
    }

    @Override
    public BigDecimal subtractMoney(Money substractingMoney) {
        getMoney().decreaseAmount(substractingMoney);
        if (getBalance().compareTo(getMINIMUM_BALANCE()) < 0){
            getMoney().decreaseAmount(getPENALTY_FEE());
        }
        return getBalance();
    }

    public void substractMonthlyFee() {
        BigDecimal months = new BigDecimal(getMonthsSinceLastMaintenanceFeeDeduction());
        Money totalFee = new Money(getMONTHLY_MAINTENANCE_FEE().multiply(months));
        subtractMoney(totalFee);
        whenLastMaintenanceFeeWasDeducted = LocalDate.now();
    }

    public int getMonthsSinceLastMaintenanceFeeDeduction() {
        return Period.between(getWhenLastMaintenanceFeeWasDeducted(), LocalDate.now()).getMonths();
    }

    public BigDecimal checkBalance() {
        if (getMonthsSinceLastMaintenanceFeeDeduction() >= 1) {
            substractMonthlyFee();
            return getBalance();
        }
        return getBalance();
    }

}
