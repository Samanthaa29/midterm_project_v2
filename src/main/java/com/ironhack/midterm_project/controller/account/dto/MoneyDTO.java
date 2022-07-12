package com.ironhack.midterm_project.controller.account.dto;

import java.math.BigDecimal;

public class MoneyDTO {
    private BigDecimal balance;

    public MoneyDTO(BigDecimal balance) {
        this.balance = balance;
    }

    public MoneyDTO() {
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
