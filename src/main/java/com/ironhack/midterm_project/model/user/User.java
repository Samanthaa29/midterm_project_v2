package com.ironhack.midterm_project.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.model.other.Role;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER) //
    private Set<Role> roles;
    @OneToMany(mappedBy = "primaryOwner", /*cascade = CascadeType.ALL, */fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Account> accounts;
    @OneToMany(mappedBy = "secondaryOwner", /*cascade = CascadeType.ALL, */fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Account> secondaryAccounts;


    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Set<Account> getSecondaryAccounts() {
        return secondaryAccounts;
    }

    public void setSecondaryAccounts(Set<Account> secondaryAccounts) {
        this.secondaryAccounts = secondaryAccounts;
    }
}
