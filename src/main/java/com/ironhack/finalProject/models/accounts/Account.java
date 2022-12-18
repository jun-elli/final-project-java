package com.ironhack.finalProject.models.accounts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.finalProject.models.Money;
import com.ironhack.finalProject.models.users.AccountHolder;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@MappedSuperclass
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Transient
    @JsonIgnore
    private Money money = new Money(new BigDecimal("0"));
    @NotNull
    private BigDecimal balance = new BigDecimal("0");
    @NotNull
    private String currency = "USD";
    @ManyToOne
    @JoinColumn(name = "primary_owner_id", referencedColumnName = "id", nullable = false)
    private AccountHolder primaryOwner;
    @Nullable
    @ManyToOne
    @JoinColumn(name = "secondary_owner_id", referencedColumnName = "id")
    private AccountHolder secondaryOwner;
    private final BigDecimal PENALTY_FEE = BigDecimal.valueOf(40);

    public Account() {
    }

    public Account(Money money, AccountHolder primaryOwner, @Nullable AccountHolder secondaryOwner) {
        this.money = money;
        this.balance = money.getAmount();
        this.currency = money.getCurrency().toString();
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        if (Objects.equals(getCurrency(), money.getCurrency().toString())){
            this.money = money;
            setBalance(money.getAmount());
        }else{
            throw new IllegalArgumentException("Currencies are different.");
        }
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

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    @Nullable
    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(@Nullable AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public BigDecimal getPENALTY_FEE() {
        return PENALTY_FEE;
    }
    public String printActualBalance(){
        return this.money.toString();
    }

    //Basic add and substract money methods,
    // they will be overridden to account for limits on subclasses
    public BigDecimal addMoney(Money addingMoney){
        Money newMoney = new Money(getBalance().add(addingMoney.getAmount()));
        setMoney(newMoney);
        return getBalance();
    }
    public BigDecimal subtractMoney(Money substractingMoney){
        this.money.decreaseAmount(substractingMoney);
        return getBalance();
    }
}
