package com.ironhack.finalProject.DTO;

import com.ironhack.finalProject.config.security.Credentials;
import com.ironhack.finalProject.models.Address;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RegistrationUserDTO {
    private Integer userType;
    private String fullName;
    private String username;
    private String secretPass;
    private ArrayList<String> roles;
    private LocalDate dateOfBirth;
    private Address primaryAddress;
    private Address optionalAddress;
    private String hashedKey;

    public RegistrationUserDTO() {
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullname) {
        this.fullName = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecretPass() {
        return secretPass;
    }

    public void setSecretPass(String secretPass) {
        this.secretPass = secretPass;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
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

    public Address getOptionalAddress() {
        return optionalAddress;
    }

    public void setOptionalAddress(Address optionalAddress) {
        this.optionalAddress = optionalAddress;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
