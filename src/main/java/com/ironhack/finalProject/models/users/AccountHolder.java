package com.ironhack.finalProject.models.users;

import com.ironhack.finalProject.config.security.Credentials;
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
@DiscriminatorValue("2")
public class AccountHolder extends User{

    private LocalDate dateOfBirth;

    @Nullable
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "primary_address_id", referencedColumnName = "id")
    private Address primaryAddress;
    @Nullable
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "optional_address_id", referencedColumnName = "id")
    private Address optionalAddress;

    public AccountHolder() {
    }

    public AccountHolder(String fullName, Credentials credentials, LocalDate dateOfBirth, Address primaryAddress, @Nullable Address optionalAddress) {
        super(fullName, credentials);
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
