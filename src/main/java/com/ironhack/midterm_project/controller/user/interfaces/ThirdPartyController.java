package com.ironhack.midterm_project.controller.user.interfaces;

import com.ironhack.midterm_project.model.user.ThirdParty;
import com.ironhack.midterm_project.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface ThirdPartyController {

    List<ThirdParty> findAll(@AuthenticationPrincipal CustomUserDetails userDetails);
    ThirdParty findById(@AuthenticationPrincipal CustomUserDetails userDetails, Long id);
    ThirdParty store(@AuthenticationPrincipal CustomUserDetails userDetails, ThirdParty thirdParty);
    void update(@AuthenticationPrincipal CustomUserDetails userDetails, Long id, ThirdParty thirdParty);
    void delete(@AuthenticationPrincipal CustomUserDetails userDetails, Long id);
}
