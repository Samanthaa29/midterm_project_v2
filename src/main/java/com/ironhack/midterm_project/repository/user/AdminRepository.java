package com.ironhack.midterm_project.repository.user;

import com.ironhack.midterm_project.model.account.Checking;
import com.ironhack.midterm_project.model.user.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
}
