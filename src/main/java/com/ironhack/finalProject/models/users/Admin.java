package com.ironhack.finalProject.models.users;

import com.ironhack.finalProject.config.security.Credentials;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
@DiscriminatorValue("1")
public class Admin extends User {
    public Admin() {
    }

    public Admin(String fullName, Credentials credentials) {
        super(fullName, credentials);
    }

    // Admins can create new accounts. When creating a new account they can create Checking, Savings, or CreditCard Accounts.

}
