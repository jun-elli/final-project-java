package com.ironhack.finalProject.models.users;

import com.ironhack.finalProject.models.Address;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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

}
