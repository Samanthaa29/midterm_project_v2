package com.ironhack.midterm_project.service.account.interfaces;

import com.ironhack.midterm_project.controller.account.dto.CreditCardDTO;
import com.ironhack.midterm_project.model.account.CreditCard;

import java.math.BigDecimal;

public interface CreditCardService {
    CreditCard store(CreditCardDTO creditCardDto);
    void update(Long id, CreditCardDTO creditCardDto);
    void updateBalance(Long id, BigDecimal balance);
    void delete(Long id);
}
