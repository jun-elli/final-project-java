package com.ironhack.finalProject.models.users;

import com.ironhack.finalProject.config.security.Credentials;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "third_parties")
@DiscriminatorValue("3")
public class ThirdParty extends User{
    private String hashedKey;

    public ThirdParty() {
    }

    public ThirdParty(String fullName, Credentials credentials, String hashedKey) {
        super(fullName, credentials);
        this.hashedKey = hashedKey;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
