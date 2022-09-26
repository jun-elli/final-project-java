package com.ironhack.finalProject.models.accounts;

import com.ironhack.finalProject.models.Money;
import com.ironhack.finalProject.models.enums.AccountStatus;
import com.ironhack.finalProject.models.users.AccountHolder;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "checking_accounts")
public class Checking extends Account{
    //like IBAN 2000 4564 5000 3837
    @NotNull
    private String secretKey;
    @Transient
    private final BigDecimal MINIMUM_BALANCE = BigDecimal.valueOf(250);
    @Transient
    private final BigDecimal MONTHLY_MAINTENANCE_FEE = BigDecimal.valueOf(12);
    @NotNull
    private Date creationDate;
    @NotNull
    private AccountStatus status = AccountStatus.DISABLED;

    public Checking() {
    }

    public Checking(Money money, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey, Date creationDate, AccountStatus status) {
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

    public BigDecimal getMINIMUM_BALANCE() {
        return MINIMUM_BALANCE;
    }

    public BigDecimal getMONTHLY_MAINTENANCE_FEE() {
        return MONTHLY_MAINTENANCE_FEE;
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
}
