package com.ironhack.midterm_project.service.account.impl;

import com.ironhack.midterm_project.controller.account.dto.CheckingDTO;
import com.ironhack.midterm_project.enums.Status;
import com.ironhack.midterm_project.model.account.Checking;
import com.ironhack.midterm_project.model.other.Money;
import com.ironhack.midterm_project.model.user.AccountHolder;
import com.ironhack.midterm_project.repository.account.CheckingRepository;
import com.ironhack.midterm_project.repository.user.AccountHolderRepository;
import com.ironhack.midterm_project.service.account.interfaces.CheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Optional;

@Service
public class CheckingServiceImpl implements CheckingService {
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public void updateSecretKey(Long id, String secretKey) {
        Optional<Checking> optionalChecking = checkingRepository.findById(id);
        if (!optionalChecking.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Checking not found");
        }
        optionalChecking.get().setSecretKey(secretKey);
        checkingRepository.save(optionalChecking.get());
    }

    public Checking store(CheckingDTO checkingDto){
        AccountHolder primaryUser = accountHolderRepository.findById(checkingDto.getPrimaryOwnerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountHolder not found"));
        LocalDate today24YearsBefore = LocalDate.now().minusYears(24);
        checkingDto.setSecretKey(passwordEncoder.encode(checkingDto.getSecretKey()));
        if(primaryUser.getDateOfBirth().toLocalDate().isAfter(today24YearsBefore)){
            throw new IllegalArgumentException("The primary owner of the Checking must be at least 24 old. " +
                    "Please create a Student Checking");
        }
        Long secondaryOwnerId = checkingDto.getSecondaryOwnerId();
        if (secondaryOwnerId == 0) {
            Checking checking = new Checking(new Money(checkingDto.getMoney(), Currency.getInstance("USD")), checkingDto.getSecretKey(),
                    primaryUser, checkingDto.getCreationDate());
            return checking;
        }
        Optional<AccountHolder> secondaryOwner = accountHolderRepository.findById(checkingDto.getSecondaryOwnerId());
        Checking checking = new Checking(new Money(checkingDto.getMoney(), Currency.getInstance("USD")), primaryUser, secondaryOwner.get(), checkingDto.getCreationDate(),checkingDto.getSecretKey(),checkingDto.getStatus());
        return checking;
    }
    public Checking update(Long id, CheckingDTO checkingDto) {
        Checking checking1 = checkingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        Optional<AccountHolder> primaryUser = accountHolderRepository.findById(checkingDto.getPrimaryOwnerId());
        checkingDto.setSecretKey(passwordEncoder.encode(checkingDto.getSecretKey()));
        if (checkingDto.getSecondaryOwnerId() == 0) {
            Checking checking = new Checking(new Money(checkingDto.getMoney(), Currency.getInstance("USD")), checkingDto.getSecretKey(),
                    primaryUser.get(), checkingDto.getCreationDate());
            return checking;
        }
        Optional<AccountHolder> secondaryOwner = accountHolderRepository.findById(checkingDto.getSecondaryOwnerId());
        Checking checking = new Checking(new Money(checkingDto.getMoney(), Currency.getInstance("USD")), primaryUser.get(), secondaryOwner.get(),
                checkingDto.getCreationDate(),checkingDto.getSecretKey(),checkingDto.getStatus());
        checkingRepository.save(checking);
        return checking;
    }

    public void updateBalance(Long id, BigDecimal balance) {
        Checking checking = checkingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        checking.getBalance().setAmount(balance);
        checking.setBalance(checking.getBalance());
        checkingRepository.save(checking);
    }
    public Checking delete(Long id) {
        Checking checking = checkingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checking not found"));
        return checking;
    }
}
