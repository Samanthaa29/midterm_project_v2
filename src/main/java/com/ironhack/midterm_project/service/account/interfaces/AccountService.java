package com.ironhack.midterm_project.service.account.interfaces;

import com.ironhack.midterm_project.controller.account.dto.Transference;
import com.ironhack.midterm_project.controller.user.dto.ThirdPartyDTO;
import com.ironhack.midterm_project.model.account.Account;

public interface AccountService {

    void transferBalance(Transference transference);
    Account thirdPartyReceive(ThirdPartyDTO thirdPartyTransactionDTO, String hashedKey);
    Account thirdPartySend(ThirdPartyDTO thirdPartyDto, String hashedKey);
}
