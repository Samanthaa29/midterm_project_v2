package com.ironhack.midterm_project.controller.account.dto;

import com.ironhack.midterm_project.model.other.Money;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.sql.Date;

public class CreditCardDTO {

    private BigDecimal money;
    private String secretKey;
    private Long primaryOwnerId;
    private Long secondaryOwnerId = 0L;
    private Date creationDate;
    @DecimalMax(value = "0.2")
    @DecimalMin(value = "0.1")
    private BigDecimal interestRate;
    @Max(value = 100000)
    @Min(value = 100)
    private BigDecimal creditLimit;


    public CreditCardDTO(BigDecimal money, String secretKey, Long primaryOwnerId, Long secondaryOwnerId, Date creationDate, BigDecimal interestRate, BigDecimal creditLimit) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.creationDate = creationDate;
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
    }

    public CreditCardDTO() {
    }

    public CreditCardDTO(BigDecimal money, String secretKey, Long primaryOwnerId, Date creationDate, BigDecimal interestRate, BigDecimal creditLimit) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryOwnerId = primaryOwnerId;
        this.creationDate = creationDate;
        this.interestRate = interestRate;
        this.creditLimit = creditLimit;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Long getPrimaryOwnerId() {
        return primaryOwnerId;
    }

    public void setPrimaryOwnerId(Long primaryOwnerId) {
        this.primaryOwnerId = primaryOwnerId;
    }

    public Long getSecondaryOwnerId() {
        return secondaryOwnerId;
    }

    public void setSecondaryOwnerId(Long secondaryOwnerId) {
        this.secondaryOwnerId = secondaryOwnerId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }
}
