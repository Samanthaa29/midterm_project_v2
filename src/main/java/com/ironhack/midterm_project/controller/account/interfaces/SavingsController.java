package com.ironhack.midterm_project.controller.account.interfaces;

import com.ironhack.midterm_project.controller.account.dto.MoneyDTO;
import com.ironhack.midterm_project.controller.account.dto.SavingsDTO;
import com.ironhack.midterm_project.model.account.Savings;
import com.ironhack.midterm_project.model.user.User;
import com.ironhack.midterm_project.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface SavingsController {
    List<Savings> findAll(@AuthenticationPrincipal CustomUserDetails userDetails);
    Savings findById(@AuthenticationPrincipal CustomUserDetails userDetails, Long id);
    Savings findByUser(@AuthenticationPrincipal CustomUserDetails userDetails, User user);
    Savings store(@AuthenticationPrincipal CustomUserDetails userDetails, SavingsDTO savingsDto);
    void update(@AuthenticationPrincipal CustomUserDetails userDetails, Long id, SavingsDTO savingsDto);
    void updateBalance(@AuthenticationPrincipal CustomUserDetails userDetails, Long id, MoneyDTO balance);
    void delete(@AuthenticationPrincipal CustomUserDetails userDetails, Long id);
}
