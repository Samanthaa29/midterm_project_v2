package com.ironhack.midterm_project.controller.account.impl;

import com.ironhack.midterm_project.controller.user.dto.ThirdPartyDTO;
import com.ironhack.midterm_project.controller.account.dto.Transference;
import com.ironhack.midterm_project.controller.account.interfaces.AccountController;
import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.model.user.User;
import com.ironhack.midterm_project.repository.account.AccountRepository;
import com.ironhack.midterm_project.security.CustomUserDetails;
import com.ironhack.midterm_project.service.account.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
@RestController
public class AccountControllerImpl implements AccountController {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountService accountService;

    @PatchMapping("/transference")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transferBalance(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody @Valid Transference transference) {
        accountService.transferBalance(transference);
    }

    @GetMapping("/myAccounts")
    @ResponseStatus(HttpStatus.OK)
    public Set<Account> findAllMyAccounts(@AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();
        return user.getAccounts();
    }

    @PatchMapping("/thirdParty/{hashedKey}/receive")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Account thirdPartyReceive(@PathVariable String hashedKey,
                                     @RequestBody @Valid ThirdPartyDTO thirdPartyTransactionDTO){
        return accountService.thirdPartyReceive(thirdPartyTransactionDTO, hashedKey);
    }


    @PatchMapping("/thirdParty/{hashedKey}/send")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Account thirdPartySend(@PathVariable String hashedKey,
                                  @RequestBody @Valid ThirdPartyDTO thirdPartyTransactionDTO) {
        return accountService.thirdPartySend(thirdPartyTransactionDTO,hashedKey);
    }
}
