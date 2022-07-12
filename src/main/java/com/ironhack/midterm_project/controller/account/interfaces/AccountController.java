package com.ironhack.midterm_project.controller.account.interfaces;

import com.ironhack.midterm_project.controller.user.dto.ThirdPartyDTO;
import com.ironhack.midterm_project.controller.account.dto.Transference;
import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

public interface AccountController {

    void transferBalance(@AuthenticationPrincipal CustomUserDetails userDetails, Transference transference);
    Set<Account> findAllMyAccounts(@AuthenticationPrincipal CustomUserDetails userDetails);
    Account thirdPartyReceive(@PathVariable String hashedKey, ThirdPartyDTO thirdPartyTransactionDTO);
    Account thirdPartySend(@PathVariable String hashedKey, ThirdPartyDTO thirdPartyTransactionDTO);
}
