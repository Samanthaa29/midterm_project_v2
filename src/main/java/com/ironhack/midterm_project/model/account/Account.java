package com.ironhack.midterm_project.model.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.midterm_project.model.other.Address;
import com.ironhack.midterm_project.model.other.Money;
import com.ironhack.midterm_project.model.user.AccountHolder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {

    private static final Money PENALTY_FEE = new Money(BigDecimal.valueOf(40));

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Money balance;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "primary_owner_id")
    private AccountHolder primaryOwner;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "secondary_owner_id")

    private AccountHolder secondaryOwner;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount",column = @Column(name = "penalty_amount")),
            @AttributeOverride(name="currency", column = @Column(name = "penalty_currency"))
    })
    private Money penaltyFee;
    private Date creationDate;
    private String secretKey;

    public Account() {
    }

    public Account(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Date creationDate, String secretKey) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.penaltyFee = penaltyFee;
        this.creationDate = creationDate;
        this.secretKey = secretKey;
    }
    // Constructor without secondaryOwner because it's an Optional
    public Account(Money balance, AccountHolder primaryOwner, Date creationDate, String secretKey) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.penaltyFee = penaltyFee;
        this.creationDate = creationDate;
        this.secretKey = secretKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public AccountHolder getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(AccountHolder primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public AccountHolder getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(AccountHolder secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public Money getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(Money penaltyFee) {
        this.penaltyFee = PENALTY_FEE;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
