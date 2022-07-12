package com.ironhack.midterm_project.model.account;

import com.ironhack.midterm_project.model.other.Money;
import com.ironhack.midterm_project.model.user.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CreditCard extends Account{

    private static final Money DEFAULT_CREDIT_LIMIT = new Money(BigDecimal.valueOf(100));
    private static final Money UPPER_CREDIT_LIMIT = new Money(BigDecimal.valueOf(100000));
    private static final BigDecimal DEFAULT_INTEREST_RATE = BigDecimal.valueOf(0.2);
    private static final BigDecimal LOWER_INTEREST_RATE = BigDecimal.valueOf(0.1);
    @Embedded
    private Money creditLimit;
    private BigDecimal interestRate;
    private Date lastInterestsModificationDate = null;


    public CreditCard() {
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Date creationDate, String secretKey, Money creditLimit, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner, creationDate, secretKey);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, Date creationDate, String secretKey, Money creditLimit, BigDecimal interestRate) {
        super(balance, primaryOwner, creationDate, secretKey);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public Date getLastInterestsModificationDate() {
        return lastInterestsModificationDate;
    }

    public void setLastInterestsModificationDate(Date lastInterestsModificationDate) {
        this.lastInterestsModificationDate = lastInterestsModificationDate;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        if (this.creditLimit.getAmount().compareTo(BigDecimal.valueOf(100000))==1){
            this.creditLimit=UPPER_CREDIT_LIMIT;
        } else if (this.creditLimit.getAmount().compareTo(BigDecimal.valueOf(100))==-1 || this.creditLimit==null) {
            this.creditLimit=DEFAULT_CREDIT_LIMIT;
        } else {
            this.creditLimit=creditLimit;
        }
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        if (this.interestRate.compareTo(BigDecimal.valueOf(0.2))==1 || this.creditLimit==null){
            this.interestRate=DEFAULT_INTEREST_RATE;
        } else if (this.interestRate.compareTo(BigDecimal.valueOf(0.1))==-1) {
            this.interestRate=LOWER_INTEREST_RATE;
        } else {
            this.interestRate=interestRate;
        }
    }

    public void applyMonthlyInterestsCreditCard() {
        if (LocalDate.now().minusMonths(1).compareTo(this.getCreationDate().toLocalDate())==1 ||
                LocalDate.now().minusMonths(1).compareTo(this.getCreationDate().toLocalDate())==0 ||
                LocalDate.now().minusMonths(1).compareTo(this.getLastInterestsModificationDate().toLocalDate())==1 ||
                LocalDate.now().minusMonths(1).compareTo(this.getLastInterestsModificationDate().toLocalDate())==0){
            Money newBalance = new Money(this.getBalance().getAmount().multiply(BigDecimal.valueOf(1).subtract(getInterestRate().divide
                    (BigDecimal.valueOf (100)).divide(BigDecimal.valueOf(12)))),getBalance().getCurrency());
            this.setBalance(newBalance);
        }
    }



}
