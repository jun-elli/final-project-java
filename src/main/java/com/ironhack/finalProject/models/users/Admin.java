package com.ironhack.finalProject.models.users;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends User {
    public Admin() {
    }

    public Admin(String fullName, String username, String password) {
        super(fullName, username, password);
    }

    // Admins can create new accounts. When creating a new account they can create Checking, Savings, or CreditCard Accounts.

}
