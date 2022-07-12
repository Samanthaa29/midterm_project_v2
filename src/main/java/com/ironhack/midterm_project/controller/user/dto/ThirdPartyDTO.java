package com.ironhack.midterm_project.controller.user.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

public class ThirdPartyDTO {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private BigDecimal money;
    private Long accountId;
    private String secretKey;

    public ThirdPartyDTO(PasswordEncoder passwordEncoder, BigDecimal money, Long accountId, String secretKey) {
        this.passwordEncoder = passwordEncoder;
        this.money = money;
        this.accountId = accountId;
        this.secretKey = secretKey;
    }

    public ThirdPartyDTO() {
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
