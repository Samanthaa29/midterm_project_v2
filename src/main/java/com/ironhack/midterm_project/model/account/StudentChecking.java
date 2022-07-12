package com.ironhack.midterm_project.model.account;

import com.ironhack.midterm_project.enums.Status;
import com.ironhack.midterm_project.model.other.Money;
import com.ironhack.midterm_project.model.user.AccountHolder;

import javax.persistence.*;
import java.sql.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class StudentChecking extends Account{

    @Enumerated(EnumType.STRING)
    private Status status;

    public StudentChecking() {
    }

    public StudentChecking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Date creationDate, String secretKey, Status status) {
        super(balance, primaryOwner, secondaryOwner, creationDate, secretKey);
        this.status = status;
    }

    public StudentChecking(Money balance, AccountHolder primaryOwner, Date creationDate, String secretKey, Status status) {
        super(balance, primaryOwner, creationDate, secretKey);
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
