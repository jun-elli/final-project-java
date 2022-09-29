package com.ironhack.finalProject.models.users;

import com.ironhack.finalProject.models.Address;
import com.ironhack.finalProject.models.accounts.Account;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "account_holders")
public class AccountHolder extends User{
    // Date of birth
    //A primaryAddress (which should be a separate address class)
    //An optional mailingAddress
    @NotNull
    private LocalDate dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "primary_address_id", referencedColumnName = "id", nullable = false)
    private Address primaryAddress;
    @Nullable
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "optional_address_id", referencedColumnName = "id")
    private Address optionalAddress;

   /* @OneToMany(mappedBy = "primaryOwner")
    private List<Account> accountSetWherePrimaryOwner;
    @OneToMany(mappedBy = "secondaryOwner")
    private Set<Account> accountSetWhereSecondaryOwner;*/

    public AccountHolder() {
    }
    public AccountHolder(String fullName, String username, String password, LocalDate dateOfBirth, Address primaryAddress, @Nullable Address optionalAddress) {
        super(fullName, username, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.optionalAddress = optionalAddress;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    @Nullable
    public Address getOptionalAddress() {
        return optionalAddress;
    }

    public void setOptionalAddress(@Nullable Address optionalAddress) {
        this.optionalAddress = optionalAddress;
    }
}
