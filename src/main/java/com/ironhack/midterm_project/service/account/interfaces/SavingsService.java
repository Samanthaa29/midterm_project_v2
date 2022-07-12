package com.ironhack.midterm_project.service.account.interfaces;

import com.ironhack.midterm_project.controller.account.dto.SavingsDTO;
import com.ironhack.midterm_project.model.account.Savings;

import java.math.BigDecimal;

public interface SavingsService {
    Savings store(SavingsDTO savingDto);
    void update(Long id, SavingsDTO savingDto);
    void updateBalance(Long id, BigDecimal balance);
    void delete(Long id);
}
