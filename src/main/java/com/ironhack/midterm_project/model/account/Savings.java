package com.ironhack.midterm_project.model.account;

import com.ironhack.midterm_project.enums.Status;
import com.ironhack.midterm_project.model.other.Money;
import com.ironhack.midterm_project.model.user.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Savings extends Account{

    private static final Money DEFAULT_MINIMUM_BALANCE = new Money(BigDecimal.valueOf(1000));
    private static final Money LOWER_MINIMUM_BALANCE = new Money(BigDecimal.valueOf(100));
    private static final BigDecimal DEFAULT_INTEREST_RATE = BigDecimal.valueOf(0.0025);
    private static final BigDecimal UPPER_INTEREST_RATE = BigDecimal.valueOf(0.5);

    @Embedded
    private Money minimumBalance;
    @Enumerated(EnumType.STRING)
    private Status status;
    private BigDecimal interestRate;
    private Date lastInterestsModificationDate = null;

    public Savings() {
    }

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Date creationDate, String secretKey, Money minimumBalance, Status status, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner, creationDate, secretKey);
        this.minimumBalance = minimumBalance;
        this.status = status;
        this.interestRate = interestRate;
    }

    public Savings(Money balance, AccountHolder primaryOwner, Date creationDate, String secretKey, Money minimumBalance, Status status, BigDecimal interestRate) {
        super(balance, primaryOwner, creationDate, secretKey);
        this.minimumBalance = minimumBalance;
        this.status = status;
        this.interestRate = interestRate;
    }

    public Date getLastInterestsModificationDate() {
        return lastInterestsModificationDate;
    }

    public void setLastInterestsModificationDate(Date lastInterestsModificationDate) {
        this.lastInterestsModificationDate = lastInterestsModificationDate;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        if (this.minimumBalance.getAmount().compareTo(BigDecimal.valueOf(1000))==1 || this.minimumBalance==null){
            this.minimumBalance=DEFAULT_MINIMUM_BALANCE;
        } else if (this.minimumBalance.getAmount().compareTo(BigDecimal.valueOf(100))==-1) {
            this.minimumBalance=LOWER_MINIMUM_BALANCE;
        } else {
            this.minimumBalance=minimumBalance;
        }
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (this.interestRate.compareTo(BigDecimal.valueOf(0.5))==1){
            this.interestRate=UPPER_INTEREST_RATE;
        } else if (this.interestRate.compareTo(BigDecimal.valueOf(0.0025))==-1 || this.interestRate==null) {
            this.interestRate=DEFAULT_INTEREST_RATE;
        } else {
            this.interestRate=interestRate;
        }
    }

    public void CheckBalance(){
        if (getBalance().getAmount().compareTo(getMinimumBalance().getAmount())==-1){
            Money newBalance = new Money(this.getBalance().getAmount().subtract(getPenaltyFee().getAmount()),getBalance().getCurrency());
            this.setBalance(newBalance);
        }
    }
    public void applyAnnualInterestsSavingAccount() {
        if (LocalDate.now().minusYears(1).compareTo(this.getCreationDate().toLocalDate())==1 ||
                LocalDate.now().minusYears(1).compareTo(this.getCreationDate().toLocalDate())==0 ||
                LocalDate.now().minusYears(1).compareTo(this.getLastInterestsModificationDate().toLocalDate())==1 ||
                LocalDate.now().minusYears(1).compareTo(this.getLastInterestsModificationDate().toLocalDate())==0){
            Money newBalance = new Money(this.getBalance().getAmount().multiply((getInterestRate().divide
                    (BigDecimal.valueOf (100))).add(BigDecimal.valueOf(1))),getBalance().getCurrency());
            this.setBalance(newBalance);
        }
    }
}
