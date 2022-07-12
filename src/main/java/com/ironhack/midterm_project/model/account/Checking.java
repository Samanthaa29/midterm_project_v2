package com.ironhack.midterm_project.model.account;

import com.ironhack.midterm_project.enums.Status;
import com.ironhack.midterm_project.model.other.Money;
import com.ironhack.midterm_project.model.user.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Checking extends Account {

    private static final Money MINIMUM_BALANCE = new Money(BigDecimal.valueOf(250));
    private static final Money MONTHLY_MAINTENANCE_FEE = new Money(BigDecimal.valueOf(12));

    @Embedded
    private Money minimumBalance;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount",column = @Column(name = "monthly_maintenance_fee_amount")),
            @AttributeOverride(name="currency", column = @Column(name = "monthly_maintenance_fee_currency"))
    })
    private Money monthlyMaintenanceFee;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Checking(Money usd, String secretKey, AccountHolder primaryUser, Date creationDate) {
    }

    public Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Date creationDate, String secretKey, Status status) {
        super(balance, primaryOwner, secondaryOwner, creationDate, secretKey);
        this.status = status;
    }

    public Checking(Money balance, AccountHolder primaryOwner, Date creationDate, String secretKey, Status status) {
        super(balance, primaryOwner, creationDate, secretKey);
        this.status = status;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = MINIMUM_BALANCE;  //Minimum_balance is a constant value = 250
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(Money monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = MONTHLY_MAINTENANCE_FEE;  //Monthly maintenance fee is a constant value = 12
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void checkBalance(){
        if (getBalance().getAmount().compareTo(getMinimumBalance().getAmount())==-1){
            Money newBalance = new Money(this.getBalance().getAmount().subtract(getPenaltyFee().getAmount()),getBalance().getCurrency());
            this.setBalance(newBalance);
        }
    }
}


