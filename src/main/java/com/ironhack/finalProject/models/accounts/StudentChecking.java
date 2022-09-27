package com.ironhack.finalProject.models.accounts;

import com.ironhack.finalProject.models.Money;
import com.ironhack.finalProject.models.enums.AccountStatus;
import com.ironhack.finalProject.models.users.AccountHolder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
@Entity
@Table(name = "student_checking_accounts")
public class StudentChecking extends Account{

    @NotNull
    private String secretKey;
    @NotNull
    private Date creationDate;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.DISABLED;

    public StudentChecking() {
    }

    public StudentChecking(Money money, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, Date creationDate, AccountStatus status) {
        super(money, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.creationDate = creationDate;
        this.status = status;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    @Override
    public void setPrimaryOwner(AccountHolder primaryOwner) {
        // calculate age
        int age = Period.between(primaryOwner.getDateOfBirth(), LocalDate.now()).getYears();
        //conditional to 24 or thrwo exception
        if (age >= 24) {
            throw new IllegalArgumentException("The owner is 24 or older, a normal Checking account should be opened instead");
        }
        super.setPrimaryOwner(primaryOwner);
    }
}
