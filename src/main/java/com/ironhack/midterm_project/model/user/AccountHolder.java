package com.ironhack.midterm_project.model.user;

import com.ironhack.midterm_project.model.other.Address;
import com.ironhack.midterm_project.model.other.Role;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class AccountHolder extends User {

    private Date dateOfBirth;
    @Embedded
    private Address primaryAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street",column = @Column(name = "mailing_address_street")),
            @AttributeOverride(name="number", column = @Column(name = "mailing_address_number")),
            @AttributeOverride(name="city", column = @Column(name = "mailing_address_city")),
            @AttributeOverride(name="postalCode", column = @Column(name = "mailing_address_postal_code"))
    })
    private Address mailingAddress;
    private Set<Role> roles;

    public AccountHolder() {
    }

    public AccountHolder(String name, String password, Date dateOfBirth, Address primaryAddress, Address mailingAddress) {
        super(name, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }

    public AccountHolder(String name, String password, Date dateOfBirth, Address primaryAddress) {
        super(name, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
}
