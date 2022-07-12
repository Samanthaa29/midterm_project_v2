package com.ironhack.midterm_project.repository.user;

import com.ironhack.midterm_project.model.account.Checking;
import com.ironhack.midterm_project.model.user.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder,Long>{
    Optional<AccountHolder> findByName(String name);
}
