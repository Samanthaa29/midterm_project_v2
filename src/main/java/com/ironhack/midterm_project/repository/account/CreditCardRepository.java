package com.ironhack.midterm_project.repository.account;

import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.model.account.Checking;
import com.ironhack.midterm_project.model.account.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard,Long> {
    @Query("SELECT cd FROM CreditCard cd JOIN User u ON cd.primaryOwner = u.id WHERE u.id = :id")
    Optional<CreditCard> findByUserid(@Param("id") Long id);
}
