package com.ironhack.midterm_project.service.account.interfaces;

import com.ironhack.midterm_project.controller.account.dto.CheckingDTO;
import com.ironhack.midterm_project.model.account.Checking;

import java.math.BigDecimal;

public interface CheckingService {
    void updateSecretKey(Long id, String secretKey);
    Checking store(CheckingDTO checkingDto);
    Checking update(Long id, CheckingDTO checkingDto);
    void updateBalance(Long id, BigDecimal balance);
    Checking delete(Long id);
}
