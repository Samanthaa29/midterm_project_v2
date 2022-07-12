package com.ironhack.midterm_project.controller.account.dto;

import com.ironhack.midterm_project.enums.Status;

import java.math.BigDecimal;
import java.sql.Date;

public class CheckingDTO {

    private BigDecimal money;
    private String secretKey;
    private Long primaryOwnerId;
    private Long secondaryOwnerId = 0L;
    private Date creationDate;
    private Status status;

    public CheckingDTO(BigDecimal money, String secretKey, Long primaryOwnerId, Long secondaryOwnerId, Date creationDate, Status status) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.creationDate = creationDate;
        this.status = status;
    }

    public CheckingDTO(BigDecimal money, String secretKey, Long primaryOwnerId, Date creationDate, Status status) {
        this.money = money;
        this.secretKey = secretKey;
        this.primaryOwnerId = primaryOwnerId;
        this.creationDate = creationDate;
        this.status = status;
    }

    public CheckingDTO() {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
