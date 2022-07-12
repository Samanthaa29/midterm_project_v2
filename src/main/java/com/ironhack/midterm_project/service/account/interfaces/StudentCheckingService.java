package com.ironhack.midterm_project.service.account.interfaces;

import com.ironhack.midterm_project.controller.account.dto.StudentCheckingDTO;
import com.ironhack.midterm_project.model.account.StudentChecking;

import java.math.BigDecimal;

public interface StudentCheckingService {
    StudentChecking store(StudentCheckingDTO studentCheckingDto);
    void update(Long id, StudentCheckingDTO studentCheckingDto);
    void updateBalance(Long id, BigDecimal balance);
    void delete(Long id);
}
