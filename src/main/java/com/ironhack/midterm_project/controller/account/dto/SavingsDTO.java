package com.ironhack.midterm_project.controller.account.dto;

import com.ironhack.midterm_project.enums.Status;

import javax.persistence.Embedded;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.sql.Date;

public class SavingsDTO {
    @DecimalMin(value = "100.00", message = "balance can not be lower than 100")
    private BigDecimal money;
    private String secretKey;
    private Long primaryOwnerId;
    private Long secondaryOwnerId = 0L;
    private Date creationDate;

    @DecimalMax(value = "0.5")
    @DecimalMin(value = "0.0025")
    private BigDecimal interestRate;
    @Embedded
    private BigDecimal minimumBalance = BigDecimal.valueOf(1000);
    private Status status;

    public SavingsDTO(BigDecimal money, String secretKey, Long primaryOwnerId, Long secondaryOwnerId, Date creationDate, BigDecimal interestRate, BigDecimal minimumBalance, Status status) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.creationDate = creationDate;
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
        this.status = status;
    }

    public SavingsDTO() {
    }

    public SavingsDTO(BigDecimal money, String secretKey, Long primaryOwnerId, Date creationDate, BigDecimal interestRate, BigDecimal minimumBalance, Status status) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryOwnerId = primaryOwnerId;
        this.creationDate = creationDate;
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
        this.status = status;
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

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
