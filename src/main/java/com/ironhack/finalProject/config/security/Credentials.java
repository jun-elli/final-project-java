package com.ironhack.finalProject.config.security;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Credentials {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    private String username;
    @NotNull
    private String secretPass;
    @OneToMany(mappedBy = "credentials", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<Role> roles;

    public Credentials() {
    }

    public Credentials(String username, String secretPass, Set<Role> roles) {
        this.username = username;
        this.secretPass = secretPass;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
