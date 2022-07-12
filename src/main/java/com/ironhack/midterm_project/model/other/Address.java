package com.ironhack.midterm_project.model.other;

import javax.persistence.*;

@Embeddable
public class Address {

    public String street;
    public int number;
    public String city;
    public int postalCode;

    public Address() {
    }

    public Address(String street, int number, String city, int postalCode) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postal_code) {
        this.postalCode = postal_code;
    }
}
