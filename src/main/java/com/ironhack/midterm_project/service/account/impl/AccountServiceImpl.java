package com.ironhack.midterm_project.service.account.impl;

import com.ironhack.midterm_project.controller.account.dto.Transference;
import com.ironhack.midterm_project.controller.user.dto.ThirdPartyDTO;
import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.model.user.AccountHolder;
import com.ironhack.midterm_project.model.user.ThirdParty;
import com.ironhack.midterm_project.repository.account.AccountRepository;
import com.ironhack.midterm_project.repository.user.AccountHolderRepository;
import com.ironhack.midterm_project.repository.user.ThirdPartyRepository;
import com.ironhack.midterm_project.service.account.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Set;
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;


    public void transferBalance(Transference transference) {
        AccountHolder accountHolder = accountHolderRepository.findByName(transference.getName()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        BigDecimal money;
        Set<Account> accountsSecondary = accountHolder.getAccounts();
        Set<Account> accounts = accountHolder.getSecondaryAccounts();;
        for(Account account3 : accountsSecondary){
            accounts.add(account3);
        }
        for(Account account4 : accounts){
            if(account4.getBalance().getAmount().compareTo(transference.getMoney()) > 0) {
                Account account = account4;
                account.getBalance().setAmount((account.getBalance().getAmount().subtract(transference.getMoney())));
                accountRepository.save(account);
                Account account2 = accountRepository.findById(transference.getAccountId2()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
                account2.getBalance().setAmount(account2.getBalance().getAmount().add(transference.getMoney()));
                accountRepository.save(account2);
                break;
            }else{
                throw new IllegalArgumentException("This Owner has not enough balance");
            }
        }
    }

    public Account thirdPartyReceive(ThirdPartyDTO thirdPartyDto, String hashedKey){
        ThirdParty thirdParty = thirdPartyRepository.findByHashedKey(hashedKey).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Third Party not found"));
        Account receivingAccount = accountRepository.findById(thirdPartyDto.getAccountId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Receiving account not found"));
        if (!thirdPartyDto.getSecretKey().equals(receivingAccount.getSecretKey())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The secret key indicated is not correct");
        }
        receivingAccount.getBalance().setAmount(receivingAccount.getBalance().increaseAmount(thirdPartyDto.getMoney()));
        accountRepository.save(receivingAccount);
        return receivingAccount;
    }

    public Account thirdPartySend(ThirdPartyDTO thirdPartyDto, String hashedKey){
        ThirdParty thirdParty = thirdPartyRepository.findByHashedKey(hashedKey).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Third Party not found"));
        Account sendingAccount = accountRepository.findById(thirdPartyDto.getAccountId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Receiving account not found"));
        if (!thirdPartyDto.getSecretKey().equals(sendingAccount.getSecretKey())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The secret key indicated is not correct");
        }
        sendingAccount.getBalance().setAmount(sendingAccount.getBalance().decreaseAmount(thirdPartyDto.getMoney()));
        accountRepository.save(sendingAccount);
        return sendingAccount;
    }
}
