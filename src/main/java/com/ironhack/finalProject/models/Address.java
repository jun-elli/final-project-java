package com.ironhack.finalProject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.finalProject.models.users.AccountHolder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String street;
    private String additionalAddress;
    @NotNull
    private String city;
    @NotNull
    private String country;
    @NotNull
    private String postalCode;

    @JsonIgnore
    @OneToOne(mappedBy = "primaryAddress")
    private AccountHolder accountHolder;
    @JsonIgnore
    @OneToOne(mappedBy = "optionalAddress")
    private AccountHolder sameAccountHolder;

    public Address() {
    }
    public Address(String street, String additionalAddress, String city, String country, String postalCode) {
        this.street = street;
        this.additionalAddress = additionalAddress;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalAddress() {
        return additionalAddress;
    }

    public void setAdditionalAddress(String additionalAddress) {
        this.additionalAddress = additionalAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }

    public AccountHolder getSameAccountHolder() {
        return sameAccountHolder;
    }

    public void setSameAccountHolder(AccountHolder sameAccountHolder) {
        this.sameAccountHolder = sameAccountHolder;
    }
}
