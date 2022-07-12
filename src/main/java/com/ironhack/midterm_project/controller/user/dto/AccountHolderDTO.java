package com.ironhack.midterm_project.controller.user.dto;

import com.ironhack.midterm_project.model.other.Address;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;

public class AccountHolderDTO {

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

    public AccountHolderDTO(Address primaryAddress, Address mailingAddress) {
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }

    public AccountHolderDTO() {
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
