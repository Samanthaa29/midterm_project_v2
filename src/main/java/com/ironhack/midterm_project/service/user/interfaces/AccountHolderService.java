package com.ironhack.midterm_project.service.user.interfaces;

import com.ironhack.midterm_project.model.user.AccountHolder;

public interface AccountHolderService {
    void update(Long id, AccountHolder accountHolder);
    void delete(Long id);
}
