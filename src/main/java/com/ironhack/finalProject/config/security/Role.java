package com.ironhack.finalProject.config.security;

import org.hibernate.annotations.Tables;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @ManyToOne
    private Credentials credentials;

    public Role() {
    }

    public Role(String name, Credentials credentials) {
        this.name = name;
        this.credentials = credentials;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
}
