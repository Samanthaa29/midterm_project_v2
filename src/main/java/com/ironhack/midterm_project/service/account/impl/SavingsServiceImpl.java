package com.ironhack.midterm_project.service.account.impl;

import com.ironhack.midterm_project.controller.account.dto.SavingsDTO;
import com.ironhack.midterm_project.model.account.Savings;
import com.ironhack.midterm_project.model.other.Money;
import com.ironhack.midterm_project.model.user.AccountHolder;
import com.ironhack.midterm_project.repository.account.SavingsRepository;
import com.ironhack.midterm_project.repository.user.AccountHolderRepository;
import com.ironhack.midterm_project.service.account.interfaces.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

@Service
public class SavingsServiceImpl implements SavingsService {

    @Autowired
    private SavingsRepository savingsRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public Savings store(SavingsDTO savingDto){
        AccountHolder primaryUser = accountHolderRepository.findById(savingDto.getPrimaryOwnerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Holder not found"));
        savingDto.setSecretKey(passwordEncoder.encode(savingDto.getSecretKey()));
        if (savingDto.getSecondaryOwnerId() == 0L) {
            Savings savings = new Savings(new Money(savingDto.getMoney(), Currency.getInstance("USD")),
                    primaryUser, savingDto.getCreationDate(),savingDto.getSecretKey(), new Money(savingDto.getMinimumBalance(), Currency.getInstance("USD")),savingDto.getStatus(),savingDto.getInterestRate());
            return savings;
        }
        Optional<AccountHolder> secondaryUser = accountHolderRepository.findById(savingDto.getSecondaryOwnerId());
        Savings savings = new Savings(new Money(savingDto.getMoney(), Currency.getInstance("USD")),primaryUser, secondaryUser.get(), savingDto.getCreationDate(), savingDto.getSecretKey(),
                new Money(savingDto.getMinimumBalance(), Currency.getInstance("USD")), savingDto.getStatus(),savingDto.getInterestRate());
        return savings;
    }

    public void update(Long id, SavingsDTO savingDto) {
        Savings savings1 = savingsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Saving not found"));
        AccountHolder primaryUser = accountHolderRepository.findById(savingDto.getPrimaryOwnerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Holder not found"));
        savingDto.setSecretKey(passwordEncoder.encode(savingDto.getSecretKey()));
        if (savingDto.getSecondaryOwnerId() == 0L) {
            Savings saving = new Savings(new Money(savingDto.getMoney(), Currency.getInstance("USD")),
                    primaryUser, savingDto.getCreationDate(),savingDto.getSecretKey(), new Money(savingDto.getMinimumBalance(), Currency.getInstance("USD")),savingDto.getStatus(),savingDto.getInterestRate());
            savingsRepository.save(saving);
        }else {
            Optional<AccountHolder> secondaryUser = accountHolderRepository.findById(savingDto.getSecondaryOwnerId());
            Savings savings = new Savings(new Money(savingDto.getMoney(), Currency.getInstance("USD")),primaryUser, secondaryUser.get(), savingDto.getCreationDate(),
                    savingDto.getSecretKey(), new Money(savingDto.getMinimumBalance(), Currency.getInstance("USD")),savingDto.getStatus(),savingDto.getInterestRate());
            savingsRepository.save(savings);
        }
    }

    public void updateBalance(Long id, BigDecimal balance) {
        Savings savings = savingsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Saving not found"));
        savings.getBalance().setAmount(balance);
        savingsRepository.save(savings);
    }
    public void delete(Long id) {
        Savings savings = savingsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Saving not found"));
        savingsRepository.delete(savings);
    }
}