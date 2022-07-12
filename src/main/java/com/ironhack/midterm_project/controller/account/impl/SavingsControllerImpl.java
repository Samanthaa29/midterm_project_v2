package com.ironhack.midterm_project.controller.account.impl;

import com.ironhack.midterm_project.controller.account.interfaces.SavingsController;
import com.ironhack.midterm_project.controller.account.dto.MoneyDTO;
import com.ironhack.midterm_project.controller.account.dto.SavingsDTO;
import com.ironhack.midterm_project.model.account.Savings;
import com.ironhack.midterm_project.model.user.User;
import com.ironhack.midterm_project.repository.account.SavingsRepository;
import com.ironhack.midterm_project.security.CustomUserDetails;
import com.ironhack.midterm_project.service.account.interfaces.SavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class SavingsControllerImpl implements SavingsController {
    @Autowired
    private SavingsRepository savingsRepository;

    @Autowired
    private SavingsService savingService;


    @GetMapping("/savings")
    @ResponseStatus(HttpStatus.OK)
    public List<Savings> findAll(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return savingsRepository.findAll();
    }

    @GetMapping("/savings/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Savings findById(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable(name = "id") Long id) {
        Optional<Savings> optionalSaving = savingsRepository.findById(id);
        return optionalSaving.get();
    }

    @GetMapping("/savings/primaryOwner/{primaryOwner}")
    @ResponseStatus(HttpStatus.OK)
    public Savings findByUser(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable(name = "primaryOwner") User user) {
        Optional<Savings> optionalSaving = savingsRepository.findByUserid(user.getId());
        return optionalSaving.get();
    }

    @PostMapping("/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings store(@AuthenticationPrincipal CustomUserDetails userDetails,@RequestBody @Valid SavingsDTO savingDto) {
        Savings saving =  savingService.store(savingDto);
        return savingsRepository.save(saving);
    }

    @PutMapping("/savings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal CustomUserDetails userDetails,@PathVariable Long id, @RequestBody @Valid SavingsDTO savingDto) {
        savingService.update(id, savingDto);
    }

    @PatchMapping("/savings/{id}/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalance(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id, @RequestBody @Valid MoneyDTO balance) {
        savingService.updateBalance(id, balance.getBalance());
    }

    @DeleteMapping("/savings/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id) {
        savingService.delete(id);
    }

}
