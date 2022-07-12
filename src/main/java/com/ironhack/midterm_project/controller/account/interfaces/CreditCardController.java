package com.ironhack.midterm_project.controller.account.interfaces;

import com.ironhack.midterm_project.controller.account.dto.CreditCardDTO;
import com.ironhack.midterm_project.controller.account.dto.MoneyDTO;
import com.ironhack.midterm_project.model.account.CreditCard;
import com.ironhack.midterm_project.model.user.User;
import com.ironhack.midterm_project.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CreditCardController {

    List<CreditCard> findAll(@AuthenticationPrincipal CustomUserDetails userDetails);
    CreditCard findById(@AuthenticationPrincipal CustomUserDetails userDetails,Long id);
    CreditCard findByUser(@AuthenticationPrincipal CustomUserDetails userDetails, User user);
    CreditCard store(@AuthenticationPrincipal CustomUserDetails userDetails,CreditCardDTO creditCardDto);
    void update(@AuthenticationPrincipal CustomUserDetails userDetails,Long id, CreditCardDTO Dto);
    void updateBalance(@AuthenticationPrincipal CustomUserDetails userDetails,Long id, MoneyDTO balance);
    void delete(@AuthenticationPrincipal CustomUserDetails userDetails,Long id);
}
