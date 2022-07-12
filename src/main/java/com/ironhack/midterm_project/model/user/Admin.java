package com.ironhack.midterm_project.model.user;

import com.ironhack.midterm_project.model.other.Role;

import javax.persistence.*;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User{

    public Admin() {
    }

    public Admin(String name, String password) {
        super(name, password);
        setRoles(Set.of(new Role("ADMIN")));
    }
}
