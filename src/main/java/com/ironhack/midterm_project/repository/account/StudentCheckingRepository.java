package com.ironhack.midterm_project.repository.account;

import com.ironhack.midterm_project.model.account.Account;
import com.ironhack.midterm_project.model.account.Checking;
import com.ironhack.midterm_project.model.account.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface StudentCheckingRepository extends JpaRepository<StudentChecking,Long> {
    @Query("SELECT sc FROM StudentChecking sc JOIN User u ON sc.primaryOwner = u.id WHERE u.id = :id")
    Optional<StudentChecking> findByUserid(@Param("id") Long id);
}
