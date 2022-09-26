package com.ironhack.finalProject.models.users;

import com.ironhack.finalProject.models.Address;

import java.time.LocalDate;

public class AccountHolder extends User{
    // Date of birth
    //A primaryAddress (which should be a separate address class)
    //An optional mailingAddress

    private LocalDate dateOfBirth;
    private Address primaryAddress;
    private Address optionalAddress;

}
