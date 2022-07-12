package com.ironhack.midterm_project.controller.account.interfaces;

import com.ironhack.midterm_project.controller.account.dto.CheckingDTO;
import com.ironhack.midterm_project.controller.account.dto.CheckingSecretKeyDTO;
import com.ironhack.midterm_project.controller.account.dto.MoneyDTO;
import com.ironhack.midterm_project.model.account.Checking;
import com.ironhack.midterm_project.model.user.User;
import com.ironhack.midterm_project.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CheckingController {

    List<Checking> findAll(@AuthenticationPrincipal CustomUserDetails userDetails);
    Checking findById(@AuthenticationPrincipal CustomUserDetails userDetails,Long id);
    Checking store(@AuthenticationPrincipal CustomUserDetails userDetails, CheckingDTO checkingDto);
    void update(@AuthenticationPrincipal CustomUserDetails userDetails,Long id, CheckingDTO checkingDto);
    void updateBalance(@AuthenticationPrincipal CustomUserDetails userDetails, Long id, MoneyDTO balance);
    void updateSecretKey (@AuthenticationPrincipal CustomUserDetails userDetails, Long id, CheckingSecretKeyDTO checkingSecretKeyDTO);
    void delete(@AuthenticationPrincipal CustomUserDetails userDetails, Long id);
    Checking findByUser(@AuthenticationPrincipal CustomUserDetails userDetails, User user);

}
