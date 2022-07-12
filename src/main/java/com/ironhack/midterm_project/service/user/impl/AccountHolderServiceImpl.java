package com.ironhack.midterm_project.service.user.impl;

import com.ironhack.midterm_project.model.user.AccountHolder;
import com.ironhack.midterm_project.repository.user.AccountHolderRepository;
import com.ironhack.midterm_project.service.user.interfaces.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@Service
public class AccountHolderServiceImpl implements AccountHolderService {
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    public void update(Long id, AccountHolder accountHolder) {
        AccountHolder accountHolder1 = accountHolderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Holder not found"));
        accountHolder.setId(id);
        accountHolderRepository.save(accountHolder);
    }
    public void delete(Long id) {
        AccountHolder accountHolder = accountHolderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Holder not found"));
        accountHolderRepository.delete(accountHolder);
    }
}
