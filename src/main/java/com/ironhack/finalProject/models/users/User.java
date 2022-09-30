package com.ironhack.finalProject.models.users;

import com.ironhack.finalProject.config.security.Credentials;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String fullName;
    @OneToOne
    @JoinColumn(name = "credentials_id", referencedColumnName = "id")
    Credentials credentials;

    public User() {
    }

    public User(String fullName, Credentials credentials) {
        this.fullName = fullName;
        this.credentials = credentials;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}
