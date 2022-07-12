package com.ironhack.midterm_project.service.account.impl;

import com.ironhack.midterm_project.controller.account.dto.StudentCheckingDTO;
import com.ironhack.midterm_project.model.account.StudentChecking;
import com.ironhack.midterm_project.model.other.Money;
import com.ironhack.midterm_project.model.user.AccountHolder;
import com.ironhack.midterm_project.repository.account.StudentCheckingRepository;
import com.ironhack.midterm_project.repository.user.AccountHolderRepository;
import com.ironhack.midterm_project.service.account.interfaces.StudentCheckingService;
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
public class StudentCheckingServiceImpl implements StudentCheckingService {
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public StudentChecking store(StudentCheckingDTO studentCheckingDto){
        AccountHolder primaryUser = accountHolderRepository.findById(studentCheckingDto.getPrimaryOwnerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountHolder not found"));
        studentCheckingDto.setSecretKey(passwordEncoder.encode(studentCheckingDto.getSecretKey()));
        LocalDate today24YearsBefore = LocalDate.now().minusYears(24);
        if(primaryUser.getDateOfBirth().toLocalDate().isBefore(today24YearsBefore)){
            throw new IllegalArgumentException("The primary owner of the Student Checking must be less than 24 old\n" +
                    "Please create a Checking");
        }
        if (studentCheckingDto.getSecondaryOwnerId() == 0L) {
            StudentChecking studentChecking = new StudentChecking(new Money(studentCheckingDto.getMoney(), Currency.getInstance("USD")),
                    primaryUser, studentCheckingDto.getCreationDate(),studentCheckingDto.getSecretKey(),studentCheckingDto.getStatus());
            return studentChecking;
        }
        Optional<AccountHolder> secondaryUser = accountHolderRepository.findById(studentCheckingDto.getSecondaryOwnerId());
        StudentChecking studentChecking = new StudentChecking(new Money(studentCheckingDto.getMoney(), Currency.getInstance("USD")),
                primaryUser, secondaryUser.get(), studentCheckingDto.getCreationDate(),studentCheckingDto.getSecretKey(),studentCheckingDto.getStatus());
        return studentChecking;
    }
    public void update(Long id, StudentCheckingDTO studentCheckingDto) {
        StudentChecking studentChecking1 = studentCheckingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StudentChecking not found"));
        studentCheckingDto.setSecretKey(passwordEncoder.encode(studentCheckingDto.getSecretKey()));
        AccountHolder primaryUser = accountHolderRepository.findById(studentCheckingDto.getPrimaryOwnerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AccountHolder not found"));
        if (studentCheckingDto.getSecondaryOwnerId() == 0L) {
            StudentChecking studentChecking = new StudentChecking(new Money(studentCheckingDto.getMoney(), Currency.getInstance("USD")),
                    primaryUser, studentCheckingDto.getCreationDate(),studentCheckingDto.getSecretKey(),studentCheckingDto.getStatus());
            studentCheckingRepository.save(studentChecking);
        }else {
            Optional<AccountHolder> secondaryUser = accountHolderRepository.findById(studentCheckingDto.getSecondaryOwnerId());
            StudentChecking studentChecking = new StudentChecking(new Money(studentCheckingDto.getMoney(), Currency.getInstance("USD")),
                    primaryUser, secondaryUser.get(), studentCheckingDto.getCreationDate(),studentCheckingDto.getSecretKey(),studentCheckingDto.getStatus());
            studentCheckingRepository.save(studentChecking);
        }
    }

    public void updateBalance(Long id, BigDecimal balance) {
        StudentChecking studentChecking = studentCheckingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StudentChecking not found"));
        studentChecking.getBalance().setAmount(balance);
        studentCheckingRepository.save(studentChecking);
    }
    public void delete(Long id) {
        StudentChecking studentChecking = studentCheckingRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StudentChecking not found"));
        studentCheckingRepository.delete(studentChecking);
    }
}
