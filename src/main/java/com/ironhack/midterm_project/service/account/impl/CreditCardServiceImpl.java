package com.ironhack.midterm_project.service.account.impl;

import com.ironhack.midterm_project.controller.account.dto.CreditCardDTO;
import com.ironhack.midterm_project.model.account.CreditCard;
import com.ironhack.midterm_project.model.other.Money;
import com.ironhack.midterm_project.model.user.AccountHolder;
import com.ironhack.midterm_project.repository.account.CreditCardRepository;
import com.ironhack.midterm_project.repository.user.AccountHolderRepository;
import com.ironhack.midterm_project.service.account.interfaces.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CreditCard store(CreditCardDTO creditCardDto){
        AccountHolder primaryUser = accountHolderRepository.findById(creditCardDto.getPrimaryOwnerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Accountholder not found"));
        creditCardDto.setSecretKey(passwordEncoder.encode(creditCardDto.getSecretKey()));
        if (creditCardDto.getSecondaryOwnerId() == 0L) {
            CreditCard creditCard = new CreditCard(new Money(creditCardDto.getMoney(), Currency.getInstance("USD")),
                    primaryUser,creditCardDto.getCreationDate(),creditCardDto.getSecretKey(),new Money(creditCardDto.getCreditLimit(), Currency.getInstance("USD")),creditCardDto.getInterestRate());
            return creditCard;
        }
        Optional<AccountHolder> secondaryUser = accountHolderRepository.findById(creditCardDto.getSecondaryOwnerId());
        CreditCard creditCard = new CreditCard(new Money(creditCardDto.getMoney(), Currency.getInstance("USD")), primaryUser, secondaryUser.get(),
                creditCardDto.getCreationDate(), creditCardDto.getSecretKey(), new Money(creditCardDto.getCreditLimit(), Currency.getInstance("USD")), creditCardDto.getInterestRate());
        return creditCard;
    }

    public void update(Long id, CreditCardDTO creditCardDto) {
        CreditCard creditCard1 = creditCardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CreditCard not found"));
        AccountHolder primaryUser = accountHolderRepository.findById(creditCardDto.getPrimaryOwnerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Accountholder not found"));
        creditCardDto.setSecretKey(passwordEncoder.encode(creditCardDto.getSecretKey()));
        if (creditCardDto.getSecondaryOwnerId() == 0L) {
            CreditCard creditCard = new CreditCard(new Money(creditCardDto.getMoney(), Currency.getInstance("USD")), primaryUser,
                    creditCardDto.getCreationDate(), creditCardDto.getSecretKey(), new Money(creditCardDto.getCreditLimit(), Currency.getInstance("USD")), creditCardDto.getInterestRate());
            creditCardRepository.save(creditCard);
        }else {
            Optional<AccountHolder> secondaryUser = accountHolderRepository.findById(creditCardDto.getSecondaryOwnerId());
            CreditCard creditCard = new CreditCard(new Money(creditCardDto.getMoney(), Currency.getInstance("USD")), primaryUser, secondaryUser.get(),
                    creditCardDto.getCreationDate(), creditCardDto.getSecretKey(), new Money(creditCardDto.getCreditLimit(), Currency.getInstance("USD")), creditCardDto.getInterestRate());
            creditCardRepository.save(creditCard);
        }
    }

    public void updateBalance(Long id, BigDecimal balance) {
        CreditCard creditCard = creditCardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CreditCard not found"));
        creditCard.getBalance().setAmount(balance);
        creditCard.setBalance(creditCard.getBalance());
        creditCardRepository.save(creditCard);
    }
    public void delete(Long id) {
        CreditCard creditCard = creditCardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CreditCard not found"));
        creditCardRepository.delete(creditCard);
    }
}
