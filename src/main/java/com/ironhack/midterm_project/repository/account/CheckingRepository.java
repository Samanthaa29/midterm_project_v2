package com.ironhack.midterm_project.repository.account;

import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.model.account.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CheckingRepository extends JpaRepository<Checking,Long> {
    @Query("SELECT c FROM Checking c JOIN User u ON c.primaryOwner = u.id WHERE u.id = :id")
    Optional<Checking> findByUserid(@Param("id") Long id);
}
