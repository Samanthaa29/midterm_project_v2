package com.ironhack.midterm_project.repository.account;

import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.model.account.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    @Query("SELECT a FROM Account a JOIN AccountHolder ah ON a.primaryOwner = ah.id")
    Set<Account> findAllMyAccounts(@Param("id") Long id);

}
