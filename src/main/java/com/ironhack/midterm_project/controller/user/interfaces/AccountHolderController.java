package com.ironhack.midterm_project.controller.user.interfaces;

import com.ironhack.midterm_project.model.user.AccountHolder;
import com.ironhack.midterm_project.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface AccountHolderController {

    List<AccountHolder> findAll(@AuthenticationPrincipal CustomUserDetails userDetails);
    AccountHolder findById(@AuthenticationPrincipal CustomUserDetails userDetails,Long id);
    AccountHolder store(@AuthenticationPrincipal CustomUserDetails userDetails, AccountHolder accountHolder);
    void update(@AuthenticationPrincipal CustomUserDetails userDetails, Long id, AccountHolder accountHolder);
    void delete(@AuthenticationPrincipal CustomUserDetails userDetails,Long id);
}
