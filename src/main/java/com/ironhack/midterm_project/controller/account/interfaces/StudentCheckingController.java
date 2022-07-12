package com.ironhack.midterm_project.controller.account.interfaces;

import com.ironhack.midterm_project.controller.account.dto.StudentCheckingDTO;
import com.ironhack.midterm_project.controller.account.dto.MoneyDTO;
import com.ironhack.midterm_project.model.account.StudentChecking;
import com.ironhack.midterm_project.model.user.User;
import com.ironhack.midterm_project.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface StudentCheckingController {

    List<StudentChecking> findAll(@AuthenticationPrincipal CustomUserDetails userDetails);
    StudentChecking findById(@AuthenticationPrincipal CustomUserDetails userDetails,Long id);
    StudentChecking findByUser(@AuthenticationPrincipal CustomUserDetails userDetails, User user);
    StudentChecking store(@AuthenticationPrincipal CustomUserDetails userDetails, StudentCheckingDTO studentCheckingDto);
    void update(@AuthenticationPrincipal CustomUserDetails userDetails,Long id, StudentCheckingDTO studentCheckingDto);
    void updateBalance(@AuthenticationPrincipal CustomUserDetails userDetails,Long id, MoneyDTO balance);
    void delete(@AuthenticationPrincipal CustomUserDetails userDetails,Long id);
}
