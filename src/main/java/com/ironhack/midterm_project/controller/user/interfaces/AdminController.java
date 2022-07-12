package com.ironhack.midterm_project.controller.user.interfaces;

import com.ironhack.midterm_project.model.user.Admin;
import com.ironhack.midterm_project.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface AdminController {

    List<Admin> findAll(@AuthenticationPrincipal CustomUserDetails userDetails);
    Admin findById(@AuthenticationPrincipal CustomUserDetails userDetails, Long id);
    Admin store(@AuthenticationPrincipal CustomUserDetails userDetails, Admin admin);
    void update(@AuthenticationPrincipal CustomUserDetails userDetails, Long id, Admin admin);
    void delete(@AuthenticationPrincipal CustomUserDetails userDetails, Long id);
}
