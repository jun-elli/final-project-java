package com.ironhack.finalProject.models.users;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String fullName;
    @NotNull
    private String username;
    @NotNull
    private String secretPass;

    public User() {
    }

    public User(String fullName, String username, String secretPass) {
        setFullName(fullName);
        setUsername(username);
        setSecretPass(secretPass);
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
}
