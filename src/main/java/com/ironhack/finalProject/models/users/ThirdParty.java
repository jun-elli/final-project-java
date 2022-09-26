package com.ironhack.finalProject.models.users;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "third_parties")
public class ThirdParty extends User{
    // hashed key like a PIN
    @NotNull
    private String hashedKey;

    public ThirdParty() {
    }

    public ThirdParty(String fullName, String username, String password, String hashedKey) {
        super(fullName, username, password);
        this.hashedKey = hashedKey;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
