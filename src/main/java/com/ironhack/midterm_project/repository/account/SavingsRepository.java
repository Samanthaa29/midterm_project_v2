package com.ironhack.midterm_project.repository.account;

import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.model.account.Checking;
import com.ironhack.midterm_project.model.account.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SavingsRepository extends JpaRepository<Savings,Long> {
    @Query("SELECT s FROM Savings s JOIN User u ON s.primaryOwner = u.id WHERE u.id = :id")
    Optional<Savings> findByUserid(@Param("id") Long id);
}
